package engine.gui;

import engine.Camera;
import engine.gfx.Assets;
import engine.gfx.Shader;
import engine.gfx.TileSheet;
import engine.math.Matrix4f;
import engine.math.Vector2f;
import engine.math.Vector3f;

public class BitmapFont {

    public static final TileSheet TILE_SHEET = new TileSheet("font", 16);
    private static Shader shader = new Shader("gui");

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

	    TILE_SHEET.bindTile(shader, cellX, cellY);
	    Assets.model.render();
	}

    }

}
