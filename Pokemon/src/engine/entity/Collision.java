package engine.entity;

import engine.math.Vector2f;

public class Collision {

	public Vector2f distance;
	public boolean intersecting;

	public Collision(Vector2f distance, boolean intersecting) {
		this.distance = distance;
		this.intersecting = intersecting;
	}

}
