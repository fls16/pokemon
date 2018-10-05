package pokemon.entities;

import engine.Camera;
import engine.Window;
import engine.entity.Entity;
import engine.entity.Transform;
import engine.gfx.Animation;
import engine.gfx.TileSheet;
import engine.input.Input;
import engine.level.Level;
import engine.math.Vector2f;

public class Player extends Entity {

    private float speed;
    private boolean canMove;
    private int direction;
    private int moveTo;
    private Vector2f movement;

    public Player(Transform transform, TileSheet tile_sheet) {
	super(transform, tile_sheet);
	this.speed = 5.0f;
	this.canMove = true;
	this.direction = 2;
	this.movement = new Vector2f();
    }

    @Override
    protected void onUpdate(float delta, Window window, Camera camera, Level world) {
	Input input = window.getInput();

	speed = input.isKeyDown(Input.L_SHIFT) ? 5 * 2 : 5;

	if (canMove) {
	    use_animation = direction + 4;
	    if (input.isKeyDown(Input.W)) {
		moveTo = (int) Math.round(-transform.pos.y) - 2;
		canMove = false;
		use_animation = direction = 0;
	    } else if (input.isKeyDown(Input.D)) {
		moveTo = (int) Math.round(transform.pos.x) + 2;
		canMove = false;
		use_animation = direction = 1;
	    } else if (input.isKeyDown(Input.S)) {
		moveTo = (int) Math.round(-transform.pos.y) + 2;
		canMove = false;
		use_animation = direction = 2;
	    } else if (input.isKeyDown(Input.A)) {
		moveTo = (int) Math.round(transform.pos.x) - 2;
		canMove = false;
		use_animation = direction = 3;
	    }

	} else {
	    float tempSpeed = speed * 1.0f / 60.0f;
	    switch (direction) {
	    case 0: // W
		if (-transform.pos.y - tempSpeed * 2 >= moveTo)
		    movement.set(0.0f, tempSpeed);
		else
		    canMove = true;
		break;
	    case 1: // D
		if (transform.pos.x + tempSpeed < moveTo)
		    movement.set(tempSpeed, 0.0f);
		else
		    canMove = true;
		break;
	    case 2: // S
		if (-transform.pos.y + tempSpeed < moveTo)
		    movement.set(0.0f, -tempSpeed);
		else
		    canMove = true;
		break;
	    case 3: // A
		if (transform.pos.x - tempSpeed * 2 > moveTo)
		    movement.set(-tempSpeed, 0.0f);
		else
		    canMove = true;
		break;
	    }
	    move(movement);
	}

    }

    @Override
    protected Animation[] addAnimations(TileSheet tile_sheet) {
	Animation[] animations = new Animation[8];

	// running
	animations[0] = new Animation(new int[][] { { 0, 1, 2, 3, 4 }, { 4, 4, 4, 4, 4 } }, tile_sheet, 6);
	animations[1] = new Animation(new int[][] { { 0, 1, 2, 3, 4 }, { 5, 5, 5, 5, 5 } }, tile_sheet, 6);
	animations[2] = new Animation(new int[][] { { 0, 1, 2, 3, 4 }, { 2, 2, 2, 2, 2 } }, tile_sheet, 6);
	animations[3] = new Animation(new int[][] { { 0, 1, 2, 3, 4 }, { 3, 3, 3, 3, 3 } }, tile_sheet, 6);

	// standing
	animations[4] = new Animation(new int[][] { { 0 }, { 4 } }, tile_sheet, 4);
	animations[5] = new Animation(new int[][] { { 0 }, { 5 } }, tile_sheet, 4);
	animations[6] = new Animation(new int[][] { { 0 }, { 2 } }, tile_sheet, 4);
	animations[7] = new Animation(new int[][] { { 0 }, { 3 } }, tile_sheet, 4);

	return animations;
    }

}
