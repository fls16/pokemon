package engine.gui;

import engine.Camera;
import engine.Window;
import engine.gfx.Assets;
import engine.gfx.Shader;
import engine.gfx.TileSheet;
import engine.input.Input;
import engine.math.Matrix4f;
import engine.math.Rectangle;
import engine.math.Vector2f;
import engine.math.Vector3f;

public abstract class GUIElement {

	protected TileSheet tile_sheet;
	protected Rectangle bounding_box;
	protected boolean enabled;
	protected int texture_x, texture_y;

	public GUIElement(float x, float y, float width, float height) {
		this.bounding_box = new Rectangle(x, y, width, height);
		this.enabled = true;
		this.texture_x = 0;
		this.texture_y = 0;
	}

	public void update(float delta, Window window, Input input) {
		onUpdate(delta, window, input);
	}

	public void render(Camera camera, Shader shader, Window window) {
		Vector2f position = new Vector2f(bounding_box.x + bounding_box.width / 2 - window.width / 2,
				-bounding_box.y - bounding_box.height / 2 + window.height / 2);
		Matrix4f transform = new Matrix4f();
		Matrix4f.translate(position, new Matrix4f(), transform);
		transform.scale(new Vector3f(bounding_box.width / 2, bounding_box.height / 2, 1));
		Matrix4f.mul(camera.getProjection(), transform, transform);

		shader.setUniformMatrix4f("projection", transform);
		tile_sheet.bindTile(shader, texture_x, texture_y);
		Assets.model.render();
		onRender(camera, shader, window, position);
	}

	public abstract void onUpdate(float delta, Window window, Input input);

	public abstract void onRender(Camera camera, Shader shader, Window window, Vector2f position);

	public void init(TileSheet tile_sheet) {
		this.tile_sheet = tile_sheet;
	}

	public GUIElement changeTextureCoords(int texture_x, int texture_y) {
		this.texture_x = texture_x;
		this.texture_y = texture_y;
		return this;
	}

	public TileSheet getTileSheet() {
		return tile_sheet;
	}

	public Rectangle getBounds() {
		return bounding_box;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	};

	public boolean isEnabled() {
		return enabled;
	};

}
