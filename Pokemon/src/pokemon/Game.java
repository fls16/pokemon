package pokemon;

import org.lwjgl.glfw.GLFW;

import engine.Camera;
import engine.GameEngine2D;
import engine.Logger;
import engine.Settings;
import engine.Window;
import engine.entity.Entity;
import engine.entity.Tile;
import engine.entity.Transform;
import engine.gfx.TileSheet;
import engine.gfx.TileSheetManager;
import engine.gui.GUI;
import engine.gui.GUIManager;
import engine.gui.Option;
import engine.input.Input;
import engine.level.Level;
import engine.level.LevelManager;
import pokemon.entities.Player;
import pokemon.entities.PokeCenter;
import pokemon.util.PokeSpawn;

public class Game extends GameEngine2D {

    private Window window;
    private Camera camera;
    private Input input;

    private TileSheetManager tile_sheet_manager;
    private GUIManager gui_manager;
    private LevelManager level_manager;

    private Entity player;

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

	settings.vsync = false;

    }

    @Override
    protected void handleWindow(Window window) {
	this.window = window;
	this.input = window.getInput();
	this.camera = window.getCamera();
    }

    @Override
    protected void declareTiles() {
	// TILES
	Tile t_grass = new Tile(0, 0).setSolid(false);
	Tile t_flower_1 = new Tile(0, 1).setSolid(false);
    }

    @Override
    protected void loadRessources(TileSheetManager tsm) {
	// INIT ALL TILES
	tsm.addTileSheet(new TileSheet("test", 16));
	tsm.addTileSheet(new TileSheet("player", 8));
	tsm.addTileSheet(new TileSheet("entities", 64));
	this.tile_sheet_manager = tsm;
    }

    @Override
    protected void handleGUIManager(GUIManager gui_manager) {
	// MAINMENU
	GUI main_menu = new GUI(window);

	Option play = new Option("PLAY", window.width / 2 - 256 / 2, window.height / 2 - 200, 256, 64);
	play.setOnAction(() -> {
	    level_manager.setCurrentLevel("BLUBB");
	    gui_manager.setCurrentGUI("ingame");
	});
	Option editor = new Option("EDITOR", window.width / 2 - 256 / 2, window.height / 2 - 100, 256, 64);
	editor.setOnAction(() -> {
	    super.showLevelEditor();
	});
	Option quit = new Option("QUIT", window.width / 2 - 256 / 2, window.height / 2, 256, 64);
	quit.setOnAction(() -> {
	    super.requestClose();
	});

	main_menu.addElements(play, editor, quit);
	gui_manager.addGUI("main_menu", main_menu);
	gui_manager.setCurrentGUI("main_menu");

	// INGAME
	GUI ingame = new GUI(window);
	gui_manager.addGUI("ingame", ingame);

	// BATTLE
	GUI battle = new GUI(window);

	Option fight = new Option("FIGHT", window.width / 2, window.height - window.height / 4, window.width / 4,
		window.height / 8);
	Option bag = new Option("BAG", window.width / 2 + window.width / 4, window.height - window.height / 4,
		window.width / 4, window.height / 8);
	Option pokemon = new Option("POKEMON", window.width / 2, window.height - window.height / 8, window.width / 4,
		window.height / 8);
	Option run = new Option("RUN", window.width / 2 + window.width / 4, window.height - window.height / 8,
		window.width / 4, window.height / 8);

	battle.addElements(fight, bag, pokemon, run);

	gui_manager.addGUI("battle", battle);
	// gui_manager.setCurrentGUI("battle");
    }

    @Override
    protected void handleLevelManager(LevelManager level_manager) {
	this.level_manager = level_manager;
	Level test_level_1 = level_manager.getLevel("BLUBB");
	test_level_1 = new Level("BLUBB", 64, 64);
	test_level_1.calculateView(window);
	level_manager.addLevel("BLUBB", test_level_1);

	// adding test-tiles
	// test_level_1.setSecondaryTile(Tile.tiles[1], 3, 3);

	// adding test-entities
	player = new Player(new Transform(8, -8), tile_sheet_manager.getTileSheet("player")).setSolid(true);

	Entity pokeCenter = new PokeCenter(new Transform(12, -12, 5, 5), tile_sheet_manager.getTileSheet("entities"))
		.setSolid(true);
	Entity pokeSpawn1 = new PokeSpawn(new Transform(2, -2), tile_sheet_manager.getTileSheet("entities"));
	Entity pokeSpawn2 = new PokeSpawn(new Transform(4, -2), tile_sheet_manager.getTileSheet("entities"));
	Entity pokeSpawn3 = new PokeSpawn(new Transform(4, -4), tile_sheet_manager.getTileSheet("entities"));
	Entity pokeSpawn4 = new PokeSpawn(new Transform(2, -4), tile_sheet_manager.getTileSheet("entities"));

	test_level_1.player = (Player) player;
	test_level_1.addEntities(pokeSpawn1, pokeSpawn2, pokeSpawn3, pokeSpawn4, pokeCenter, player);
    }

    // for camera controll
    int entIndex = 0;

    @Override
    protected void onUpdate(float delta) {
	level_manager.getCurrentLevel().ifPresent(level -> {

	    // entity camera follow
	    // if (input.isKeyPressed(Input.N1)) {
	    // entIndex++;
	    // if (entIndex >= level.getEntities().size()) {
	    // entIndex = 0;
	    // }
	    // }
	    if (player != null) {
		camera.focusOn(player, level);
	    }

	    // zoom
	    if (window.getInput().isKeyPressed(GLFW.GLFW_KEY_KP_ADD)) {
		level.zoomIn(window);
		if (player != null) {
		    camera.focusOn(player, level);
		}
	    }
	    if (window.getInput().isKeyPressed(GLFW.GLFW_KEY_KP_SUBTRACT)) {
		level.zoomOut(window);
		if (player != null) {
		    camera.focusOn(player, level);
		}
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
