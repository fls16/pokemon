package engine.level;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import engine.Window;

public class LevelManager {

	private Map<String, Level> levels;
	private Level current_level;

	public LevelManager() {
		this.levels = new HashMap<>();
	}

	public void addLevel(String name, Level level) {
		levels.put(name, level);
	}

	public void removeLevel(String name) {
		levels.remove(name);
	}

	public Level getLevel(String name) {
		return levels.get(name);
	}

	public Optional<Level> getCurrentLevel() {
		return Optional.ofNullable(current_level);
	}

	public void setCurrentLevel(String name) {
		current_level = levels.get(name);
	}

	public void calculateView(Window window) {
		levels.forEach((s, l) -> l.calculateView(window));
	}

}
