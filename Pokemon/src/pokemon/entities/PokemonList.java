package pokemon.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import pokemon.entities.Pokemon.EggGroup;
import pokemon.entities.Pokemon.LevelingRate;
import pokemon.entities.Pokemon.Type;

public class PokemonList {

    Random random = new Random();
    private static Map<Integer, Pokemon> pokemonMap = new HashMap<>();

    public void initPokemon() {
	try (BufferedReader br = new BufferedReader(new FileReader("pokemonvalues.csv"))) {
	    br.readLine();
	    Pokemon pokemon = null;
	    String line;

	    while ((line = br.readLine()) != null) {
		String[] parameters = line.split(";");
		pokemon = new Pokemon();
		pokemon.id = Integer.parseInt(parameters[0]);
		pokemon.name = parameters[1];
		pokemon.type1 = Type.valueOf(parameters[2]);
		pokemon.type2 = Type.valueOf(parameters[3]);
		pokemon.abilityname = parameters[4];
		if (parameters[5].split(",").length == 1)
		    pokemon.eggGroup1 = EggGroup.valueOf(parameters[5]);
		else {
		    pokemon.eggGroup1 = EggGroup.valueOf(parameters[5].split(",")[0]);
		    pokemon.eggGroup2 = EggGroup.valueOf(parameters[5].split(",")[1]);
		}
		pokemon.genderRate = parameters[6];
		pokemon.captureRate = Integer.parseInt(parameters[7]);
		pokemon.levelingRate = LevelingRate.valueOf(parameters[8]);
		pokemon.expYield = Integer.parseInt(parameters[9]);
		pokemon.eggSteps = Integer.parseInt(parameters[10]);
		pokemon.height = Float.parseFloat(parameters[11]);
		pokemon.weight = Float.parseFloat(parameters[12]);
		if (parameters[13].equals("0"))
		    pokemon.legendary = false;
		else
		    pokemon.legendary = true;
		pokemon.baseHitpoints = Integer.parseInt(parameters[14]);
		pokemon.baseAttack = Integer.parseInt(parameters[15]);
		pokemon.baseDefense = Integer.parseInt(parameters[16]);
		pokemon.baseSpecialAttack = Integer.parseInt(parameters[17]);
		pokemon.baseSpecialDefense = Integer.parseInt(parameters[18]);
		pokemon.baseSpeed = Integer.parseInt(parameters[19]);
		pokemon.hitpointsYield = Integer.parseInt(parameters[20]);
		pokemon.attackYield = Integer.parseInt(parameters[21]);
		pokemon.defenseYield = Integer.parseInt(parameters[22]);
		pokemon.specialAttackYield = Integer.parseInt(parameters[23]);
		pokemon.specialDefenseYield = Integer.parseInt(parameters[24]);
		pokemon.speedYield = Integer.parseInt(parameters[25]);

		pokemonMap.put(pokemon.id, pokemon);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
