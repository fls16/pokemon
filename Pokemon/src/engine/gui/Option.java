package engine.gui;

import engine.Action;
import engine.Camera;
import engine.Window;
import engine.gfx.Shader;
import engine.input.Input;
import engine.math.Vector2f;

public class Option extends GUIElement {

    private String text;
    private String drawnText;

    private boolean selected = false;
    private boolean entered;
    private boolean clicked;

    private Action onReleased;

    public Option(String text, float x, float y, float width, float height) {
	super(x, y, width, height);
	this.text = text;
	onReleased = () -> {
	};
    }

    @Override
    public void onUpdate(float delta, Window window, Input input) {

	Vector2f pos = input.getCursorPos();
	if (bounding_box.contains(pos)) {
	    selected = true;
	    if (!entered) {
		entered = true;
	    }

	    if (input.isMouseButtonReleased(Input.M1)) {
		onReleased.perform();
		clicked = false;
	    }
	} else {
	    entered = false;
	    selected = false;
	}
    }

    public void setOnAction(Action action) {
	this.onReleased = action;
    }

    @Override
    public void onRender(Camera camera, Shader shader, Window window, Vector2f position) {
	if (!text.equals("")) {
	    drawnText = selected ? " >" + text : "  " + text;
	    int max_chars = (int) (bounding_box.width / bounding_box.height);
	    Vector2f pos = new Vector2f(position.x - bounding_box.width / 2, position.y);
	    Vector2f scale = new Vector2f(bounding_box.width / 6 / max_chars, bounding_box.height / 6);
	    BitmapFont.render(drawnText, pos, scale, camera);
	}
    }

}
