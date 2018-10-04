package engine.editor;

import java.io.File;
import java.util.List;

import engine.Camera;
import engine.Window;
import engine.entity.Tile;
import engine.gfx.Shader;
import engine.gfx.TileSheetManager;
import engine.gui.Button;
import engine.gui.GUI;
import engine.gui.GUIManager;
import engine.gui.ListView;
import engine.gui.TextInput;
import engine.input.Input;
import engine.level.Level;
import engine.level.LevelManager;
import engine.math.Vector3f;
import engine.utils.CSVLevelParser;
import engine.utils.IOUtils;

public class LevelEditor {

    private GUIManager gui_manager;
    private LevelManager level_manager;
    private TileSheetManager tile_sheet_manager;

    public LevelEditor(GUIManager gui_manager, LevelManager level_manager, TileSheetManager tile_sheet_manager,
	    Input input, Window window) {
	this.gui_manager = gui_manager;
	this.level_manager = level_manager;
	this.tile_sheet_manager = tile_sheet_manager;
	Level temp = new Level("temp", 256, 256);
	temp.calculateView(window);
	level_manager.addLevel("temp", temp);
	initLevelSelectGUI(input, window);
	initEditorGUI(input, window);
    }

    public void update(float delta, Window window, Camera camera) {
	float speed = window.getInput().isKeyDown(Input.L_SHIFT) ? 10 * 2 : 10;

	if (window.getInput().isKeyDown(Input.S)) {
	    camera.addPosition(new Vector3f(0, speed, 0));
	}
	if (window.getInput().isKeyDown(Input.W)) {
	    camera.addPosition(new Vector3f(0, -speed, 0));
	}
	if (window.getInput().isKeyDown(Input.D)) {
	    camera.addPosition(new Vector3f(-speed, 0, 0));
	}
	if (window.getInput().isKeyDown(Input.A)) {
	    camera.addPosition(new Vector3f(speed, 0, 0));
	}
	// System.out.println(camera.getPosition());
    }

    public void render(Shader shader, Camera camera) {
    }

    public void show(Camera camera) {
	gui_manager.setCurrentGUI("levelSelect");
	camera.setPosition(new Vector3f(0, 0, 0));
    }

    private void initLevelSelectGUI(Input input, Window window) {
	GUI levelSelect = new GUI(window);

	TextInput newLevel = new TextInput("", window.width / 4, 0, window.width / 2, 32);
	TextInput newLevelWidth = new TextInput("", window.width / 4, 48, window.width / 6, 32);
	TextInput newLevelHeight = new TextInput("", window.width / 4 + window.width / 6, 48, window.width / 6, 32);
	Button create = new TextInput("Create", window.width / 4 + 2 * window.width / 6, 48, window.width / 6, 32);

	create.setOnReleased(() -> {
	    Level level = new Level(newLevel.text, Integer.parseInt(newLevelWidth.text),
		    Integer.parseInt(newLevelHeight.text));
	    level.init(window);
	    level_manager.addLevel(newLevel.text, level);
	    level_manager.setCurrentLevel(newLevel.text);
	    level_name_input.text = newLevel.text;
	    gui_manager.setCurrentGUI("editor");
	});

	List<File> levelFiles = IOUtils.listFilesForFolder("level");
	ListView list_view = new ListView(window.width / 2 - window.width / 4, 96, window.width / 2,
		window.height - 32);
	list_view.setElementWidth(list_view.getContent_width());
	list_view.setElementHeight(32);
	for (File f : levelFiles) {
	    Button button = new Button(f.getName().replace(".csv", ""), 5, 5, 512, 64);
	    button.setOnReleased(() -> {
		level_manager.setCurrentLevel(button.getText());
		level_name_input.text = button.getText();
		gui_manager.setCurrentGUI("editor");
	    });
	    list_view.addElement(button);
	}

	levelSelect.addElements(newLevel, newLevelWidth, newLevelHeight, create, list_view);
	gui_manager.addGUI("levelSelect", levelSelect);
	gui_manager.setCurrentGUI("levelSelect");
    }

    TextInput level_name_input;

    private void initEditorGUI(Input input, Window window) {
	GUI editor = new GUI(window);

	level_name_input = new TextInput("PLACEHOLDER", window.width - 384, window.height - 32, 384, 32);

	Button save = new Button("SAVE", window.width - 512, window.height - 32, 128, 32);
	save.setOnReleased(() -> {
	    level_manager.getCurrentLevel().get().setName(level_name_input.text);
	    CSVLevelParser.save(level_manager.getCurrentLevel().get());
	    System.out.println("SAVED");
	});

	// LIST VIEW
	ListView list_view = new ListView(window.width - 512, 0, 512, window.height - 32);
	final Button temp = new Button("", 50 + 64, 50, 48, 48);
	temp.setEnabled(false);
	for (int i = 0; i < Tile.tiles.length; i++) {
	    Tile t = Tile.tiles[i];
	    if (t != null) {
		Button button = new Button("", 50 + 64 * i, 50, 48, 48);
		button.changeTileSheet(tile_sheet_manager.getTileSheet("tiles"));
		button.changeAllTextures(t.getTextureX(), t.getTextureY());
		button.setOnClicked(() -> {
		    temp.getBounds().x = button.getBounds().x;
		    temp.getBounds().y = button.getBounds().y;
		    temp.getBounds().width = button.getBounds().width * 1.1f;
		    temp.getBounds().height = button.getBounds().height * 1.1f;
		    temp.changeTileSheet(tile_sheet_manager.getTileSheet("tiles"));
		    temp.changeAllTextures(t.getTextureX(), t.getTextureY());
		    temp.setFollowMouse(true);
		    temp.setEnabled(true);
		    temp.setOnReleased(() -> {
			temp.setFollowMouse(false);
			temp.setEnabled(false);
			level_manager.getCurrentLevel().ifPresent(level -> {
			    float dx = -window.getCamera().getPosition().x - (window.width / 2 - 32);
			    float dy = window.getCamera().getPosition().y - (window.height / 2 - 32);
			    int x = (int) ((input.getCursorPos().x + dx) / level.getScale() / 2);
			    int y = (int) ((input.getCursorPos().y + dy) / level.getScale() / 2);
			    level.setTile(t, x, y);
			});
		    });
		});
		list_view.addElement(button);
	    }
	}

	editor.addElements(save, level_name_input, list_view, temp);
	gui_manager.addGUI("editor", editor);
    }

}
