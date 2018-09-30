package engine.editor;

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
	}

	public void render(Shader shader, Camera camera) {
	}

	public void show(Camera camera) {
		gui_manager.setCurrentGUI("editor");
		level_manager.setCurrentLevel("temp");
		camera.setPosition(new Vector3f(0, 0, 0));
	}

	private void initEditorGUI(Input input, Window window) {
		GUI editor = new GUI(window);

		TextInput level_name_input = new TextInput("test", window.width - 384, window.height - 32, 384, 32);

		Button save = new Button("SAVE", window.width - 512, window.height - 32, 128, 32);
		save.setOnReleased(() -> {
			Level temp = level_manager.getLevel("temp");
			temp.setName(level_name_input.text);
			CSVLevelParser.save(level_manager.getCurrentLevel().get());
		});

		// LIST VIEW
		ListView list_view = new ListView(window.width - 512, 0, 512, window.height - 32);
		final Button temp = new Button("", 50 + 64, 50, 48, 48);
		temp.setEnabled(false);
		for (int i = 0; i < Tile.tiles.length; i++) {
			Tile t = Tile.tiles[i];
			if (t != null) {
				System.out.println(t.getId() + " " + t.getTextureX() + " " + t.getTextureY());
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
							level.setTile(t, (int) input.getCursorPos().x / level.getScale() / 2,
									(int) input.getCursorPos().y / level.getScale() / 2);
						});
					});
				});
				System.out.println("added: " + button);
				list_view.addElement(button);
			}
		}

		editor.addElements(save, level_name_input, list_view, temp);
		gui_manager.addGUI("editor", editor);
		gui_manager.setCurrentGUI("editor");
	}

}
