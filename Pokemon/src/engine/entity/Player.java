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

	public Player(Transform transform, TileSheet tile_sheet) {
		super(transform, tile_sheet);
	}

	@Override
	protected void onUpdate(float delta, Window window, Camera camera, Level world) {
		Vector2f movement = new Vector2f();

		speed = window.getInput().isKeyDown(Input.L_SHIFT) ? 10 * 2 : 10;

		if (window.getInput().isKeyDown(Input.S)) {
			movement.add(0.0f, -speed * 1.0f / 60.0f);
		}
		if (window.getInput().isKeyDown(Input.W)) {
			movement.add(0.0f, speed * 1.0f / 60.0f);
		}
		if (window.getInput().isKeyDown(Input.D)) {
			movement.add(speed * 1.0f / 60.0f, 0.0f);
		}
		if (window.getInput().isKeyDown(Input.A)) {
			movement.add(-speed * 1.0f / 60.0f, 0.0f);
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
