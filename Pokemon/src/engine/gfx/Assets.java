package engine.gfx;

public class Assets {

	public static Model model;

	public static void init() {
		float[] vertices = new float[] { //
				-1.0f, 1.0f, 0.0f, // TOP LEFT
				1.0f, 1.0f, 0.0f, // TOP RIGHT
				1.0f, -1.0f, 0.0f, // BOTTOM RIGHT
				-1.0f, -1.0f, 0.0f, // BOTTOM LEFT
		};
		float[] texture = new float[] { //
				0, 0, // TOP LEFT
				1, 0, // TOP RIGHT
				1, 1, // BOTTOM RIGHT
				0, 1, // BOTTOM LEFT
		};
		int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };
		model = new Model(vertices, texture, indices);
	}

	public static void cleanUp() {
		System.out.println("Model: " + model + " deleted");
		model = null;
	}

}
