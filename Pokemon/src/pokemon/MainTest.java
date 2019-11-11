package pokemon;

import java.util.ArrayList;
import java.util.List;

import pokemon.entities.Pokemon;
import pokemon.util.PokemonTemplate;
import pokemon.util.TypeModifier;
import pokemon.util.TypeModifier.Type;

public class MainTest {

    public static void main(String[] args) {
	PokemonTemplate pokemonTemplate = new PokemonTemplate();
	pokemonTemplate.init();
	List<Pokemon> pokemonList = new ArrayList<>();

	for (int i = 1; i < 494; i++) {
	    pokemonList.add(Pokemon.create(PokemonTemplate.pokemonMap.get(i), 5));
	}
	for (Pokemon p : pokemonList) {
	    System.out.println(p.toString());
	}

	Pokemon pokemon = Pokemon.create(PokemonTemplate.pokemonMap.get(493), 100);
	pokemon.evHitpoints = 255;
	pokemon.evAttack = 255;
	pokemon.evDefense = 255;
	pokemon.evSpecialAttack = 255;
	pokemon.evSpecialDefense = 255;
	pokemon.evSpeed = 255;
	pokemon.ivHitpoints = 31;
	pokemon.ivAttack = 31;
	pokemon.ivDefense = 31;
	pokemon.ivSpecialAttack = 31;
	pokemon.ivSpecialDefense = 31;
	pokemon.ivSpeed = 31;
	pokemon.calculateMaximumStats();

	System.out.println(pokemon.toString());

	System.out.println(TypeModifier.attackTypeModifier(Type.Fighting, Type.Rock, null));

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
