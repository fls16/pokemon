package engine.gui;

import engine.Camera;
import engine.Window;
import engine.gfx.Shader;
import engine.input.Input;
import engine.math.Vector2f;

public class Label extends GUIElement {

    public String text;
    protected String visible_text;

    public Label(String text, float x, float y, float width, float height) {
	super(x, y, width, height);
	this.text = text == null ? text = "" : text;
    }

    @Override
    public void onUpdate(float delta, Window window, Input input) {

    }

    @Override
    public void onRender(Camera camera, Shader shader, Window window, Vector2f position) {
	if (!text.equals("")) {
	    int max_chars = (int) (bounding_box.width / bounding_box.height);
	    visible_text = text.length() > max_chars ? text.substring(0, max_chars) : text;
	    Vector2f pos = new Vector2f(position.x - bounding_box.width / 2, position.y);
	    Vector2f scale = new Vector2f(bounding_box.width / 2 / max_chars, bounding_box.height / 2);
	    BitmapFont.render(visible_text, pos, scale, camera);
	}
    }

    public String getText() {
	return text;
    }

}
