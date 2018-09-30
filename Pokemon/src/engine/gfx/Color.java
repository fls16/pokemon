package engine.gfx;

public class Color {

	public enum ColorType {
		PINK
	};

	public float r;
	public float g;
	public float b;
	public float a;

	public Color(float r, float g, float b, float a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}

	public static Color get(ColorType colorType) {
		switch (colorType) {
		case PINK:
			return new Color(0.0f, 1.0f, 0.0f, 1.0f);
		default:
			return null;
		}
	}

}
