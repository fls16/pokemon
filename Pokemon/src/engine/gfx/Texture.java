package engine.gfx;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class Texture {

	private int id;
	private int width, height;

	public Texture(String fileName) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(fileName));
			width = image.getWidth();
			height = image.getHeight();

			int[] pixels_raw = new int[width * height];
			pixels_raw = image.getRGB(0, 0, width, height, null, 0, width);

			ByteBuffer pixels = BufferUtils.createByteBuffer(width * height * 4);

			for (int w = 0; w < width; w++) {
				for (int h = 0; h < height; h++) {
					int pixel = pixels_raw[width * w + h];
					pixels.put((byte) ((pixel >> 16) & 0xff)); // RED
					pixels.put((byte) ((pixel >> 8) & 0xff)); // GREEN
					pixels.put((byte) ((pixel >> 0) & 0xff)); // BLUE
					pixels.put((byte) ((pixel >> 24) & 0xff)); // ALPHA
				}
			}

			pixels.flip();

			id = GL11.glGenTextures();
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width, height, 0, GL11.GL_RGBA,
					GL11.GL_UNSIGNED_BYTE, pixels);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bind(int sampler) {
		if (sampler >= 0 && sampler <= 31) {
			GL13.glActiveTexture(GL13.GL_TEXTURE0 + sampler);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		GL11.glDeleteTextures(id);
		super.finalize();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
