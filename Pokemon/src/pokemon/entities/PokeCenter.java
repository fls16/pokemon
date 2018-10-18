package pokemon.entities;

import engine.Camera;
import engine.Window;
import engine.entity.Entity;
import engine.entity.Transform;
import engine.gfx.Animation;
import engine.gfx.Graphic;
import engine.gfx.TileSheet;
import engine.level.Level;

public class PokeCenter extends Entity {

    public PokeCenter(Transform transform, TileSheet tile_sheet) {
	super(transform, tile_sheet);
	bounding_box.half_extend.y -= 2;
    }

    @Override
    protected Graphic[] addGraphics(TileSheet tile_sheet) {
	Graphic[] graphics = new Graphic[1];

	// running
	graphics[0] = new Animation(new int[][] { { 0, 1, 2 }, { 0, 0, 0 } }, 5, 5, tile_sheet, 4).repeat(false)
		.running(false);

	return graphics;
    }

    @Override
    protected void onUpdate(float delta, Window window, Camera camera, Level world) {

    }

    @Override
    protected void onCollision(Entity entity) {
	if (entity.getClass().equals(Player.class)) {
	    ((Animation) graphics[use_animation]).running(true);
	}
    }

    @Override
    public void onPlayerCall(Player player) {
	((Animation) graphics[use_animation]).running(true);
    }

    @Override
    public void onPlayerCollision(Player player) {
	if (player.direction == 0 && Math.round(player.transform.pos.x) == Math.round(transform.pos.x)) {
	    ((Animation) graphics[use_animation]).running(true);
	    // TEST

	}
    }

}
