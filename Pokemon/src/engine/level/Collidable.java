package engine.level;

import engine.entity.Entity;

public class Collidable {

    public boolean solid;
    private OnCollision onCollision;

    public Collidable(boolean solid) {
	this.solid = solid;
	onCollision = (e1, e2) -> {
	};
    }

    public void onCollision(Entity e1, Entity e2) {
	onCollision.action(e1, e2);
    }

    public Collidable setOnCollision(OnCollision onCollision) {
	this.onCollision = onCollision;
	return this;
    }

}
