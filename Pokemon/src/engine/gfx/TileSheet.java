package engine.gfx;

import engine.math.Matrix4f;

public class TileSheet {

    private String name;
    private Texture texture;
    private int amount_of_tiles;
    private int tile_size;
    private int grid_size;
    private Matrix4f temp;
    private float defaultScale;

    public TileSheet(String name, int amount_of_tiles) {
	this.name = name;
	this.texture = new Texture("sheets/" + name + ".png");
	this.amount_of_tiles = amount_of_tiles;
	this.tile_size = texture.getWidth() / amount_of_tiles;
	this.grid_size = texture.getWidth();
	this.defaultScale = (float) tile_size / grid_size;
	this.temp = new Matrix4f();
    }

    public void bindTile(Shader shader, int x, int y) {
	temp.m00 = defaultScale;
	temp.m11 = defaultScale;
	temp.m30 = x * defaultScale;
	temp.m31 = y * defaultScale;
	shader.setUniform1i("sampler", 0);
	shader.setUniformMatrix4f("texModifier", temp);
	texture.bind(0);
    }

    public void bindTile(Shader shader, int x, int y, int width, int height) {
	temp.m00 = (float) (width * tile_size) / texture.getWidth();
	temp.m11 = (float) (height * tile_size) / texture.getHeight();
	temp.m30 = x * temp.m00;
	temp.m31 = y * temp.m11;
	shader.setUniform1i("sampler", 0);
	shader.setUniformMatrix4f("texModifier", temp);
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

    public String getName() {
	return name;
    }

}
