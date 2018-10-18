package engine;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwTerminate;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import engine.editor.LevelEditor;
import engine.entity.TileRenderer;
import engine.gfx.Assets;
import engine.gfx.Shader;
import engine.gfx.TileSheetManager;
import engine.gui.BitmapFont;
import engine.gui.GUI;
import engine.gui.GUIManager;
import engine.input.Input;
import engine.level.LevelManager;

public abstract class GameEngine2D {

    private TileSheetManager tile_sheet_manager;

    private GUIManager gui_manager;
    private LevelManager level_manager;
    private LevelEditor level_editor;

    private Window window;
    private Camera camera;
    private Shader shader;

    private Settings settings;
    private Input input;

    private int UPS = 60;
    private int FPS = 60;

    public void run() {
	Logger.printMsg("Running.. " + Version.getVersion());

	init();
	loadRessources(tile_sheet_manager);
	handleGUIManager(gui_manager);
	handleLevelManager(level_manager);
	loop();

	onEnd();
	Assets.cleanUp();
	window.cleanUp();
	glfwTerminate();
	glfwSetErrorCallback(null).free();
    }

    private void update(float delta) {
	window.update();
	level_editor.update(delta, window, camera);
	level_manager.getCurrentLevel().ifPresent(level -> level.update(delta, window, camera));
	gui_manager.getCurrentGUI().ifPresent(gui -> gui.update(delta));
	onUpdate(delta);
    }

    private void render() {
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
	// GL11.glClearDepth(1.0);
	// GL11.glDepthFunc(GL11.GL_LEQUAL);
	level_manager.getCurrentLevel().ifPresent(level -> {
	    level.correctCamera(camera, window);
	    level.render(shader, camera);
	});
	gui_manager.getCurrentGUI().ifPresent(gui -> gui.render());
	onRender();
	window.swapBuffers();
    }

    private void init() {
	this.settings = new Settings();
	onSetUp(settings);

	GLFWErrorCallback.createPrint(System.err).set();
	if (!glfwInit())
	    throw new IllegalStateException("Unable to initialize GLFW");

	window = new Window(settings.width, settings.height, settings.title, settings.fullscreen, settings.vsync);
	window.createWindow();
	window.setOnResize((windowID, width, height) -> {
	    this.window.width = width;
	    this.window.height = height;
	    camera.setProjection(width, height);
	    GL11.glViewport(0, 0, width, height);
	    level_manager.calculateView(this.window);
	    gui_manager.resize(width, height);
	});

	handleWindow(window);

	GL.createCapabilities();
	GL11.glEnable(GL11.GL_BLEND);
	GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); // TRANSPARENCY
	GL11.glEnable(GL11.GL_TEXTURE_2D);
	// GL11.glEnable(GL11.GL_DEPTH_TEST);
	GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

	Assets.init();

	input = window.getInput();
	camera = window.getCamera();
	shader = new Shader("shader");

	declareTiles();

	tile_sheet_manager = new TileSheetManager();
	tile_sheet_manager.addTileSheet(BitmapFont.TILE_SHEET);
	tile_sheet_manager.addTileSheet(GUI.TILE_SHEET);
	tile_sheet_manager.addTileSheet(TileRenderer.TILE_SHEET);

	gui_manager = new GUIManager(window);
	level_manager = new LevelManager();
	level_manager.init(window);
	level_editor = new LevelEditor(gui_manager, level_manager, tile_sheet_manager, input, window);
    }

    private void loop() {
	long initialTime = System.nanoTime();
	final double timeU = 1000000000 / UPS;
	final double timeF = 1000000000 / FPS;
	double deltaU = 0, deltaF = 0;
	int frames = 0, ticks = 0;
	long timer = System.currentTimeMillis();
	boolean running = true;

	while (running) {

	    long currentTime = System.nanoTime();
	    deltaU += (currentTime - initialTime) / timeU;
	    deltaF += (currentTime - initialTime) / timeF;
	    initialTime = currentTime;

	    if (deltaU >= 1) {
		update((float) deltaU);
		ticks++;
		deltaU--;
	    }

	    if (deltaF >= 1) {
		render();
		frames++;
		deltaF--;
	    }

	    if (System.currentTimeMillis() - timer > 1000) {
		window.setTitle(window.getTitle() + " UPS: " + ticks + " FPS: " + frames);
		frames = 0;
		ticks = 0;
		timer += 1000;
	    }

	    if (input.isKeyReleased(Input.ESC)) {
		window.requestClose();
	    }
	    if (window.shouldClose()) {
		running = false;
	    }
	}
    }

    protected void showLevelEditor() {
	level_editor.show(camera);
    }

    protected void requestClose() {
	window.requestClose();
    }

    protected int getUPS() {
	return UPS;
    }

    protected int getFPS() {
	return FPS;
    }

    protected abstract void onSetUp(Settings settings);

    protected abstract void handleWindow(Window window);

    protected abstract void declareTiles();

    protected abstract void loadRessources(TileSheetManager tsm);

    protected abstract void handleGUIManager(GUIManager gui_manager);

    protected abstract void handleLevelManager(LevelManager level_manager);

    protected abstract void onRender();

    protected abstract void onUpdate(float delta);

    protected abstract void onEnd();

    protected static void launch(String[] args, GameEngine2D engine) {
	engine.run();
    }

}
