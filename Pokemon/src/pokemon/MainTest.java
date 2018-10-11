package pokemon;

import java.util.ArrayList;
import java.util.List;

import pokemon.entities.Pokemon;
import pokemon.entities.PokemonTemplate;

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
	pokemon.effortHitpoints = 255;
	pokemon.effortAttack = 255;
	pokemon.effortDefense = 255;
	pokemon.effortSpecialAttack = 255;
	pokemon.effortSpecialDefense = 255;
	pokemon.effortSpeed = 255;
	pokemon.individualHitpoints = 31;
	pokemon.individualAttack = 31;
	pokemon.individualDefense = 31;
	pokemon.individualSpecialAttack = 31;
	pokemon.individualSpecialDefense = 31;
	pokemon.individualSpeed = 31;
	pokemon.calculateMaximumStats();

	System.out.println(pokemon.toString());
    }

}
