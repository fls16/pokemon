package pokemon.entities;

import engine.Camera;
import engine.Window;
import engine.entity.Entity;
import engine.entity.Transform;
import engine.gfx.Graphic;
import engine.gfx.TileSheet;
import engine.level.Level;

public class PokeSpawn extends Entity {

    private boolean initBattleFlag = false;

    public PokeSpawn(Transform transform, TileSheet tile_sheet) {
	super(transform, tile_sheet);
    }

    @Override
    protected Graphic[] addGraphics(TileSheet tile_sheet) {
	Graphic[] graphics = new Graphic[1];

	graphics[0] = new Graphic(1, 1, tile_sheet);

	return graphics;
    }

    @Override
    protected void onCollision(Entity entity) {

    }

    @Override
    protected void onUpdate(float delta, Window window, Camera camera, Level level) {
	if (Math.round(level.player.transform.pos.x) == Math.round(transform.pos.x)) {
	    if (Math.round(level.player.transform.pos.y) == Math.round(transform.pos.y)) {
		if (!initBattleFlag) {
		    initBattleFlag = true;
		    System.out.println("init battle");
		    // battle init
		}

	    } else {
		initBattleFlag = false;
	    }
	} else {
	    initBattleFlag = false;
	}
    }

    @Override
    public void onPlayerCall(Player player) {

    }

    @Override
    public void onPlayerCollision(Player player) {

    }

}
