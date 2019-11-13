package pokemon.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GlobalData {

    // util stuff
    public static Random random = new Random();

    // global variables
    public static boolean day;
    public static Map<Integer, Region> region_map = new HashMap<>();
    public static Map<Integer, BasicStats> pokemon_map = new HashMap<>();

    // init everything
    public void init() {
	PokemonTemplate pokemon_template = new PokemonTemplate();
	pokemon_template.init();

	Region region = new Region();
	region.init();
    }

}
