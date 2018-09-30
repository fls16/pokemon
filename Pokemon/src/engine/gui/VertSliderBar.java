package engine.gui;

import engine.Camera;
import engine.Window;
import engine.gfx.Shader;
import engine.gfx.TileSheet;
import engine.input.Input;

public class VertSliderBar extends Button {

	private float value;
	private float max_value;
	private float min_value;

	private Button value_picker;

	public VertSliderBar(float x, float y, float width, float height) {
		super("", x, y, width, height);
		this.max_value = 1.0f;
		this.min_value = 0.0f;
		this.value = max_value / 2.0f;

		this.value_picker = new Button("", x, y + height / 2, width, width);
		this.value_picker.changeAllTextures(5, 5);

	}

	@Override
	public void update(float delta, Window window, Input input) {
		super.update(delta, window, input);
		if (bounding_box.contains(input.getCursorPos()) && input.isMouseButtonDown(Input.M1)) {
			value_picker.bounding_box.y = input.getCursorPos().y - (value_picker.bounding_box.height / 2);
			if (input.getCursorPos().y < bounding_box.y + bounding_box.width / 2)
				value_picker.bounding_box.y = bounding_box.y;

			if (input.getCursorPos().y > bounding_box.y + bounding_box.height - bounding_box.width / 2)
				value_picker.bounding_box.y = bounding_box.y + bounding_box.height - bounding_box.width;

		}
		value_picker.update(delta, window, input);
		value = -(bounding_box.y - value_picker.bounding_box.y) / (bounding_box.height - bounding_box.width);
	}

	@Override
	public void render(Camera camera, Shader shader, Window window) {
		super.render(camera, shader, window);
		value_picker.render(camera, shader, window);
	}

	@Override
	public void init(TileSheet tile_sheet) {
		super.init(tile_sheet);
		value_picker.init(tile_sheet);
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
		value_picker.bounding_box.y = bounding_box.y + value * (bounding_box.height - value_picker.bounding_box.height);
	}

	public Button getValuePicker() {
		return value_picker;
	}

}
