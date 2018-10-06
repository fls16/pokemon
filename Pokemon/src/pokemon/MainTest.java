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
    }

}
