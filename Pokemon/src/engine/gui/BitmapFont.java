package engine.gui;

import engine.Camera;
import engine.gfx.Assets;
import engine.gfx.Assets.DrawOrder;
import engine.gfx.Shader;
import engine.gfx.TileSheet;
import engine.math.Matrix4f;
import engine.math.Vector2f;
import engine.math.Vector3f;

public class BitmapFont {

    public static final TileSheet TILE_SHEET = new TileSheet("font", 16);
    private static Shader shader = new Shader("gui");
    static {
	float[] texture = new float[] { //
		0, 0, // TOP LEFT
		1, 0, // TOP RIGHT
		1, 1, // BOTTOM RIGHT
		0, 1, // BOTTOM LEFT
	};
	int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };
	float[] vertices = new float[] { //
		-1.0f, 1.0f, 0.7f, // TOP LEFT
		1.0f, 1.0f, 0.7f, // TOP RIGHT
		1.0f, -1.0f, 0.7f, // BOTTOM RIGHT
		-1.0f, -1.0f, 0.7f, // BOTTOM LEFT
	};
	// model = new Model(vertices, texture, indices);
    }

    private BitmapFont() {
    }

    public static void render(String text, Vector2f pos, Vector2f scale, Camera camera) {
	for (int i = 0; i < text.length(); i++) {
	    int asciiCode = (int) text.charAt(i);
	    int cellX = (int) (asciiCode % (TILE_SHEET.getAmountOfTiles()));
	    cellX = cellX == 0 ? 15 : cellX - 1;
	    int cellY = ((int) (asciiCode - 1) / (TILE_SHEET.getAmountOfTiles()));

	    Vector3f charPos = new Vector3f((pos.x + scale.x * 2 * i) + scale.x, pos.y, 1);
	    Matrix4f target = new Matrix4f();
	    Matrix4f.translate(charPos, new Matrix4f(), target).scale(new Vector3f(scale.x, scale.y, 1));
	    shader.setUniformMatrix4f("projection", Matrix4f.mul(camera.getProjection(), target, new Matrix4f()));
	    Assets.get(DrawOrder.GUI).render();
	    TILE_SHEET.bindTile(shader, cellX, cellY);
	}

    }

}
