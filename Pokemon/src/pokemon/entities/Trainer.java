package pokemon.entities;

import java.util.List;

public class Trainer {

    private static long idCounter = 0;

    public long hiddenId;
    public String name;
    public int money;
    public String startMessage;
    public String endMessage;
    public List<Pokemon> pokemonList;
    public Pokemon pokemon0;
    public Pokemon pokemon1;
    public Pokemon pokemon2;
    public Pokemon pokemon3;
    public Pokemon pokemon4;
    public Pokemon pokemon5;
    public Item item;

    public Trainer() {
    }

    public static Trainer create(String name, int money, String startMessage, String endMessage, Pokemon pokemon0,
	    Pokemon pokemon1, Pokemon pokemon2, Pokemon pokemon3, Pokemon pokemon4, Pokemon pokemon5, Item item) {
	Trainer trainer = new Trainer();

	trainer.hiddenId = idCounter++;
	trainer.name = name;
	trainer.money = money;
	trainer.startMessage = startMessage;
	trainer.endMessage = endMessage;
	if (pokemon0 != null) {
	    trainer.pokemonList.add(pokemon0);
	}
	if (pokemon1 != null) {
	    trainer.pokemonList.add(pokemon1);
	}
	if (pokemon2 != null) {
	    trainer.pokemonList.add(pokemon2);
	}
	if (pokemon3 != null) {
	    trainer.pokemonList.add(pokemon3);
	}
	if (pokemon4 != null) {
	    trainer.pokemonList.add(pokemon4);
	}
	if (pokemon5 != null) {
	    trainer.pokemonList.add(pokemon5);
	}
	trainer.item = item;

	return trainer;
    }

    public Pokemon sendNextPokemon() {
	if (pokemonList.size() != 0) {
	    return pokemonList.get(0);
	} else {
	    return null;
	}
    }
}
