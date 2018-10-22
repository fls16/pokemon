package engine.gui;

import engine.Action;
import engine.Camera;
import engine.Window;
import engine.gfx.Shader;
import engine.gfx.TileSheet;
import engine.input.Input;
import engine.math.Rectangle;
import engine.math.Vector2f;

public class Button extends Label {

    private enum State {
	IDLE, SELECTED, CLICKED
    }

    private int texture_idle_x, texture_idle_y;
    private int texture_selected_x, texture_selected_y;
    private int texture_clicked_x, texture_clicked_y;

    private State state;
    private boolean entered;
    private boolean clicked;
    private boolean down_while_entered;
    private Action onEntered;
    private Action onClicked;
    private Action onReleased;

    private boolean follow_mouse;

    public Button(String text, float x, float y, float width, float height) {
	super(text, x, y, width, height);
	this.state = State.IDLE;
	this.entered = false;
	this.clicked = false;

	this.onEntered = () -> {
	};
	this.onClicked = () -> {
	};
	this.onReleased = () -> {
	};

	texture_idle_x = 0;
	texture_idle_y = 0;
	texture_selected_x = 1;
	texture_selected_y = 0;
	texture_clicked_x = 2;
	texture_clicked_y = 0;
    }

    @Override
    public void onUpdate(float delta, Window window, Input input) {
	super.onUpdate(delta, window, input);
	Vector2f pos = input.getCursorPos();
	if (bounding_box.contains(pos)) {
	    state = State.SELECTED;
	    texture_x = texture_selected_x;
	    texture_y = texture_selected_y;
	    if (!entered && !follow_mouse) {
		onEntered.perform();
		entered = true;
		if (input.isMouseButtonDown(Input.M1)) {
		    down_while_entered = true;
		} else {
		    down_while_entered = false;
		}
	    }
	    if (input.isMouseButtonDown(Input.M1) && !down_while_entered) {
		state = State.CLICKED;
		texture_x = texture_clicked_x;
		texture_y = texture_clicked_y;
		if (!clicked) {
		    onClicked.perform();
		    clicked = true;
		}
	    }

	    if (input.isMouseButtonReleased(Input.M1)) {
		onReleased.perform();
		clicked = false;
	    }
	} else {
	    state = State.IDLE;
	    texture_x = texture_idle_x;
	    texture_y = texture_idle_y;
	    entered = false;
	}

	if (follow_mouse) {
	    bounding_box.x = pos.x - bounding_box.width / 2;
	    bounding_box.y = pos.y - bounding_box.height / 2;
	}

    }

    @Override
    public void onRender(Camera camera, Shader shader, Window window, Vector2f position) {
	super.onRender(camera, shader, window, position);
    }

    public void setFollowMouse(boolean follow_mouse) {
	this.follow_mouse = follow_mouse;
    }

    public State getState() {
	return state;
    }

    public void changeAllTextures(int x, int y) {
	texture_idle_x = x;
	texture_idle_y = y;
	texture_selected_x = x;
	texture_selected_y = y;
	texture_clicked_x = x;
	texture_clicked_y = y;
    }

    public void changeIdleTexture(int x, int y) {
	texture_idle_x = x;
	texture_idle_y = y;
    }

    public void changeSelectedTexture(int x, int y) {
	texture_selected_x = x;
	texture_selected_y = y;
    }

    public void changeClickedTexture(int x, int y) {
	texture_clicked_x = x;
	texture_clicked_y = y;
    }

    public void setOnEntered(Action onEntered) {
	this.onEntered = onEntered;
    }

    public void setOnClicked(Action onClicked) {
	this.onClicked = onClicked;
    }

    public void setOnReleased(Action onReleased) {
	this.onReleased = onReleased;
    }

    public void changeTileSheet(TileSheet tile_sheet) {
	this.tile_sheet = tile_sheet;
    }

    public void setBoundingBox(Rectangle bounding_box) {
	this.bounding_box = bounding_box;
    }
}
