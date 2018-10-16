package engine.level;

import java.util.ArrayDeque;
import java.util.Collection;

import engine.Camera;
import engine.Window;
import engine.entity.Entity;
import engine.gfx.Shader;

public class Chunk {

    public int x, y;
    public Collection<Entity> entities;

    public Chunk(int x, int y) {
	this.x = x;
	this.y = y;
	this.entities = new ArrayDeque<>();
    }

    public void update(float delta, Window window, Camera camera, Level level) {
	entities.forEach(e -> e.update(delta, window, camera, level));
    }

    public void render(Shader shader, Camera camera, Level level) {
	entities.forEach(e -> e.render(shader, camera, level));
    }

    @Override
    public String toString() {
	return x + "|" + y;
    }

}
