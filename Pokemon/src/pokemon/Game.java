package pokemon;

import org.lwjgl.glfw.GLFW;

import engine.Camera;
import engine.GameEngine2D;
import engine.Logger;
import engine.Settings;
import engine.Window;
import engine.entity.Tile;
import engine.entity.Transform;
import engine.gfx.TileSheet;
import engine.gfx.TileSheetManager;
import engine.gui.Button;
import engine.gui.GUI;
import engine.gui.GUIManager;
import engine.input.Input;
import engine.level.Level;
import engine.level.LevelManager;
import engine.math.Vector2f;
import pokemon.entities.Player;

public class Game extends GameEngine2D {

    private Window window;
    private Camera camera;
    private Input input;

    private TileSheetManager tile_sheet_manager;
    private GUIManager gui_manager;
    private LevelManager level_manager;

    // TILES
    // private final Tile t_grass = new Tile(2, 1).setSolid(true);
    // private final Tile t_1 = new Tile(1, 0).setSolid(true);
    // private final Tile t_2 = new Tile(2, 0).setSolid(true);
    // private final Tile t_3 = new Tile(3, 0).setSolid(true);

    @Override
    protected void onSetUp(Settings settings) {
	Logger.printMsg("Started..");
	settings.title = "Pokemon";

	settings.width = 1216;
	settings.height = 704;
	settings.fullscreen = false;

	// settings.width = 1920;
	// settings.height = 1080;
	// settings.fullscreen = true;

	settings.vsync = true;

    }

    @Override
    protected void handleWindow(Window window) {
	this.window = window;
	this.input = window.getInput();
	this.camera = window.getCamera();

	// INIT ALL TILES
	for (int y = 0; y < 16; y++) {
	    for (int x = 0; x < 16; x++) {
		new Tile(x, y);
	    }
	}
    }

    @Override
    protected void loadRessources(TileSheetManager tsm) {
	tsm.addTileSheet("test", new TileSheet("test", 16));
	tsm.addTileSheet("player_m", new TileSheet("player_m", 32));
	this.tile_sheet_manager = tsm;
    }

    @Override
    protected void handleGUIManager(GUIManager gui_manager) {
	// MAINMENU
	GUI main_menu = new GUI(window);

	Button play = new Button("PLAY", window.width / 2 - 256 / 2, window.height / 2 - 200, 256, 64);
	play.setOnReleased(() -> {
	    level_manager.setCurrentLevel("level_1");
	    gui_manager.setCurrentGUI("ingame");
	});
	Button editor = new Button("EDITOR", window.width / 2 - 256 / 2, window.height / 2 - 100, 256, 64);
	editor.setOnReleased(() -> {
	    super.showLevelEditor();
	});
	Button quit = new Button("QUIT", window.width / 2 - 256 / 2, window.height / 2, 256, 64);
	quit.setOnReleased(() -> {
	    super.requestClose();
	});

	main_menu.addElements(play, editor, quit);
	gui_manager.addGUI("main_menu", main_menu);
	gui_manager.setCurrentGUI("main_menu");

	// INGAME
	GUI ingame = new GUI(window);
	gui_manager.addGUI("ingame", ingame);
    }

    @Override
    protected void handleLevelManager(LevelManager level_manager) {
	this.level_manager = level_manager;
	Level test_level_1 = level_manager.getLevel("level_1");

	// adding test-entities
	Player player = new Player(new Transform(new Vector2f(60, -60)), tile_sheet_manager.getTileSheet("test"));
	player.setSolid(true);
	test_level_1.addEntity(player);
    }

    // for camera controll
    int entIndex = 0;

    @Override
    protected void onUpdate(float delta) {
	level_manager.getCurrentLevel().ifPresent(level -> {

	    // entity camera follow
	    if (input.isKeyPressed(Input.N1)) {
		entIndex++;
		if (entIndex >= level.getEntities().size()) {
		    entIndex = 0;
		}
	    }
	    if (!level.getEntities().isEmpty()) {
		camera.focusOn(level.getEntities().get(entIndex), level);
	    }

	    // zoom
	    if (window.getInput().isKeyPressed(GLFW.GLFW_KEY_KP_ADD)) {
		level.zoomIn(window);
		if (!level.getEntities().isEmpty())
		    camera.focusOn(level.getEntities().get(entIndex), level);
	    }
	    if (window.getInput().isKeyPressed(GLFW.GLFW_KEY_KP_SUBTRACT)) {
		level.zoomOut(window);
		if (!level.getEntities().isEmpty())
		    camera.focusOn(level.getEntities().get(entIndex), level);
	    }
	});
    }

    @Override
    protected void onRender() {

    }

    @Override
    protected void onEnd() {
	Logger.printMsg("Application stoped!");
    }

    public static void main(String[] args) {
	launch(args, new Game());
    }

    // deprecated
    /*
     * Entity[] test_entities = new Entity[1]; for (int i = 0; i <
     * test_entities.length; i++) { Random r = new Random(); Vector2f pos = new
     * Vector2f(r.nextInt(100), -r.nextInt(100)); test_entities[i] = new Entity(new
     * Transform(pos, new Vector2f(1.0f, 1.0f)),
     * tile_sheet_manager.getTileSheet("test")) { private Vector3f dest = new
     * Vector3f(r.nextInt(100), -r.nextInt(100), 0); private int speed = 10;
     * 
     * @Override protected void onUpdate(float delta, Window window, Camera camera,
     * Level world) { if (EntityMover.moveTo(this, dest, speed)) { dest = new
     * Vector3f(r.nextInt(100), -r.nextInt(100), 0); } }
     * 
     * @Override protected Animation[] addAnimations(TileSheet tile_sheet) {
     * Animation[] animations = new Animation[1]; animations[0] = new Animation(1,
     * 3, tile_sheet, 4); bounding_box = new AABB(new Vector2f(transform.pos.x,
     * transform.pos.y), new Vector2f(transform.scale.x, transform.scale.y / 2));
     * return animations; } }; Entity e = test_entities[i]; e.setSolid(true); }
     * test_level_1.addEntities(test_entities);
     * 
     */

}
