package engine.level;

import java.util.ArrayDeque;
import java.util.Collection;

import engine.Camera;
import engine.Window;
import engine.entity.Entity;
import engine.gfx.Shader;

public class Chunk {

    public int xOffset, yOffset;
    public Collection<Entity> entities;
    // public Collidable[][] collidables;

    public Chunk(int xOffset, int yOffset) {
	this.xOffset = xOffset;
	this.yOffset = yOffset;
	this.entities = new ArrayDeque<>();
    }

    public void update(float delta, Window window, Camera camera, Level level) {
	entities.forEach(e -> e.update(delta, window, camera, level));
    }

    public void render(Shader shader, Camera camera, Level level) {
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
