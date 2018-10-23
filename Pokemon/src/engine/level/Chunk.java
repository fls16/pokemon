package engine.level;

import java.util.ArrayList;
import java.util.List;

import engine.Camera;
import engine.Window;
import engine.entity.Entity;
import engine.entity.Tile;
import engine.entity.TileRenderer;
import engine.gfx.Shader;
import engine.input.Input;

public class Chunk {

    public int xOffset, yOffset;
    public List<Entity> entities;
    public Tile[][] tiles;
    private int chunkScale;
    // private short[][] primaryTiles;
    // public Collidable[][] collidables;

    public Chunk(int xOffset, int yOffset, int chunkScale) {
	this.xOffset = xOffset;
	this.yOffset = yOffset;
	this.chunkScale = chunkScale;
	this.entities = new ArrayList<>();
	tiles = new Tile[chunkScale][chunkScale];
	for (int x = 0; x < chunkScale; x++) {
	    for (int y = 0; y < chunkScale; y++) {
		tiles[x][y] = Tile.tiles[0];
	    }
	}
    }

    public void update(float delta, Window window, Camera camera, Level level) {
	if (window.getInput().isKeyPressed(Input.SPACE)) {
	    entities.sort((e1, e2) -> e1.compareDrawOrder(e2));
	    System.out.println(entities);
	}
	entities.forEach(e -> e.update(delta, window, camera, level));
    }

    public void render(int camX, int camY, Shader shader, Camera camera, Level level) {
	// for (int w = 0; w < chunkScale; w++) {
	// for (int h = 0; h < chunkScale; h++) {
	// int posX = w * (xOffset + 1) + level.camX - (chunkScale / 2) + 1;
	// int posY = h * (yOffset + 1) + level.camY - (chunkScale / 2);
	// // tempPrimaryTile = getPrimaryTileAt(posX, posY);
	// if (tiles[w][h] != null) {
	// Level.tile_renderer.render(tiles[w][h], posX, -posY, shader,
	// level.getWorldMatrix(), camera);
	// // tempSecondaryTile = getSecondaryTileAt(posX, posY);
	// // if (tempSecondaryTile != null) {
	// // tile_renderer.render(tempSecondaryTile, posX, -posY, shader, world,
	// camera);
	// // }
	// }
	// }
	// }
	for (int x = 0; x < chunkScale; x++) {
	    for (int y = 0; y < chunkScale; y++) {
		// int posX = x + camX - chunkScale / 2 - 1;
		// int posY = y + camY - chunkScale / 2;
		tiles[x][y].render((xOffset * chunkScale) + x, (-yOffset * chunkScale) + -y, shader,
			TileRenderer.TILE_SHEET, camera, level.getWorldMatrix());
	    }
	}
	entities.forEach(e -> e.render(shader, camera, level));
    }

    public boolean addEntity(Entity entity) {
	return entities.add(entity);
    }

    public Entity getEntityAt(int x, int y) {
	for (Entity e : entities) {
	    if (e.bounding_box.contains(x, y)) {
		return e;
	    }
	}
	return null;
    }

    @Override
    public String toString() {
	return xOffset + "|" + yOffset;
    }

}
