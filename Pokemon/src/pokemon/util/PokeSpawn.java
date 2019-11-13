package pokemon.util;

import engine.Camera;
import engine.Window;
import engine.entity.Entity;
import engine.entity.Transform;
import engine.gfx.Graphic;
import engine.gfx.TileSheet;
import engine.level.Level;
import pokemon.entities.Player;

public class PokeSpawn extends Entity {

    private boolean init_battle_flag = false;

    public PokeSpawn(Transform transform, TileSheet tile_sheet) {
	super(transform, tile_sheet);
    }

    @Override
    protected Graphic[] addGraphics(TileSheet tile_sheet) {
	Graphic[] graphics = new Graphic[2];

	graphics[0] = new Graphic(0, 5, tile_sheet);
	graphics[1] = new Graphic(1, 5, tile_sheet);

	return graphics;
    }

    @Override
    protected void onCollision(Entity entity) {

    }

    @Override
    public int compareDrawOrder(Entity entity) {
	return -1;
    }

    @Override
    protected void onUpdate(float delta, Window window, Camera camera, Level level) {
	if (Math.round(level.player.transform.pos.x) == Math.round(transform.pos.x)) {
	    if (Math.round(level.player.transform.pos.y) == Math.round(transform.pos.y)) {
		if (!init_battle_flag) {
		    init_battle_flag = true;
		    use_animation = 1;
		    System.out.println("init battle");
		    // battle init
		}

	    } else {
		init_battle_flag = false;
		use_animation = 0;
	    }
	} else {
	    init_battle_flag = false;
	    use_animation = 0;
	}
    }

    @Override
    public void onPlayerCall(Player player) {

    }

    @Override
    public void onPlayerCollision(Player player) {

    }

}
