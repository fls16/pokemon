package engine.gfx;

import java.util.HashMap;
import java.util.Map;

import engine.Logger;

public class Assets {

    public enum DrawOrder {
	MIN, VERY_LOW, LOW, NORMAL, HIGH, VERY_HIGH, GUI, GUI_TXT, MAX
    }

    public static Model model;

    private final static Map<DrawOrder, Model> models = new HashMap<>();

    public static void init() {
	float[] texture = new float[] { //
		0, 0, // TOP LEFT
		1, 0, // TOP RIGHT
		1, 1, // BOTTOM RIGHT
		0, 1, // BOTTOM LEFT
	};
	int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };
	model = new Model(getVertices(0.0f), texture, indices);
	models.put(DrawOrder.MIN, new Model(getVertices(0.0f), texture, indices));
	models.put(DrawOrder.VERY_LOW, new Model(getVertices(0.1f), texture, indices));
	models.put(DrawOrder.LOW, new Model(getVertices(0.2f), texture, indices));
	models.put(DrawOrder.NORMAL, new Model(getVertices(0.3f), texture, indices));
	models.put(DrawOrder.HIGH, new Model(getVertices(0.4f), texture, indices));
	models.put(DrawOrder.VERY_HIGH, new Model(getVertices(0.5f), texture, indices));
	models.put(DrawOrder.GUI, new Model(getVertices(0.6f), texture, indices));
	models.put(DrawOrder.GUI_TXT, new Model(getVertices(0.7f), texture, indices));
	models.put(DrawOrder.MAX, new Model(getVertices(1.0f), texture, indices));
	Logger.printMsg(models.toString());
    }

    private static float[] getVertices(float z) {
	float[] vertices = new float[] { //
		-1.0f, 1.0f, z, // TOP LEFT
		1.0f, 1.0f, z, // TOP RIGHT
		1.0f, -1.0f, z, // BOTTOM RIGHT
		-1.0f, -1.0f, z, // BOTTOM LEFT
	};
	return vertices;
    }

    public static Model gets(DrawOrder drawOrder) {
	return models.get(drawOrder);
    }

    public static void cleanUp() {
	System.out.println("Model: " + models + " deleted");
	models.clear();
    }

}
