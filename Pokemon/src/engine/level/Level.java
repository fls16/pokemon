package engine.level;

import java.util.ArrayDeque;
import java.util.Collection;

import engine.Camera;
import engine.Window;
import engine.entity.Entity;
import engine.entity.Tile;
import engine.entity.TileRenderer;
import engine.gfx.Shader;
import engine.math.Matrix4f;
import engine.math.Vector3f;
import pokemon.entities.Player;

public class Level {

    private final static TileRenderer tile_renderer = new TileRenderer();
    private final static int default_scale = 32;

    private String name;

    private int viewX, viewY;
    private short[][] primaryTiles;
    private short[][] secondaryTiles;
    private Collidable[][] collidables;
    private Chunk[][] chunks;
    private int chunkScale = 16;
    // private Entity[][][] entities;
    // private AABB[][] bounding_boxes;
    public Collection<Entity> globalEntities;
    private int width, height;
    private int scale;
    private Matrix4f world;

    // player
    public Player player;

    // temp variables
    private int camX, camY;
    private int posX, posY;
    private Tile tempPrimaryTile;
    private Tile tempSecondaryTile;
    // private Entity[] tempEntities;

    public Level(String name, int width, int height) {
	if (width < chunkScale * 4 && width % chunkScale != 0 && height < chunkScale * 4 && height % chunkScale != 0)
	    throw new IllegalArgumentException("Level width/height must be at least 64 and dividable by " + chunkScale);
	this.name = name;
	this.width = width;
	this.height = height;
	this.scale = default_scale;
	this.primaryTiles = new short[width][height];
	this.secondaryTiles = new short[width][height];
	this.globalEntities = new ArrayDeque<>();
	// bounding_boxes = new AABB[width][height];
	this.collidables = new Collidable[width][height];
	this.chunks = new Chunk[width / chunkScale][height / chunkScale];
	for (int x = 0; x < width / chunkScale; x++) {
	    for (int y = 0; y < height / chunkScale; y++) {
		chunks[x][y] = new Chunk(x, y);
	    }
	}
	// this.entities = new Entity[width][height][1];
	// entities = new ArrayList<>();
	world = new Matrix4f().setTranslation(new Vector3f());
	world.scale(new Vector3f(scale, scale, 1));
    }

    private int tempChunkX, tempChunkY;
    private Chunk[] activeChunks = new Chunk[9];

    public void update(float delta, Window window, Camera camera) {
	// EntityMover.update(delta);
	// for (int i = 0; i < entities.size(); i++) {
	// Entity e_1 = entities.get(i);
	// e_1.update(delta, window, camera, this);
	//
	// if (e_1.isSolid()) {
	// // e_1.collideWithTiles(this);
	// for (int j = i + 1; j < entities.size(); j++) {
	// Entity e_2 = entities.get(j);
	// if (e_2.isSolid()) {
	// e_1.collideWithEntity(e_2);
	// }
	// }
	// }
	// }

	// camera position calculation
	camX = -((int) camera.getPosition().x / (scale * 2));
	camY = (int) camera.getPosition().y / (scale * 2);

	// active chunk calculation
	activeChunks[0] = camX - chunkScale < 0 || camY - chunkScale < 0 ? // left top
		null : chunks[(camX - chunkScale) / chunkScale][(camY - chunkScale) / chunkScale];
	activeChunks[1] = camY - chunkScale < 0 ? // top center
		null : chunks[camX / chunkScale][(camY - chunkScale) / chunkScale];

	activeChunks[2] = camX + chunkScale >= width || camY - chunkScale < 0 ? // top right
		null : chunks[(camX + chunkScale) / chunkScale][(camY - chunkScale) / chunkScale];
	activeChunks[3] = camX + chunkScale >= width ? // right center
		null : chunks[(camX + chunkScale) / chunkScale][camY / chunkScale];

	activeChunks[4] = camX + chunkScale >= width || camY + chunkScale >= height ? // right bottom
		null : chunks[(camX + chunkScale) / chunkScale][(camY + chunkScale) / chunkScale];
	activeChunks[5] = camY + chunkScale >= height ? // bottom center
		null : chunks[camX / chunkScale][(camY + chunkScale) / chunkScale];

	activeChunks[6] = camX - chunkScale < 0 || camY + chunkScale >= height ? // bottom left
		null : chunks[(camX - chunkScale) / chunkScale][(camY + chunkScale) / chunkScale];
	activeChunks[7] = camX - chunkScale < 0 ? // left center
		null : chunks[(camX - chunkScale) / chunkScale][camY / chunkScale];

	activeChunks[8] = chunks[camX / chunkScale][camY / chunkScale]; // center

	for (Chunk c : activeChunks) {
	    if (c != null) {
		c.update(delta, window, camera, this);
	    }
	}
	player.update(delta, window, camera, this);
    }

