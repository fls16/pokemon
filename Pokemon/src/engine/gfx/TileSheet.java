package engine.gfx;

import engine.math.Matrix4f;
import engine.math.Vector3f;

public class TileSheet {

	private Texture texture;
	private Matrix4f scale;
	private Matrix4f translation;
	private int amount_of_tiles;
	private int tile_size;
	private int grid_size;

	public TileSheet(String fileName, int amount_of_tiles) {
		texture = new Texture("sheets/" + fileName + ".png");
		scale = new Matrix4f().scale(1.0f / (float) amount_of_tiles);
		translation = new Matrix4f();
		this.amount_of_tiles = amount_of_tiles;
		tile_size = texture.getWidth() / amount_of_tiles;
		grid_size = texture.getWidth();
	}

	public void bindTile(Shader shader, int x, int y) {
		Matrix4f temp = new Matrix4f().translate(new Vector3f(x, y, 0));
		Matrix4f.mul(scale, temp, translation);
		shader.setUniform1i("sampler", 0);
		shader.setUniformMatrix4f("texModifier", translation);
		texture.bind(0);
	}

	public int getTileSize() {
		return tile_size;
	}

	public int getGridSize() {
		return grid_size;
	}

	public int getAmountOfTiles() {
		return amount_of_tiles;
	}

}
