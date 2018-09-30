package engine.entity;

public class Tile {

	public static Tile[] tiles = new Tile[Short.MAX_VALUE];
	private static short number_of_tiles = 0;
//	private final static Tile default_tile = new Tile(0, 0);

	private short id;
	private int texture_x, texture_y;

	private boolean solid;

	public Tile(int texture_x, int texture_y) {
		this.id = number_of_tiles++;
		this.solid = false;
		if (tiles[id] != null) {
			throw new IllegalArgumentException("Tiles at {" + id + "} is already being used!");
		}
		tiles[id] = this;
		this.texture_x = texture_x;
		this.texture_y = texture_y;
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