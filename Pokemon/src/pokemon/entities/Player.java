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
    public boolean can_move;
    public int direction;
    private int move_to;
    private Vector2f movement;

    public Player(Transform transform, TileSheet tile_sheet) {
	super(transform, tile_sheet);
	this.speed = 5.0f;
	this.can_move = true;
	this.direction = 2;
	this.movement = new Vector2f();
    }

    @Override
    protected void onUpdate(float delta, Window window, Camera camera, Level level) {
	Input input = window.getInput();

	if (input.isKeyPressed(Input.E) && entity_in_front != null) {
	    entity_in_front.onPlayerCall(this);
	}

	// calcEntityInFront(level);

	speed = input.isKeyDown(Input.L_SHIFT) ? 5 * 10 : 5;

	if (can_move) {
	    use_animation = direction + 4;
	    if (input.isKeyDown(Input.W)) {
		move_to = (int) Math.round(-transform.pos.y) - 2;
		can_move = false;
		use_animation = direction = 0;
	    } else if (input.isKeyDown(Input.D)) {
		move_to = (int) Math.round(transform.pos.x) + 2;
		can_move = false;
		use_animation = direction = 1;
	    } else if (input.isKeyDown(Input.S)) {
		move_to = (int) Math.round(-transform.pos.y) + 2;
		can_move = false;
		use_animation = direction = 2;
	    } else if (input.isKeyDown(Input.A)) {
		move_to = (int) Math.round(transform.pos.x) - 2;
		can_move = false;
		use_animation = direction = 3;
	    }
	    // if (!canMove && use_animation != direction) {
	    // ((Animation) graphics[use_animation]).reset();
	    // System.out.println("reset");
	    // }
	    calcEntityInFront(level);
	    if (!can_move && targetEntitySolid()) {
		entity_in_front.onPlayerCollision(this);
		can_move = true;
	    }

	} else {
	    float temp_speed = speed * 1.0f / 60.0f;
	    movement.set(0.0f, 0.0f);
	    switch (direction) {
	    case 0: // W
		if (!targetSolid(level, transform.pos.x, move_to) && -transform.pos.y > move_to)
		    movement.set(0.0f, temp_speed);
		else
		    can_move = true;
		break;
	    case 1: // D
		if (!targetSolid(level, move_to, -transform.pos.y) && transform.pos.x + temp_speed < move_to)
		    movement.set(temp_speed, 0.0f);
		else
		    can_move = true;
		break;
	    case 2: // S
		if (!targetSolid(level, transform.pos.x, move_to) && -transform.pos.y + temp_speed < move_to)
		    movement.set(0.0f, -temp_speed);
		else
		    can_move = true;
		break;
	    case 3: // A
		if (!targetSolid(level, move_to, -transform.pos.y) && transform.pos.x > move_to)
		    movement.set(-temp_speed, 0.0f);
		else
		    can_move = true;
		break;
	    }
	    move(movement, level);
	}

    }

    private Entity entity_in_front;

    private void calcEntityInFront(Level level) {
	int x = Math.round(transform.pos.x);
	int y = Math.round(-transform.pos.y);
	y = (direction == 0 ? y - 2 : y);
	y = (direction == 2 ? y + 2 : y);
	x = (direction == 1 ? x + 2 : x);
	x = (direction == 3 ? x - 2 : x);
	entity_in_front = level.getEntityAt(x, y);
    }

    private boolean targetEntitySolid() {
	if (entity_in_front != null) {
	    return entity_in_front.isSolid();
	}
	return false;
    }

    int temp_x;
    int temp_y;
    Tile temp_tile;

    private boolean targetSolid(Level level, float x, float y) {
	temp_x = Math.round(x / 2);
	temp_y = Math.round(y / 2);
	temp_tile = level.getPrimaryTileAt(temp_x, temp_y);
	if (temp_tile != null) {
	    return temp_tile.isSolid();
	} else {
	    temp_tile = level.getSecondaryTileAt(temp_x, temp_y);
	    if (temp_tile != null) {
		return temp_tile.isSolid();
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
