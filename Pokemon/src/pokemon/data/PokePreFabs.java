package pokemon.data;

import java.util.HashMap;
import java.util.Map;

import engine.level.PreFab;

public class PokePreFabs {

    public enum PreFabName {
	POKECENTER
    }

    private static Map<PreFabName, PreFab> preFabs = new HashMap<>();

    static {
	initPreFabs();
    }

    private PokePreFabs() {
    }

    public static PreFab getPreFab(PreFabName name) {
	return preFabs.get(name);
    }

    private static void initPreFabs() {
	// PreFab pokecenter = new PreFab();
	// pokecenter.tiles[0][0][0] = new Tile();
	// preFabs.put(PreFabName.POKECENTER, new PreFab(width, height))
    }

}
