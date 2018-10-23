package engine.entity;

import engine.Camera;
import engine.gfx.Assets;
import engine.gfx.Model;
import engine.gfx.Shader;
import engine.gfx.TileSheet;
import engine.math.Matrix4f;
import engine.math.Vector3f;

public class TileRenderer {

    public final static TileSheet TILE_SHEET = new TileSheet("tiles", 64);
    private Model model;

    public TileRenderer() {
	float[] vertices = new float[] { //
		-1.0f, 1.0f, 0.0f, // TOP LEFT 0
		1.0f, 1.0f, 0.0f, // TOP RIGHT 1
		1.0f, -1.0f, 0.0f, // BOTTOM RIGHT 2
		-1.0f, -1.0f, 0.0f,// BOTTOM LEFT 3
	};
	float[] texture = new float[] { 0, 0, 1, 0, 1, 1, 0, 1, };
	int[] indices = new int[] { 0, 1, 2, 2, 3, 0 };
	model = new Model(vertices, texture, indices);
    }

    private Matrix4f translation;

    public void render(Tile tile, int x, int y, Shader shader, Matrix4f world, Camera camera) {
	shader.bind();

	TILE_SHEET.bindTile(shader, tile.getTextureX(), tile.getTextureY());

	Matrix4f tile_pos = new Matrix4f().translate(new Vector3f(x * 2, y * 2, 0));
	Matrix4f target = new Matrix4f();
	Matrix4f camProjection = camera.getProjection();
	Matrix4f.mul(camera.getProjection(), world, target);
	Matrix4f.mul(target, tile_pos, target);

	// new
	// translation = new Matrix4f();
	// float m30 = left.m00 * right.m30 + left.m10 * right.m31 + left.m20 *
	// right.m32 + left.m30 * right.m33;
	// float m31 = left.m01 * right.m30 + left.m11 * right.m31 + left.m21 *
	// right.m32 + left.m31 * right.m33;
	// translation.m30 = camera.getProjection().m00 * world.m30 +
	// camera.getProjection().m30;
	// translation.m30 = translation.m00 * (x * 2) + translation.m30;
	// translation.m31 = y * 2 * camera.getProjection().m11;
	// translation.m00 = camera.getProjection().m00 * world.m00;
	// translation.m11 = camera.getProjection().m11 * world.m11;

	shader.setUniform1i("sampler", 0);
	shader.setUniformMatrix4f("projection", target);

	Assets.model.render();
    }

}
