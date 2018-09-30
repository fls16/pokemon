package engine.gui;

import org.lwjgl.glfw.GLFW;

import engine.Camera;
import engine.Window;
import engine.gfx.Shader;
import engine.input.Input;
import engine.math.Vector2f;

public class TextInput extends Button {

	private boolean activ;

	public TextInput(String text, float x, float y, float width, float height) {
		super(text, x, y, width, height);
		this.activ = false;
		setOnClicked(() -> activ = true);
	}

	@Override
	public void onUpdate(float delta, Window window, Input input) {
		super.onUpdate(delta, window, input);
		if (activ) {
			listenToInput(input);
		}
		if (!bounding_box.contains(input.getCursorPos()) && input.isMouseButtonDown(Input.M1)) {
			activ = false;
		}
	}

	@Override
	public void onRender(Camera camera, Shader shader, Window window, Vector2f position) {
		super.onRender(camera, shader, window, position);
	}

	private void listenToInput(Input input) {
		if (!text.isEmpty() && input.isKeyPressed(Input.BACK)) {
			text = text.substring(0, text.length() - 1);
		} else {
			for (int i = 32; i < 143; i++) {
				int key_code = i;

				key_code = i == GLFW.GLFW_KEY_Z ? GLFW.GLFW_KEY_Y : key_code;
				key_code = i == GLFW.GLFW_KEY_Y ? GLFW.GLFW_KEY_Z : key_code;
				key_code = i > 64 && i < 91 && !input.isKeyDown(Input.R_SHIFT) ? key_code += 32 : key_code;

				if (input.isKeyPressed(i)) {
					text += (char) (key_code);
				}

			}
		}
	}
}
