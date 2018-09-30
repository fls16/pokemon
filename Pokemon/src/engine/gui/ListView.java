package engine.gui;

import java.util.ArrayList;
import java.util.List;

import engine.Camera;
import engine.Window;
import engine.gfx.Shader;
import engine.gfx.TileSheet;
import engine.input.Input;
import engine.math.Vector2f;

public class ListView extends GUIElement {

	private List<GUIElement> elements;
	private VertSliderBar slider;
	private float element_width, element_height;
	private float spacing;

	public ListView(float x, float y, float width, float height) {
		super(x, y, width, height);
		this.elements = new ArrayList<>();
		this.slider = new VertSliderBar(x + width - 32, y, 32, height);
		this.slider.setValue(0);
		this.element_width = 48.0f;
		this.element_height = 48.0f;
		this.spacing = 1.2f;
	}

	@Override
	public void onUpdate(float delta, Window window, Input input) {
		slider.update(delta, window, input);
		for (int i = 0; i < elements.size(); i++) {
			GUIElement e = elements.get(i);
			e.update(delta, window, input);
		}
	}

	@Override
	public void onRender(Camera camera, Shader shader, Window window, Vector2f position) {
		slider.render(camera, shader, window);

		int row_amount = (int) ((bounding_box.width - slider.bounding_box.width) / (element_width * spacing));
		int column_length = elements.size() / row_amount + 1;
		int counter = 0;
		for (int y = 0; y < column_length; y++) {
			for (int x = 0; x < row_amount; x++) {
				if (counter < elements.size()) {
					GUIElement e = elements.get(counter++);
					float dy = bounding_box.y + element_height * spacing * y
							- (bounding_box.height * slider.getValue());
					if (dy >= bounding_box.y && dy < bounding_box.y + bounding_box.height - element_height) {
						e.bounding_box.x = bounding_box.x + element_width * spacing * x;
						e.bounding_box.y = dy;
						e.render(camera, shader, window);
					}
				}
			}
		}
		counter = 0;
	}

	@Override
	public void init(TileSheet tile_sheet) {
		super.init(tile_sheet);
		slider.init(tile_sheet);
	}

	public boolean addElement(GUIElement element) {
		element.bounding_box.width = element_width;
		element.bounding_box.height = element_height;
		return elements.add(element);
	}

}
