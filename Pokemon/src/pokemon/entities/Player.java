package pokemon.entities;

import engine.Camera;
import engine.Window;
import engine.entity.Entity;
import engine.entity.Tile;
import engine.entity.Transform;
import engine.gfx.Animation;
import engine.gfx.Graphic;
import engine.gfx.TileSheet;
import engine.input.Input;
import engine.level.Level;
import engine.math.Vector2f;

public class Player extends Entity {

    private float speed;
    public boolean canMove;
    public int direction;
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
    protected void onUpdate(float delta, Window window, Camera camera, Level level) {
	Input input = window.getInput();

	if (input.isKeyPressed(Input.E) && entityInFront != null) {
	    entityInFront.onPlayerCall(this);
	}

	// calcEntityInFront(level);

	speed = input.isKeyDown(Input.L_SHIFT) ? 5 * 10 : 5;

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
	    // if (!canMove && use_animation != direction) {
	    // ((Animation) graphics[use_animation]).reset();
	    // System.out.println("reset");
	    // }
	    calcEntityInFront(level);
	    if (!canMove && targetEntitySolid()) {
		entityInFront.onPlayerCollision(this);
		canMove = true;
	    }

	} else {
	    float tempSpeed = speed * 1.0f / 60.0f;
	    movement.set(0.0f, 0.0f);
	    switch (direction) {
	    case 0: // W
		if (!targetSolid(level, transform.pos.x, moveTo) && -transform.pos.y > moveTo)
		    movement.set(0.0f, tempSpeed);
		else
		    canMove = true;
		break;
	    case 1: // D
		if (!targetSolid(level, moveTo, -transform.pos.y) && transform.pos.x + tempSpeed < moveTo)
		    movement.set(tempSpeed, 0.0f);
		else
		    canMove = true;
		break;
	    case 2: // S
		if (!targetSolid(level, transform.pos.x, moveTo) && -transform.pos.y + tempSpeed < moveTo)
		    movement.set(0.0f, -tempSpeed);
		else
		    canMove = true;
		break;
	    case 3: // A
		if (!targetSolid(level, moveTo, -transform.pos.y) && transform.pos.x > moveTo)
		    movement.set(-tempSpeed, 0.0f);
		else
		    canMove = true;
		break;
	    }
	    move(movement, level);
	}

    }

    private Entity entityInFront;

    private void calcEntityInFront(Level level) {
	int x = Math.round(transform.pos.x);
	int y = Math.round(-transform.pos.y);
	y = (direction == 0 ? y - 2 : y);
	y = (direction == 2 ? y + 2 : y);
	x = (direction == 1 ? x + 2 : x);
	x = (direction == 3 ? x - 2 : x);
	entityInFront = level.getEntityAt(x, y);
    }

    private boolean targetEntitySolid() {
	if (entityInFront != null) {
	    return entityInFront.isSolid();
	}
	return false;
    }

    int tempX;
    int tempY;
    Tile tempT;

    private boolean targetSolid(Level level, float x, float y) {
	tempX = Math.round(x / 2);
	tempY = Math.round(y / 2);
	tempT = level.getPrimaryTileAt(tempX, tempY);
	if (tempT != null) {
	    return tempT.isSolid();
	} else {
	    tempT = level.getSecondaryTileAt(tempX, tempY);
	    if (tempT != null) {
		return tempT.isSolid();
	    }
	}
	return true;
    }

    @Override
    protected Graphic[] addGraphics(TileSheet tile_sheet) {
	Graphic[] graphics = new Graphic[8];

	// running
	graphics[0] = new Animation(new int[][] { { 0, 1, 2 }, { 3, 3, 3 } }, tile_sheet, 8);
	graphics[1] = new Animation(new int[][] { { 0, 1, 2 }, { 2, 2, 2 } }, tile_sheet, 8);
	graphics[2] = new Animation(new int[][] { { 0, 1, 2 }, { 0, 0, 0 } }, tile_sheet, 8);
	graphics[3] = new Animation(new int[][] { { 0, 1, 2 }, { 1, 1, 1 } }, tile_sheet, 8);

	// standing
	graphics[4] = new Graphic(1, 3, tile_sheet);
	graphics[5] = new Graphic(1, 2, tile_sheet);
	graphics[6] = new Graphic(1, 0, tile_sheet);
	graphics[7] = new Graphic(1, 1, tile_sheet);

	return graphics;
    }

    @Override
    protected void onCollision(Entity entity) {

    }

    @Override
    public void onPlayerCall(Player player) {

    }

    @Override
    public void onPlayerCollision(Player player) {

    }

}
