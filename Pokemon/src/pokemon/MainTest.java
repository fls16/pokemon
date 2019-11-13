package pokemon;

import java.util.ArrayList;
import java.util.List;

import pokemon.entities.Pokemon;
import pokemon.util.PokemonTemplate;
import pokemon.util.TypeModifier;
import pokemon.util.TypeModifier.Type;

public class MainTest {

    public static void main(String[] args) {
	PokemonTemplate pokemon_template = new PokemonTemplate();
	pokemon_template.init();
	List<Pokemon> pokemon_list = new ArrayList<>();

	// for (int i = 1; i < 494; i++) {
	// pokemon_list.add(Pokemon.create(PokemonTemplate.pokemon_map.get(i), 5));
	// }
	// for (Pokemon p : pokemon_list) {
	// System.out.println(p.toString());
	// }

	Pokemon pokemon_arceus = Pokemon.create(PokemonTemplate.pokemon_map.get(493), 100);
	pokemon_arceus.ev_hitpoints = 255;
	pokemon_arceus.ev_attack = 255;
	pokemon_arceus.ev_defense = 255;
	pokemon_arceus.ev_special_attack = 255;
	pokemon_arceus.ev_special_defense = 255;
	pokemon_arceus.ev_speed = 255;
	pokemon_arceus.iv_hitpoints = 31;
	pokemon_arceus.iv_attack = 31;
	pokemon_arceus.iv_defense = 31;
	pokemon_arceus.iv_special_attack = 31;
	pokemon_arceus.iv_special_defense = 31;
	pokemon_arceus.iv_speed = 31;
	pokemon_arceus.calculateStats();

	System.out.println(pokemon_arceus.toString());

	System.out.println(TypeModifier.attackTypeModifier(Type.Fighting, pokemon_arceus.basic_stats.type1,
		pokemon_arceus.basic_stats.type2));

	System.out.println(pokemon_arceus.basic_stats.type1);
	System.out.println(pokemon_arceus.basic_stats.type2);

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
