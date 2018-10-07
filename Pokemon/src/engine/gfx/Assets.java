package engine.gfx;

import java.util.HashMap;
import java.util.Map;

public class Assets {

    public enum DrawOrder {
	MIN, VERY_LOW, LOW, NORMAL, HIGH, VERY_HIGH, GUI, GUI_TXT, MAX
    }

    private final static Map<DrawOrder, Model> model = new HashMap<>();

    public static void init() {
	float[] texture = new float[] { //
		0, 0, // TOP LEFT
		1, 0, // TOP RIGHT
		1, 1, // BOTTOM RIGHT
		0, 1, // BOTTOM LEFT
	};
	int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };
	model.put(DrawOrder.MIN, new Model(getVertices(0.0f), texture, indices));
	model.put(DrawOrder.VERY_LOW, new Model(getVertices(0.1f), texture, indices));
	model.put(DrawOrder.LOW, new Model(getVertices(0.2f), texture, indices));
	model.put(DrawOrder.NORMAL, new Model(getVertices(0.3f), texture, indices));
	model.put(DrawOrder.HIGH, new Model(getVertices(0.4f), texture, indices));
	model.put(DrawOrder.VERY_HIGH, new Model(getVertices(0.5f), texture, indices));
	model.put(DrawOrder.GUI, new Model(getVertices(0.6f), texture, indices));
	model.put(DrawOrder.GUI_TXT, new Model(getVertices(0.7f), texture, indices));
	model.put(DrawOrder.MAX, new Model(getVertices(1.0f), texture, indices));
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

    public static Model get(DrawOrder drawOrder) {
	return model.get(drawOrder);
    }

    public static void cleanUp() {
	System.out.println("Model: " + model + " deleted");
	model.clear();
    }

}
