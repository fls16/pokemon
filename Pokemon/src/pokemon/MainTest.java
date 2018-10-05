package pokemon;

import java.util.ArrayList;
import java.util.List;

import pokemon.entities.Pokemon;
import pokemon.entities.PokemonInitializer;

public class MainTest {

    public static void main(String[] args) {
	System.out.println("just a test");

	PokemonInitializer pokemonInit = new PokemonInitializer();
	pokemonInit.initPokemon();
	List<Pokemon> pokemonList = new ArrayList<>();
	for (int i = 1; i < 494; i++) {
	    pokemonList.add(new Pokemon().create(PokemonInitializer.pokemonMap.get(i), 5));
	}
	for (Pokemon p : pokemonList) {
	    System.out.println(p.toString());
	}
    }

}
