package engine.entity;

import engine.Camera;
import engine.gfx.Assets;
import engine.gfx.Shader;
import engine.gfx.TileSheet;
import engine.math.Matrix4f;
import engine.math.Vector3f;

public class Tile {

    public static Tile[] tiles = new Tile[Short.MAX_VALUE];
    public static short number_of_tiles = 0;
    // public Model model;
    // private final static Tile default_tile = new Tile(0, 0);

    private short id;
    private int texture_x, texture_y;

    private boolean solid;

    public Tile() {
	// model = Assets.get(DrawOrder.LOW);
    }

    public Tile(int texture_x, int texture_y) {
	this.id = number_of_tiles++;
	this.solid = false;
	if (tiles[id] != null) {
	    throw new IllegalArgumentException("Tiles at {" + id + "} is already being used!");
	}
	tiles[id] = this;
	this.texture_x = texture_x;
	this.texture_y = texture_y;
	// model = Assets.get(DrawOrder.LOW);
    }

    public void render(int x, int y, Shader shader, TileSheet tileSheet, Camera camera, Matrix4f world) {
	shader.bind();

	tileSheet.bindTile(shader, texture_x, texture_y);

	Matrix4f tile_pos = new Matrix4f().translate(new Vector3f(x * 2, y * 2, 0));
	Matrix4f target = new Matrix4f();
	Matrix4f.mul(camera.getProjection(), world, target);
	Matrix4f.mul(target, tile_pos, target);

	// graphics[use_animation].render(shader);
	shader.setUniform1i("sampler", 0);
	shader.setUniformMatrix4f("projection", target);

	Assets.model.render();
    }

    public boolean isSolid() {
	return solid;
    }

    public Tile setSolid(boolean solid) {
	this.solid = solid;
	return this;
    }

    public short getId() {
	return id;
    }

    public int getTextureX() {
	return texture_x;
    }

    public int getTextureY() {
	return texture_y;
    }

}
