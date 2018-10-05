package pokemon;

import pokemon.entities.Pokemon;
import pokemon.entities.PokemonInitializer;

public class MainTest {

    public static void main(String[] args) {
	System.out.println("just a test");

	PokemonInitializer pokemonInit = new PokemonInitializer();
	pokemonInit.initPokemon();
	Pokemon pokemon = new Pokemon().create(PokemonInitializer.pokemonMap.get(1), 5);
	System.out.println(pokemon.hiddenId);
	System.out.println(pokemon.id);
	System.out.println(pokemon.name);
	System.out.println(pokemon.gender);
	Pokemon pokemon2 = new Pokemon().create(PokemonInitializer.pokemonMap.get(6), 5);
	System.out.println(pokemon2.hiddenId);
	System.out.println(pokemon2.id);
	System.out.println(pokemon2.name);
	System.out.println(pokemon2.gender);
	Pokemon pokemon3 = new Pokemon().create(PokemonInitializer.pokemonMap.get(493), 5);
	System.out.println(pokemon3.hiddenId);
	System.out.println(pokemon3.id);
	System.out.println(pokemon3.name);
	System.out.println(pokemon3.gender);
    }

}
