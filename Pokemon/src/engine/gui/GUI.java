package engine.gui;

import java.util.ArrayList;
import java.util.List;

import engine.Camera;
import engine.Window;
import engine.gfx.Shader;
import engine.gfx.TileSheet;
import engine.input.Input;

public class GUI {

	private static Shader shader = new Shader("gui");
	public static final TileSheet TILE_SHEET = new TileSheet("gui", 16);

	private Window window;
	private Camera camera;
	private Input input;

	private List<GUIElement> gui_elements;

	public GUI(Window window) {
		this.window = window;
		camera = new Camera(window.width, window.height);
		input = window.getInput();
		gui_elements = new ArrayList<>();
	}

	public void resize(int width, int height) {
		// camera.setProjection(width, height);
	}

	public void update(float delta) {
		for (int i = 0; i < gui_elements.size(); i++) {
			GUIElement e = gui_elements.get(i);
			if (e.isEnabled()) {
				e.update(delta, window, input);
			}
		}
	}

	public void render() {
		shader.bind();
		for (int i = 0; i < gui_elements.size(); i++) {
			GUIElement e = gui_elements.get(i);
			if (e.isEnabled()) {
				e.render(camera, shader, window);
			}
		}
	}

	public boolean removeElement(GUIElement e) {
		return gui_elements.remove(e);
	}

	public GUIElement getElement(int i) {
		return gui_elements.get(i);
	}

	public Camera getCamera() {
		return camera;
	}

	public boolean addElement(GUIElement e) {
		if (e.getTileSheet() == null)
			e.init(TILE_SHEET);
		return gui_elements.add(e);
	}

	public void addElements(GUIElement... elements) {
		for (int i = 0; i < elements.length; i++) {
			if (elements[i].getTileSheet() == null)
				elements[i].init(TILE_SHEET);
			gui_elements.add(elements[i]);
		}
	}

}
