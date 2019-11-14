package pokemon.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GlobalData {

    // util stuff
    public static Random random = new Random();

    // global variables
    public static boolean day = false;
    public static Map<Integer, Route> route_map = new HashMap<>();
    public static Map<Integer, BasicStats> pokemon_map = new HashMap<>();

    // init everything
    public void init() {
	PokemonTemplate pokemon_template = new PokemonTemplate();
	pokemon_template.init();

	RouteTemplate.init();
    }

}
