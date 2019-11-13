package pokemon.entities;

import java.util.List;

public class Trainer {

    private static long idCounter = 0;

    public long hiddenId;
    public String name;
    public int money;
    public String start_message;
    public String end_message;
    public List<Pokemon> pokemon_list;
    public Pokemon pokemon_0;
    public Pokemon pokemon_1;
    public Pokemon pokemon_2;
    public Pokemon pokemon_3;
    public Pokemon pokemon_4;
    public Pokemon pokemon_5;
    public Item item;

    public Trainer() {
    }

    public static Trainer create(String name, int money, String start_message, String end_message, Pokemon pokemon_0,
	    Pokemon pokemon_1, Pokemon pokemon_2, Pokemon pokemon_3, Pokemon pokemon_4, Pokemon pokemon_5, Item item) {
	Trainer trainer = new Trainer();

	trainer.hiddenId = idCounter++;
	trainer.name = name;
	trainer.money = money;
	trainer.start_message = start_message;
	trainer.end_message = end_message;
	if (pokemon_0 != null) {
	    trainer.pokemon_list.add(pokemon_0);
	}
	if (pokemon_1 != null) {
	    trainer.pokemon_list.add(pokemon_1);
	}
	if (pokemon_2 != null) {
	    trainer.pokemon_list.add(pokemon_2);
	}
	if (pokemon_3 != null) {
	    trainer.pokemon_list.add(pokemon_3);
	}
	if (pokemon_4 != null) {
	    trainer.pokemon_list.add(pokemon_4);
	}
	if (pokemon_5 != null) {
	    trainer.pokemon_list.add(pokemon_5);
	}
	trainer.item = item;

	return trainer;
    }

    public Pokemon sendNextPokemon() {
	if (pokemon_list.size() != 0) {
	    return pokemon_list.get(0);
	} else {
	    return null;
	}
    }
}
