package engine.level;

import java.util.ArrayList;
import java.util.List;

import engine.Camera;
import engine.Window;
import engine.entity.AABB;
import engine.entity.Entity;
import engine.entity.EntityMover;
import engine.entity.Tile;
import engine.entity.TileRenderer;
import engine.gfx.Shader;
import engine.math.Matrix4f;
import engine.math.Vector2f;
import engine.math.Vector3f;

public class Level {

    private final static TileRenderer tile_renderer = new TileRenderer();
    private final static int default_scale = 32;

    private String name;

    private int viewX, viewY;
    private short[][] primaryTiles;
    private short[][] secondaryTiles;
    private AABB[][] bounding_boxes;
    private List<Entity> entities;
    private int width, height;
    private int scale;
    private Matrix4f world;

    public Level(String name, int width, int height) {
	this.name = name;
	this.width = width;
	this.height = height;
	scale = default_scale;
	primaryTiles = new short[width][height];
	secondaryTiles = new short[width][height];
	bounding_boxes = new AABB[width][height];
	entities = new ArrayList<>();
	world = new Matrix4f().setTranslation(new Vector3f());
	world.scale(new Vector3f(scale, scale, 1));
    }

    public void update(float delta, Window window, Camera camera) {
	EntityMover.update(delta);
	for (int i = 0; i < entities.size(); i++) {
	    Entity e_1 = entities.get(i);
	    e_1.update(delta, window, camera, this);

	    if (e_1.isSolid()) {
		e_1.collideWithTiles(this);
		for (int j = i + 1; j < entities.size(); j++) {
		    Entity e_2 = entities.get(j);
		    if (e_2.isSolid()) {
			e_1.collideWithEntity(e_2);
		    }
		}
	    }
	}
    }

    public void render(Shader shader, Camera camera) {
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

	float left = (-camera.getPosition().x / scale) - viewX;
	float right = (-camera.getPosition().x / scale) + viewX;
	float top = (-camera.getPosition().y / scale) + viewY;
	float bottom = (-camera.getPosition().y / scale) - viewY;

	for (int i = 0; i < entities.size(); i++) {
	    Entity e = entities.get(i);
	    float ex = e.transform.pos.x;
	    float ey = e.transform.pos.y;
	    if (ex > left && ex < right && ey < top && ey > bottom) {
		e.render(shader, camera, this);
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
	try {
	    return Tile.tiles[primaryTiles[x][y]];
	} catch (ArrayIndexOutOfBoundsException e) {
	    return null;
	}
    }

    public Tile getSecondaryTileAt(int x, int y) {
	try {
	    return Tile.tiles[secondaryTiles[x][y]];
	} catch (ArrayIndexOutOfBoundsException e) {
	    return null;
	}
    }

    public AABB getTileBoundingBoxAt(int x, int y) {
	try {
	    return bounding_boxes[x][y];
	} catch (ArrayIndexOutOfBoundsException e) {
	    return null;
	}
    }

    public void setPrimaryTile(Tile tile, int x, int y) {
	primaryTiles[x][y] = tile.getId();
	if (tile.isSolid()) {
	    bounding_boxes[x][y] = new AABB(new Vector2f(x * 2, -y * 2), new Vector2f(1, 1));
	} else {
	    bounding_boxes[x][y] = null;
	}
    }

    public void setSecondaryTile(Tile tile, int x, int y) {
	secondaryTiles[x][y] = tile.getId();
	if (tile.isSolid()) {
	    bounding_boxes[x][y] = new AABB(new Vector2f(x * 2, -y * 2), new Vector2f(1, 1));
	} else {
	    bounding_boxes[x][y] = null;
	}
    }

    public List<Entity> getEntities() {
	return entities;
    }

    public boolean addEntity(Entity e) {
	return entities.add(e);
    }

    public boolean removeEntity(Entity e) {
	return entities.remove(e);
    }

    public int getScale() {
	return scale;
    }

    public Matrix4f getWorldMatrix() {
	return world;
    }

    public void addEntities(Entity... entities) {
	for (int i = 0; i < entities.length; i++) {
	    this.entities.add(entities[i]);
	}
    }

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

}