    public void move(Entity entity) {

    }

    public void render(Shader shader, Camera camera) {
	for (int w = 0; w < viewX; w++) {
	    for (int h = 0; h < viewY; h++) {
		posX = w + camX - (viewX / 2) + 1;
		posY = h + camY - (viewY / 2);
		tempPrimaryTile = getPrimaryTileAt(posX, posY);
		if (tempPrimaryTile != null) {
		    tile_renderer.render(tempPrimaryTile, posX, -posY, shader, world, camera);
		    tempSecondaryTile = getSecondaryTileAt(posX, posY);
		    if (tempSecondaryTile != null) {
			tile_renderer.render(tempSecondaryTile, posX, -posY, shader, world, camera);
		    }
		}
	    }
	}
	player.render(shader, camera, this);
	for (Entity e : globalEntities) {
	    e.render(shader, camera, this);
	}
	for (Chunk c : activeChunks) {
	    if (c != null) {
		c.render(shader, camera, this);
	    }
	}

	// float left = (-camera.getPosition().x / scale) - viewX;
	// float right = (-camera.getPosition().x / scale) + viewX;
	// float top = (-camera.getPosition().y / scale) + viewY;
	// float bottom = (-camera.getPosition().y / scale) - viewY;

	// for (int i = 0; i < entities.size(); i++) {
	// Entity e = entities.get(i);
	// float ex = e.transform.pos.x;
	// float ey = e.transform.pos.y;
	// if (ex > left && ex < right && ey < top && ey > bottom) {
	// e.render(shader, camera, this);
	// }
	// }

    }

    private void oldRender(Shader shader, Camera camera) {
	int posX = (int) camera.getPosition().x / (scale * 2);
	int posY = (int) camera.getPosition().y / (scale * 2);

	for (int w = 0; w < viewX; w++) {
	    for (int h = 0; h < viewY; h++) {
		Tile t1 = getPrimaryTileAt(w - posX - (viewX / 2) + 1, h + posY - (viewY / 2));
		if (t1 != null) {
		    tile_renderer.render(t1, w - posX - (viewX / 2) + 1, -h - posY + (viewY / 2), shader, world,
			    camera);

		    // secondary tile
		    Tile t2 = getSecondaryTileAt(w - posX - (viewX / 2) + 1, h + posY - (viewY / 2));
		    tile_renderer.render(t2, w - posX - (viewX / 2) + 1, -h - posY + (viewY / 2), shader, world,
			    camera);
		}
	    }
	}
    }

    public void init(Window window) {
	calculateView(window);
	setScale(default_scale, window);
    }

    public void calculateView(Window window) {
	viewX = window.width / (scale * 2) + 4;
	viewY = window.height / (scale * 2) + 4;
    }

    public void setScale(int value, Window window) {
	if (value * width > window.width) {
	    this.scale = value;
	} else {
	    this.scale = window.width > window.height ? window.height / height : window.width / width;
	}
	calculateView(window);
	world = new Matrix4f().setTranslation(new Vector3f());
	world.scale(new Vector3f(scale, scale, 1));
    }

    public void zoomIn(Window window) {
	int value = (int) (scale * 1.3f) > 64 ? 64 : (int) (scale * 1.3f);
	setScale(value, window);
	System.out.println(scale);
    }

    public void zoomOut(Window window) {
	int value = (int) (scale / 1.3f) < 8 ? 8 : (int) (scale / 1.3f);
	setScale(value, window);
	System.out.println(scale);
    }

