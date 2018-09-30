package engine.math;

public class Rectangle {

	public float x, y;
	public float width, height;

	public Rectangle(float x, float y, float width, float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Vector2f getCenter() {
		return new Vector2f(x + width / 2, y + height / 2);
	}

	public boolean contains(Vector2f point) {
		if (point.x >= x && point.x <= x + width) {
			if (point.y >= y && point.y <= y + height) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "X: " + x + " Y: " + y + " W: " + width + " H: " + height;
	}

}
