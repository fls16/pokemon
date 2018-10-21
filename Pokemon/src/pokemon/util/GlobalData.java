package pokemon.util;

import static java.util.Arrays.asList;

import java.util.List;
import java.util.Random;

public class GlobalData {

    // util stuff
    public static Random random = new Random();

    public static class Encounter {
	public int pokemon;
	public int level;
	public int chance;

	private Encounter(Integer pokemon, Integer level, Integer chance) {
	    this.pokemon = pokemon;
	    this.level = level;
	    this.chance = chance;
	};
    };

    // pokemon encounter list
    public static List<Encounter> region1_1 = asList(new Encounter(1, 5, 24), new Encounter(4, 6, 49),
	    new Encounter(7, 5, 99));
}
