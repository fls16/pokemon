package engine.entity;

import engine.Camera;
import engine.Window;
import engine.gfx.Animation;
import engine.gfx.TileSheet;
import engine.input.Input;
import engine.level.Level;
import engine.math.Vector2f;

public class Player extends Entity {

    private float speed = 5.0f;
    private boolean canMove = true;
    private int direction;
    private int moveTo;

    public Player(Transform transform, TileSheet tile_sheet) {
	super(transform, tile_sheet);
    }

    @Override
    protected void onUpdate(float delta, Window window, Camera camera, Level world) {
	Vector2f movement = new Vector2f();
	Input input = window.getInput();

	speed = input.isKeyDown(Input.L_SHIFT) ? 5 * 2 : 5;

	if (canMove) {
	    if (input.isKeyDown(Input.W)) {
		moveTo = (int) -transform.pos.y - 2;
		direction = 0;
		canMove = false;
	    }
	    if (input.isKeyDown(Input.D)) {
		moveTo = (int) transform.pos.x + 2;
		direction = 1;
		canMove = false;
	    }
	    if (input.isKeyDown(Input.S)) {
		moveTo = (int) -transform.pos.y + 2;
		direction = 2;
		canMove = false;
	    }
	    if (input.isKeyDown(Input.A)) {
		moveTo = (int) transform.pos.x - 2;
		direction = 3;
		canMove = false;
	    }

	} else {
	    switch (direction) {
	    case 0:
		if (-transform.pos.y > moveTo) {
		    movement.add(0.0f, speed * 1.0f / 60.0f);
		    transform.pos.y += 0.01f;
		} else {
		    transform.pos.y = -moveTo;
		    canMove = true;
		}
		break;
	    case 1:
		if (transform.pos.x < moveTo) {
		    movement.add(speed * 1.0f / 60.0f, 0.0f);
		} else {
		    transform.pos.x = moveTo;
		    canMove = true;
		}
		break;
	    case 2:
		if (-transform.pos.y < moveTo) {
		    movement.add(0.0f, -speed * 1.0f / 60.0f);
		} else {
		    transform.pos.y = -moveTo;
		    canMove = true;
		}
		break;
	    case 3:
		if (transform.pos.x > moveTo) {
		    movement.add(-speed * 1.0f / 60.0f, 0.0f);
		} else {
		    transform.pos.x = moveTo;
		    canMove = true;
		}
		break;
	    }
	}
	move(movement);
    }

    @Override
    protected Animation[] addAnimations(TileSheet tile_sheet) {
	Animation[] animations = new Animation[1];
	animations[0] = new Animation(2, 5, tile_sheet, 4);
	return animations;
    }

}
