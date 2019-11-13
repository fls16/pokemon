package pokemon.entities;

import java.util.Arrays;
import java.util.List;

import pokemon.dto.BattleInfoDTO;
import pokemon.util.Ability;
import pokemon.util.AbilityMap;
import pokemon.util.BasicStats;
import pokemon.util.GlobalData;
import pokemon.util.NatureModifier;
import pokemon.util.NatureModifier.Nature;

public class Pokemon {

    public enum Gender {
	M, W, N
    }

    // util
    private static long id_counter = 0;
    public BasicStats basic_stats;

    // specific stats
    public long hidden_id;
    public String nickname;
    public int level;
    public int current_exp;
    public int current_hitpoints;
    public Nature nature;
    public Ability ability;
    public Gender gender;
    public int friendship = 70;
    public int evolve_type;

    // calculated stats
    public int max_hitpoints;
    public int attack;
    public int defense;
    public int special_attack;
    public int special_defense;
    public int speed;

    // iv's
    public int iv_hitpoints = GlobalData.random.nextInt(32);
    public int iv_attack = GlobalData.random.nextInt(32);
    public int iv_defense = GlobalData.random.nextInt(32);
    public int iv_special_attack = GlobalData.random.nextInt(32);
    public int iv_special_defense = GlobalData.random.nextInt(32);
    public int iv_speed = GlobalData.random.nextInt(32);

    // ev's
    public int ev_hitpoints = 0;
    public int ev_attack = 0;
    public int ev_defense = 0;
    public int ev_special_attack = 0;
    public int ev_special_defense = 0;
    public int ev_speed = 0;

    public Pokemon() {
    }

    public static Pokemon create(BasicStats basic_stats, int level) {
	Pokemon pokemon = new Pokemon();

	pokemon.basic_stats = basic_stats;
	pokemon.hidden_id = id_counter++;
	pokemon.nickname = basic_stats.name;
	pokemon.level = level;

	pokemon.setNature();
	pokemon.setAbility(basic_stats.abilityname);
	pokemon.setGender(basic_stats.gender_rate);
	pokemon.calculateStats();

	return pokemon;
    }

    public void setAbility(String abilityname) {
	if (abilityname.split(",").length == 1) {
	    ability = AbilityMap.abilities.get(abilityname);
	} else {
	    if (GlobalData.random.nextInt(100) < 50) {
		ability = AbilityMap.abilities.get(abilityname.split(",")[0]);
	    } else {
		ability = AbilityMap.abilities.get(abilityname.split(",")[1]);
	    }
	}
    }

    private void setNature() {
	List<Nature> natures = Arrays.asList(NatureModifier.Nature.values());
	nature = natures.get(GlobalData.random.nextInt(25));
    }

    private void setGender(String gender_rate) {
	if (gender_rate.equals("-")) {
	    gender = Gender.N;
	} else {
	    float gender_ratio = Float.parseFloat(gender_rate);
	    if (GlobalData.random.nextFloat() * 100 < gender_ratio) {
		gender = Gender.M;
	    } else {
		gender = Gender.W;
	    }
	}
    }

    public void calculateStats() {
	int old_max_hitpoints = max_hitpoints;

	max_hitpoints = (int) (((int) ((((2 * basic_stats.base_hitpoints) + iv_hitpoints + ((int) (ev_hitpoints / 4)))
		* level) / 100)) + level + 10);

	current_hitpoints = current_hitpoints + max_hitpoints - old_max_hitpoints;

	attack = (int) ((((int) ((((2 * basic_stats.base_attack) + iv_attack + ((int) (ev_attack / 4))) * level) / 100))
		+ 5) * NatureModifier.attackModifier(nature));

	defense = (int) ((((int) ((((2 * basic_stats.base_defense) + iv_defense + ((int) (ev_defense / 4))) * level)
		/ 100)) + 5) * NatureModifier.defenseModifier(nature));

	special_attack = (int) ((((int) ((((2 * basic_stats.base_special_attack) + iv_special_attack
		+ ((int) (ev_special_attack / 4))) * level) / 100)) + 5)
		* NatureModifier.specialAttackModifier(nature));

	special_defense = (int) ((((int) ((((2 * basic_stats.base_special_defense) + iv_special_defense
		+ ((int) (ev_special_defense / 4))) * level) / 100)) + 5)
		* NatureModifier.specialDefenseModifier(nature));

	speed = (int) ((((int) ((((2 * basic_stats.base_speed) + iv_speed + ((int) (ev_speed / 4))) * level) / 100))
		+ 5) * NatureModifier.speedModifier(nature));
    }

    public void onDamageCalculation(BattleInfoDTO battle_info_DTO) {
	ability.onDamageCalculation(battle_info_DTO);
    }

    public void createEgg() {
	// TO-DO
	// ...
	// egg_steps -= GlobalData.random.nextInt(301);
    }

    public void gainExp(BattleInfoDTO battle_info_DTO) {
	// this is a great example for using a recursive function!!

	// int exp = battle_info_DTO.get_enemy_pokemon.exp_yield;
	// gained_exp = (exp * a * b * c) / d
	// if (current_exp + gained_exp > max_exp) {
	// levelUp();
	// current_exp2 = gained_exp + current_exp - max_exp;
	// gainExp(current_exp2);
    }

    public void levelUp() {
	level++;
	calculateStats();
    }

    public boolean triggerEvolution() { // boolean reasonable??
	if (evolve_type == 0) { // <-- can't evolve
	    return false;
	} else if (evolve_type == 1) { // <-- evolution via leveling up
	    // regular
	    // if (friendship > 220)
	    // if (holding certain item)
	    // if (knows certain move or move type)
	    // if (at certain location)
	    // if (at certain time of day)
	    return true;
	} else if (evolve_type == 2) { // <-- evolution via stone
	    // regular
	    return true;
	} else if (evolve_type == 3) { // <-- evolution via trade
	    // regular
	    // if (holding certain item)
	    return true;
	} else if (evolve_type == 4) { // <-- miscellaneous (e.g. Shedinja)
	    // ?
	    return true;
	}
	return false;
    }

    @Override
    public String toString() {
	return "Pokemon [hidden_id=" + hidden_id + ", nickname=" + nickname + ", level=" + level + ", current_exp="
		+ current_exp + ", current_hitpoints=" + current_hitpoints + ", nature=" + nature + ", ability="
		+ ability.getName() + ", gender=" + gender + ", friendship=" + friendship + ", evolve_type="
		+ evolve_type + ", max_hitpoints=" + max_hitpoints + ", attack=" + attack + ", defense=" + defense
		+ ", special_attack=" + special_attack + ", special_defense=" + special_defense + ", speed=" + speed
		+ ", iv_hitpoints=" + iv_hitpoints + ", iv_attack=" + iv_attack + ", iv_defense=" + iv_defense
		+ ", iv_special_attack=" + iv_special_attack + ", iv_special_defense=" + iv_special_defense
		+ ", iv_speed=" + iv_speed + ", ev_hitpoints=" + ev_hitpoints + ", ev_attack=" + ev_attack
		+ ", ev_defense=" + ev_defense + ", ev_special_attack=" + ev_special_attack + ", ev_special_defense="
		+ ev_special_defense + ", ev_speed=" + ev_speed + "]" + basic_stats.toString();
    }

}
