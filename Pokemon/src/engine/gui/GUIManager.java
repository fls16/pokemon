package engine.gui;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import engine.Window;

public class GUIManager {

	private Map<String, GUI> guis;
	private GUI current_gui;

	public GUIManager(Window window) {
		this.guis = new HashMap<>();
		GUI main_menu = new GUI(window);
		Button welcome = new Button("Welcome!", window.width / 2 - 256 / 2, window.height / 2 - 64 / 2, 256, 64);
		main_menu.addElement(welcome);
		guis.put("main_menu", main_menu);
		setCurrentGUI("main_menu");
	}

	public void addGUI(String name, GUI gui) {
		guis.put(name, gui);
	}

	public void removeGUI(String name) {
		guis.remove(name);
	}

	public GUI getGUI(String name) {
		return guis.get(name);
	}

	public Optional<GUI> getCurrentGUI() {
		return Optional.ofNullable(current_gui);
	}

	public void setCurrentGUI(String name) {
		current_gui = guis.get(name);
	}

	public void resize(int width, int height) {
		guis.forEach((s, g) -> g.resize(width, height));
	}

}
