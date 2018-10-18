package engine.level;

import java.util.ArrayList;
import java.util.List;

import engine.Camera;
import engine.Window;
import engine.entity.Entity;
import engine.gfx.Shader;
import engine.input.Input;

public class Chunk {

    public int xOffset, yOffset;
    public List<Entity> entities;
    // public Collidable[][] collidables;

    public Chunk(int xOffset, int yOffset) {
	this.xOffset = xOffset;
	this.yOffset = yOffset;
	this.entities = new ArrayList<>();
    }

    public void update(float delta, Window window, Camera camera, Level level) {
	if (window.getInput().isKeyPressed(Input.SPACE)) {
	    entities.sort((e1, e2) -> e1.compareDrawOrder(e2));
	    System.out.println(entities);
	}
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
