package engine.entity;

import engine.math.Vector2f;

public class AABB {

	private Vector2f center, half_extend;

	public AABB(Vector2f center, Vector2f half_extend) {
		this.center = center;
		this.half_extend = half_extend;
	}

	public boolean contains(Vector2f point) {
		float right = -half_extend.x;
		float left = center.x + half_extend.x;
		float top = half_extend.y;
		float bottom = center.y - half_extend.y;
		if (point.x >= right && point.x <= left) {
			if (point.y <= top && point.y >= bottom) {
				return true;
			}
		}
		return false;
	}

	public Collision getCollision(AABB box2) {
		Vector2f distance = box2.center.sub(center, new Vector2f());
		distance.x = (float) Math.abs(distance.x);
		distance.y = (float) Math.abs(distance.y);
		distance.sub(half_extend.add(box2.half_extend, new Vector2f()));
		return new Collision(distance, (distance.x < 0 && distance.y < 0));
	}

	public void correctPosition(AABB box2, Collision data) {
		Vector2f correction = box2.center.sub(center, new Vector2f());
		if (data.distance.x > data.distance.y) {
			if (correction.x > 0) {
				center.add(new Vector2f(data.distance.x, 0));
			} else {
				center.add(new Vector2f(-data.distance.x, 0));
			}
		} else {
			if (correction.y > 0) {
				center.add(new Vector2f(0, data.distance.y));
			} else {
				center.add(new Vector2f(0, -data.distance.y));
			}
		}
	}

	public Vector2f getCenter() {
		return center;
	}

	public Vector2f getHalf_extend() {
		return half_extend;
	}

	@Override
	public String toString() {
		return center + " " + half_extend;
	}

}
