package pokemon.entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import pokemon.util.BasicStats;
import pokemon.util.BasicStats.EggGroup;
import pokemon.util.BasicStats.LevelingRate;
import pokemon.util.BasicStats.Type;

public class PokemonTemplate {

    public static Map<Integer, BasicStats> pokemonMap = new HashMap<>();

    public void init() {
	try (BufferedReader br = new BufferedReader(new FileReader("src/pokemon/data/pokemonvalues.csv"))) {
	    br.readLine();
	    BasicStats pokemonDTO = null;
	    String line;

	    while ((line = br.readLine()) != null) {
		String[] parameters = line.split(";");
		pokemonDTO = new BasicStats();
		pokemonDTO.id = Integer.parseInt(parameters[0]);
		pokemonDTO.name = parameters[1];
		pokemonDTO.type1 = Type.valueOf(parameters[2]);
		if (!parameters[3].equals("-")) {
		    pokemonDTO.type2 = Type.valueOf(parameters[3]);
		}
		pokemonDTO.abilityname = parameters[4];
		if (parameters[5].split(",").length == 1) {
		    pokemonDTO.eggGroup1 = EggGroup.valueOf(parameters[5]);
		} else {
		    pokemonDTO.eggGroup1 = EggGroup.valueOf(parameters[5].split(",")[0]);
		    pokemonDTO.eggGroup2 = EggGroup.valueOf(parameters[5].split(",")[1]);
		}
		pokemonDTO.genderRate = parameters[6];
		pokemonDTO.captureRate = Integer.parseInt(parameters[7]);
		pokemonDTO.levelingRate = LevelingRate.valueOf(parameters[8]);
		pokemonDTO.expYield = Integer.parseInt(parameters[9]);
		pokemonDTO.eggSteps = Integer.parseInt(parameters[10]);
		pokemonDTO.height = Float.parseFloat(parameters[11]);
		pokemonDTO.weight = Float.parseFloat(parameters[12]);
		if (parameters[13].equals("0")) {
		    pokemonDTO.legendary = false;
		} else {
		    pokemonDTO.legendary = true;
		}
		pokemonDTO.baseHitpoints = Integer.parseInt(parameters[14]);
		pokemonDTO.baseAttack = Integer.parseInt(parameters[15]);
		pokemonDTO.baseDefense = Integer.parseInt(parameters[16]);
		pokemonDTO.baseSpecialAttack = Integer.parseInt(parameters[17]);
		pokemonDTO.baseSpecialDefense = Integer.parseInt(parameters[18]);
		pokemonDTO.baseSpeed = Integer.parseInt(parameters[19]);
		pokemonDTO.hitpointsYield = Integer.parseInt(parameters[20]);
		pokemonDTO.attackYield = Integer.parseInt(parameters[21]);
		pokemonDTO.defenseYield = Integer.parseInt(parameters[22]);
		pokemonDTO.specialAttackYield = Integer.parseInt(parameters[23]);
		pokemonDTO.specialDefenseYield = Integer.parseInt(parameters[24]);
		pokemonDTO.speedYield = Integer.parseInt(parameters[25]);

		pokemonMap.put(pokemonDTO.id, pokemonDTO);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
