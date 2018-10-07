package engine.entity;

import engine.math.Matrix4f;
import engine.math.Vector2f;
import engine.math.Vector3f;

public class Transform {

    public Vector3f pos;
    public Vector3f scale;

    public Transform() {
	pos = new Vector3f();
	scale = new Vector3f(1, 1, 1);
    }

    public Transform(float x, float y) {
	this.pos = new Vector3f(x, y, 0);
	this.scale = new Vector3f(1, 1, 1);
    }

    public Transform(float x, float y, float z) {
	this.pos = new Vector3f(x, y, z);
	this.scale = new Vector3f(1, 1, 1);
    }

    public Transform(Vector3f pos, Vector2f scale) {
	this.pos = new Vector3f(pos.x, pos.y, pos.z);
	this.scale = new Vector3f(scale.x, scale.y, 1);
    }

    public Matrix4f getProjection(Matrix4f target) {
	target.translate(pos);
	return target.scale(scale);
    }

}
