package engine;

import engine.entity.Entity;
import engine.level.Level;
import engine.math.Matrix4f;
import engine.math.Vector3f;

public class Camera {

	private Vector3f position;
	private Matrix4f projection;

	public Camera(int width, int height) {
		position = new Vector3f();
		setProjection(width, height);
	}

	public void focusOn(Entity e, Level world) {
		setPosition(e.transform.pos.mul(-world.getScale(), new Vector3f()));//
	}

	public void focusOn(Entity e, Level world, float speed) {
		position.lerp(e.transform.pos.mul(-world.getScale(), new Vector3f()), speed);
	}

	public void setProjection(int width, int height) {
		projection = new Matrix4f().setOrtho2D(-width / 2, width / 2, -height / 2, height / 2);
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public void scale(float value) {
		projection.scale(new Vector3f(value, value, 0));
	}

	public void addPosition(Vector3f position) {
		Vector3f.add(this.position, position, this.position);
	}

	public Vector3f getPosition() {
		return position;
	}

	public Matrix4f getProjection() {
		Matrix4f pos = new Matrix4f().translate(position);
		return Matrix4f.mul(projection, pos, new Matrix4f());
	}

}