    public void correctCamera(Camera camera, Window window) {
	Vector3f pos = camera.getPosition();
	int w = -width * scale * 2;
	int h = height * scale * 2;

	if (pos.x > -(window.width / 2) + scale)
	    pos.x = -(window.width / 2) + scale;
	if (pos.x < w + (window.width / 2) + scale)
	    pos.x = w + (window.width / 2) + scale;
	if (pos.y < (window.height / 2) - scale)
	    pos.y = (window.height / 2) - scale;
	if (pos.y > h - (window.height / 2) - scale)
	    pos.y = h - (window.height / 2) - scale;

    }

    public Tile getPrimaryTileAt(int x, int y) {
	return (x < 0 || x >= width || y < 0 || y >= height) ? null : Tile.tiles[primaryTiles[x][y]];
    }

    public Tile getSecondaryTileAt(int x, int y) {
	return (x < 0 || x >= width || y < 0 || y >= height) ? null : Tile.tiles[secondaryTiles[x][y]];
    }

    public Entity[] getEntitiesAt(int x, int y) {
	// return (x < 0 || x > entities.length || y < 0 || y > entities[x].length) ||
	// entities[x][y][0] == null ? //
	// null : entities[x][y];
	return null;
    }

    // public AABB getTileBoundingBoxAt(int x, int y) {
    // try {
    // return bounding_boxes[x][y];
    // } catch (ArrayIndexOutOfBoundsException e) {
    // return null;
    // }
    // }

    public void setPrimaryTile(Tile tile, int x, int y) {
	primaryTiles[x][y] = tile.getId();
	collidables[x][y] = tile.isSolid() ? new Collidable(true) : null;
	// if (tile.isSolid()) {
	// bounding_boxes[x][y] = new AABB(new Vector2f(x * 2, -y * 2), new
	// Vector2f(1,
	// 1));
	// } else {
	// bounding_boxes[x][y] = null;
	// }
    }

    public void setSecondaryTile(Tile tile, int x, int y) {
	secondaryTiles[x][y] = tile.getId();
	collidables[x][y] = tile.isSolid() ? new Collidable(true) : null;
	// if (tile.isSolid()) {
	// bounding_boxes[x][y] = new AABB(new Vector2f(x * 2, -y * 2), new Vector2f(1,
	// 1));
	// } else {
	// bounding_boxes[x][y] = null;
	// }
    }

    // public List<Entity> getEntities() {
    // return entities;
    // }

    // public boolean addEntity(Entity e) {
    // return entities.add(e);
    // }

    public void addEntity(Entity entity) {
	chunks[(int) entity.transform.pos.x / chunkScale][(int) -entity.transform.pos.y / chunkScale].entities
		.add(entity);
	// entities[(int) entity.transform.pos.x][(int) -entity.transform.pos.y][0] =
	// entity;
	// collidables[(int) entity.transform.pos.x][(int) -entity.transform.pos.y] =
	// new Collidable(entity.isSolid())
	// .setOnCollision(entity.getOnCollision());

    }

    public void addEntities(Entity... entities) {
	for (Entity entity : entities) {
	    this.addEntity(entity);
	}
    }

    // public boolean removeEntity(Entity e) {
    // return entities.remove(e);
    // }

    public boolean removeEntity(Entity entity) {
	// if (entities[(int) entity.transform.pos.x][(int) entity.transform.pos.y][0]
	// == null) {
	// return false;
	// } else {
	// entities[(int) entity.transform.pos.x][(int) entity.transform.pos.y][0] =
	// null;
	// return true;
	// }
	return false;
    }

    public int getScale() {
	return scale;
    }

    public Matrix4f getWorldMatrix() {
	return world;
    }

    // public void addEntities(Entity... entities) {
    // for (int i = 0; i < entities.length; i++) {
    // this.entities.add(entities[i]);
    // }
    // }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public Entity getEntityAt(int x, int y) {
	Entity temp = null;
	for (Chunk c : activeChunks) {
	    if (c != null) {
		temp = c.getEntityAt(x, y);
		if (temp != null)
		    return temp;
	    }
	}
	return null;
    }

}
