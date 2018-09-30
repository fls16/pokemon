package engine;

import java.util.HashMap;
import java.util.Map;

public class Logger {

	private final static Logger logger = new Logger();

	private Map<String, Float> values;

	private Logger() {
		values = new HashMap<>();
	}

	public static void print(String message) {
		logger.printMessage(message);
	}

	public static void addValue(String key, float value) {
		logger.values.put(key, value);
	}

	public static float getValue(String key) {
		return logger.values.get(key);
	}

	private void printMessage(String message) {
		System.out.println(message);
	}

}
