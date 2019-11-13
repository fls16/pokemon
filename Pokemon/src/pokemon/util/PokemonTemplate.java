package pokemon.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import pokemon.util.BasicStats.EggGroup;
import pokemon.util.BasicStats.LevelingRate;
import pokemon.util.TypeModifier.Type;

public class PokemonTemplate {

    public void init() {
	try (BufferedReader br = new BufferedReader(new FileReader("src/pokemon/data/pokemon_values.csv"))) {
	    br.readLine();
	    BasicStats pokemon_DTO = null;
	    String line;

	    while ((line = br.readLine()) != null) {
		String[] parameters = line.split(";");
		pokemon_DTO = new BasicStats();
		pokemon_DTO.id = Integer.parseInt(parameters[0]);
		pokemon_DTO.name = parameters[1];
		pokemon_DTO.type1 = Type.valueOf(parameters[2]);
		if (!parameters[3].equals("-")) {
		    pokemon_DTO.type2 = Type.valueOf(parameters[3]);
		}
		pokemon_DTO.abilityname = parameters[4];
		if (parameters[5].split(",").length == 1) {
		    pokemon_DTO.egg_group1 = EggGroup.valueOf(parameters[5]);
		} else {
		    pokemon_DTO.egg_group1 = EggGroup.valueOf(parameters[5].split(",")[0]);
		    pokemon_DTO.egg_group2 = EggGroup.valueOf(parameters[5].split(",")[1]);
		}
		pokemon_DTO.gender_rate = parameters[6];
		pokemon_DTO.capture_rate = Integer.parseInt(parameters[7]);
		pokemon_DTO.leveling_rate = LevelingRate.valueOf(parameters[8]);
		pokemon_DTO.exp_yield = Integer.parseInt(parameters[9]);
		pokemon_DTO.egg_steps = Integer.parseInt(parameters[10]);
		pokemon_DTO.height = Float.parseFloat(parameters[11]);
		pokemon_DTO.weight = Float.parseFloat(parameters[12]);
		if (parameters[13].equals("0")) {
		    pokemon_DTO.legendary = false;
		} else {
		    pokemon_DTO.legendary = true;
		}
		pokemon_DTO.base_hitpoints = Integer.parseInt(parameters[14]);
		pokemon_DTO.base_attack = Integer.parseInt(parameters[15]);
		pokemon_DTO.base_defense = Integer.parseInt(parameters[16]);
		pokemon_DTO.base_special_attack = Integer.parseInt(parameters[17]);
		pokemon_DTO.base_special_defense = Integer.parseInt(parameters[18]);
		pokemon_DTO.base_speed = Integer.parseInt(parameters[19]);
		pokemon_DTO.hitpoints_yield = Integer.parseInt(parameters[20]);
		pokemon_DTO.attack_yield = Integer.parseInt(parameters[21]);
		pokemon_DTO.defense_yield = Integer.parseInt(parameters[22]);
		pokemon_DTO.special_attack_yield = Integer.parseInt(parameters[23]);
		pokemon_DTO.special_defense_yield = Integer.parseInt(parameters[24]);
		pokemon_DTO.speed_yield = Integer.parseInt(parameters[25]);

		GlobalData.pokemon_map.put(pokemon_DTO.id, pokemon_DTO);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
