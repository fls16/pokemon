package engine.gfx;

import org.lwjgl.opengl.GL11;

public class Pixel {

	public Color color;
	public float x, y, z;
	public static float size = 1.0f;

	public Pixel(Color color, float x, float y, float z, float size) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.z = z;
		Pixel.size = size;
	}

	public void render() {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glColor4f(color.r, color.g, color.b, color.a);
//		GL11.glVertex2f(-0.5f, 0.5f);
//		GL11.glVertex2f(0.5f, 0.5f);
//		GL11.glVertex2f(0.5f, -0.5f);
//		GL11.glVertex2f(-0.5f, -0.5f);

		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x - size, y);
		GL11.glVertex2f(x - size, y - size);
		GL11.glVertex2f(x, y - size);
		GL11.glEnd();
	}

}
