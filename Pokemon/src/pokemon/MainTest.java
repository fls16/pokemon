package pokemon;

import pokemon.entities.Pokemon;
import pokemon.util.GlobalData;
import pokemon.util.Route.Encounter;
import pokemon.util.Route.Weather;
import pokemon.util.TypeModifier;
import pokemon.util.TypeModifier.Type;

public class MainTest {

    public static void main(String[] args) {
	GlobalData global_data = new GlobalData();
	global_data.init();

	// Pokemon Stats
	// List<Pokemon> pokemon_list = new ArrayList<>();

	// for (int i = 1; i < 494; i++) {
	// pokemon_list.add(Pokemon.create(PokemonTemplate.pokemon_map.get(i), 5));
	// }
	// for (Pokemon p : pokemon_list) {
	// System.out.println(p.toString());
	// }

	Pokemon pokemon = Pokemon.create(GlobalData.pokemon_map.get(1), 74);
	pokemon.ev_hitpoints = 255;
	pokemon.ev_attack = 255;
	pokemon.ev_defense = 255;
	pokemon.ev_special_attack = 255;
	pokemon.ev_special_defense = 255;
	pokemon.ev_speed = 255;
	pokemon.iv_hitpoints = 31;
	pokemon.iv_attack = 31;
	pokemon.iv_defense = 31;
	pokemon.iv_special_attack = 31;
	pokemon.iv_special_defense = 31;
	pokemon.iv_speed = 31;
	pokemon.calculateStats();

	System.out.println(pokemon.toString());

	// Battle Type Multiplier
	System.out.println("Fighting Attack effectiveness against " + pokemon.basic_stats.type1 + " and "
		+ pokemon.basic_stats.type2 + ": "
		+ TypeModifier.attackTypeModifier(Type.Fighting, pokemon.basic_stats.type1, pokemon.basic_stats.type2));

	// Routes
	int counter = 0;
	int pokemon1 = 0;
	int pokemon2 = 0;
	int pokemon3 = 0;
	for (int i = 0; i < 10_000; i++) {
	    int random_pokemon = GlobalData.random.nextInt(100);
	    GlobalData.route_map.get(1).weather = Weather.CLEAR;
	    for (Encounter e : GlobalData.route_map.get(1).getEncounterList(0)) {
		counter++;
		if (random_pokemon < e.chance) {
		    // Ab hier ist "e" exakt das Pokemon, welchem man begegnen wird
		    // --> Pokemon pokemon =
		    // Pokemon.create(PokemonTemplate.pokemonMap.get(e.pokemon), e.level);
		    if (counter == 1) {
			pokemon1++;
		    }
		    if (counter == 2) {
			pokemon2++;
		    }
		    if (counter == 3) {
			pokemon3++;
		    }
		    counter = 0;
		    break;
		}
	    }
	}
	System.out.println("Pokemon 1: " + pokemon1 + " | Pokemon 2: " + pokemon2 + " | Pokemon 3: " + pokemon3);

	// System.out.println(getRandomEncounter(GlobalData.region1_1));
    }

    // private static Pokemon getRandomEncounter(List<pokemon.util.Encounter>
    // region) {
    // int pokemonNumber = GlobalData.random.nextInt(region.size());
    // Encounter encounter = region.get(pokemonNumber);
    // // return Pokemon.create(PokemonTemplate.pokemonMap.get(pokemonNumber),
    // // encounter.);
    // return null;
    // }

}
