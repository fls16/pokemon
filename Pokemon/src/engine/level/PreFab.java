package engine.level;

import engine.entity.Tile;

public class PreFab {

    public Tile[][][] tiles;

    public PreFab(int width, int height) {
	tiles = new Tile[width][height][2];
    }

    public PreFab() {
	// TODO Auto-generated constructor stub
    }

}
