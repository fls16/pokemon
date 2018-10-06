package pokemon.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import pokemon.dto.BattleInfoDTO;
import pokemon.util.Ability;
import pokemon.util.AbilityMap;
import pokemon.util.NatureModifier;
import pokemon.util.PokemonDTO;

/**
 * A Pokemon. Not much to say.
 * 
 * @author Lation
 */
public class Pokemon {

    public enum Type {
	Normal, Fighting, Flying, Poison, Ground, Rock, Bug, Ghost, Steel, Fire, Water, Grass, Electric, Psychic, Ice, Dragon, Dark
    }

    public enum Nature {
	// - Attack, - Defense, - SpAtk, - SpDef, - Speed
	// + Attack
	Hardy, Lonely, Adamant, Naughty, Brave,
	// + Defense
	Bold, Docile, Impish, Lax, Relaxed,
	// + SpAtk
	Modest, Mild, Bashful, Rash, Quiet,
	// + SpDef
	Calm, Gentle, Careful, Quirky, Sassy,
	// + Speed
	Timid, Hasty, Jolly, Naive, Serious;
    }

    public enum EggGroup {
	Monster, Water_1, Water_2, Water_3, Bug, Flying, Field, Fairy, Grass, Undiscovered, Human_Like, Mineral, Amorphous, Ditto, Dragon
    }

    public enum LevelingRate {
	Slow, Medium, Fast, MediumSlow, SlowThenFast, FastThenSlow
    }

    public enum Gender {
	M, W, N
    }

    // util
    Random random = new Random();
    private static long idCounter = 0;

    // specific stats
    public long hiddenId;
    public String nickname;
    public int level;
    public int currentExp;
    public int currentHitpoints;
    public Nature nature;
    public Ability ability;
    public Gender gender;
    public int friendship = 70;
    public int evolveType;

    // calculated stats
    public int maxhitpoints;
    public int attack;
    public int defense;
    public int specialAttack;
    public int specialDefense;
    public int speed;

    // iv's
    public int individualHitpoints = random.nextInt(32);
    public int individualAttack = random.nextInt(32);
    public int individualDefense = random.nextInt(32);
    public int individualSpecialAttack = random.nextInt(32);
    public int individualSpecialDefense = random.nextInt(32);
    public int individualSpeed = random.nextInt(32);

    // ev's
    public int effortHitpoints = 0;
    public int effortAttack = 0;
    public int effortDefense = 0;
    public int effortSpecialAttack = 0;
    public int effortSpecialDefense = 0;
    public int effortSpeed = 0;

    public PokemonDTO generalStats;

    // public int id;
    // public String name;
    // public Type type1;
    // public Type type2;
    // public String abilityname;
    // public EggGroup eggGroup1;
    // public EggGroup eggGroup2;
    // public String genderRate;
    // public int captureRate;
    // public LevelingRate levelingRate;
    // public int expYield;
    // public int eggSteps;
    // public float height;
    // public float weight;
    // public boolean legendary;
    //
    // // base stats
    // public int baseHitpoints;
    // public int baseAttack;
    // public int baseDefense;
    // public int baseSpecialAttack;
    // public int baseSpecialDefense;
    // public int baseSpeed;
    //
    //
    //
    // // iv yield
    // public int hitpointsYield;
    // public int attackYield;
    // public int defenseYield;
    // public int specialAttackYield;
    // public int specialDefenseYield;
    // public int speedYield;

    /**
     * Just an empty constructor.
     */
    private Pokemon() {
    }

    /**
     * Method to create any Pokemon at will.
     * 
     * @param pokemonDTO
     *            Template of a general pokemon of which you want to create a unique
     *            copy.
     * @param level
     *            The level the created Pokemon is going to have.
     * @return Pokemon Basically a unique Pokemon you can encounter during the game.
     */
    public Pokemon create(PokemonDTO pokemonDTO, int level) {
	Pokemon pokemon = new Pokemon();
	pokemon.generalStats = pokemonDTO;
	pokemon.hiddenId = idCounter;
	idCounter++;
	// pokemon.id = pokemonDTO.id;
	// pokemon.name = pokemonDTO.name;
	pokemon.nickname = pokemonDTO.name;
	pokemon.level = level;
	// pokemon.type1 = Type.valueOf(pokemonDTO.type1);
	if (pokemonDTO.type2 != null) {
	    // pokemon.type2 = Type.valueOf(pokemonDTO.type2);
	}
	// pokemon.eggGroup1 = EggGroup.valueOf(pokemonDTO.eggGroup1);
	if (pokemonDTO.eggGroup2 != null) {
	    // pokemon.eggGroup2 = EggGroup.valueOf(pokemonDTO.eggGroup2);
	}
	// pokemon.captureRate = pokemonDTO.captureRate;
	// pokemon.levelingRate = LevelingRate.valueOf(pokemonDTO.levelingRate);
	// pokemon.expYield = pokemonDTO.expYield;
	// pokemon.eggSteps = pokemonDTO.eggSteps;
	// pokemon.height = pokemonDTO.height;
	// pokemon.weight = pokemonDTO.weight;
	// pokemon.legendary = pokemonDTO.legendary;
	// pokemon.baseHitpoints = pokemonDTO.baseHitpoints;
	// pokemon.baseAttack = pokemonDTO.baseAttack;
	// pokemon.baseDefense = pokemonDTO.baseDefense;
	// pokemon.baseSpecialAttack = pokemonDTO.baseSpecialAttack;
	// pokemon.baseSpecialDefense = pokemonDTO.baseSpecialDefense;
	// pokemon.baseSpeed = pokemonDTO.baseSpeed;
	// pokemon.hitpointsYield = pokemonDTO.hitpointsYield;
	// pokemon.attackYield = pokemonDTO.attackYield;
	// pokemon.defenseYield = pokemonDTO.defenseYield;
	// pokemon.specialAttackYield = pokemonDTO.specialAttackYield;
	// pokemon.specialDefenseYield = pokemonDTO.specialDefenseYield;
	// pokemon.speedYield = pokemonDTO.speedYield;

	pokemon.setNature();
	pokemon.setAbility(pokemonDTO.abilityname);
	pokemon.setGender(pokemonDTO.genderRate);
	pokemon.calculateMaximumStats();

	return pokemon;
    }

    /**
     * Sets the Ability for the Pokemon when created
     * 
     * @param abilityname
     */
    public void setAbility(String abilityname) {
	if (abilityname.split(",").length == 1) {
	    ability = AbilityMap.abilities.get(abilityname);
	} else {
	    if (random.nextInt(100) < 50) {
		ability = AbilityMap.abilities.get(abilityname.split(",")[0]);
	    } else {
		ability = AbilityMap.abilities.get(abilityname.split(",")[1]);
	    }
	}
    }

    /**
     * Sets a random nature for the Pokemon when created.
     */
    private void setNature() {
	List<Nature> natures = Arrays.asList(Nature.values());
	this.nature = natures.get(random.nextInt(25));
    }

    /**
     * Randomly assigns a gender for the created Pokemon by using the genderRate.
     * 
     * @param genderRate
     *            The Float (String) probability of being male.
     */
    private void setGender(String genderRate) {
	if (genderRate.equals("-")) {
	    gender = Gender.N;
	} else {
	    float genderRatio = Float.parseFloat(genderRate);
	    if (random.nextFloat() * 100 < genderRatio) {
		gender = Gender.M;
	    } else {
		gender = Gender.W;
	    }
	}
    }

    /**
     * Calculates the new Stats of a pokemon, e.g. after a level up.
     */
    public void calculateMaximumStats() {
	int oldmaxhitpoints = maxhitpoints;

	maxhitpoints = (int) (((int) ((((2 * generalStats.baseHitpoints) + individualHitpoints
		+ ((int) (effortHitpoints / 4))) * level) / 100)) + level + 10);

	currentHitpoints = currentHitpoints + maxhitpoints - oldmaxhitpoints;

	attack = (int) ((((int) ((((2 * generalStats.baseAttack) + individualAttack + ((int) (effortAttack / 4)))
		* level) / 100)) + 5) * NatureModifier.attackModifier(nature));

	defense = (int) ((((int) ((((2 * generalStats.baseDefense) + individualDefense + ((int) (effortDefense / 4)))
		* level) / 100)) + 5) * NatureModifier.defenseModifier(nature));

	specialAttack = (int) ((((int) ((((2 * generalStats.baseSpecialAttack) + individualSpecialAttack
		+ ((int) (effortSpecialAttack / 4))) * level) / 100)) + 5)
		* NatureModifier.specialAttackModifier(nature));

	specialDefense = (int) ((((int) ((((2 * generalStats.baseSpecialDefense) + individualSpecialDefense
		+ ((int) (effortSpecialDefense / 4))) * level) / 100)) + 5)
		* NatureModifier.specialDefenseModifier(nature));

	speed = (int) ((((int) ((((2 * generalStats.baseSpeed) + individualSpeed + ((int) (effortSpeed / 4))) * level)
		/ 100)) + 5) * NatureModifier.speedModifier(nature));
    }

    public void onDamageCalculation(BattleInfoDTO battleInfoDTO) {
	ability.onDamageCalculation(battleInfoDTO);
    }

    public void createEgg() {
	// TO-DO
	// ...
	// eggSteps -= random.nextInt(301);
    }

    public void gainExp(BattleInfoDTO battleInfoDTO) {
	// int exp = battleInfoDTO.getEnemyPokemon.expYield;
	// ...
	// if (level up) {
	// calculateMaximumStats();
	//
    }

    public boolean triggerEvolution() {
	if (evolveType == 0) { // <-- can't evolve
	    return false;
	} else if (evolveType == 1) { // <-- evolution via leveling up
	    // regular
	    // if (friendship > 220)
	    // if (holding certain item)
	    // if (knows certain move or move type)
	    // if (at certain location)
	    // if (at certain time of day)
	    return true;
	} else if (evolveType == 2) { // <-- evolution via stone
	    // regular
	    return true;
	} else if (evolveType == 3) { // <-- evolution via trade
	    // regular
	    // if (holding certain item)
	    return true;
	} else if (evolveType == 4) { // <-- miscellaneous (e.g. Shedinja)
	    // ?
	    return true;
	}
	return false;
    }

    @Override
    public String toString() {
	return "Pokemon [hiddenId=" + hiddenId + ", id=" + generalStats.id + ", name=" + generalStats.name
		+ ", nickname=" + nickname + ", level=" + level + ", currentHitpoints=" + currentHitpoints + ", nature="
		+ nature + ", type1=" + generalStats.type1 + ", type2=" + generalStats.type2 + ", ability="
		+ ability.getName() + ", eggGroup1=" + generalStats.eggGroup1 + ", eggGroup2=" + generalStats.eggGroup2
		+ ", gender=" + gender + ", captureRate=" + generalStats.captureRate + ", levelingRate="
		+ generalStats.levelingRate + ", expYield=" + generalStats.expYield + ", eggSteps="
		+ generalStats.eggSteps + ", height=" + generalStats.height + ", weight=" + generalStats.weight
		+ ", friendship=" + friendship + ", legendary=" + generalStats.legendary + ", maxhitpoints="
		+ maxhitpoints + ", attack=" + attack + ", defense=" + defense + ", specialAttack=" + specialAttack
		+ ", specialDefense=" + specialDefense + ", speed=" + speed + ", baseHitpoints="
		+ generalStats.baseHitpoints + ", baseAttack=" + generalStats.baseAttack + ", baseDefense="
		+ generalStats.baseDefense + ", baseSpecialAttack=" + generalStats.baseSpecialAttack
		+ ", baseSpecialDefense=" + generalStats.baseSpecialDefense + ", baseSpeed=" + generalStats.baseSpeed
		+ ", individualHitpoints=" + individualHitpoints + ", individualAttack=" + individualAttack
		+ ", individualDefense=" + individualDefense + ", individualSpecialAttack=" + individualSpecialAttack
		+ ", individualSpecialDefense=" + individualSpecialDefense + ", individualSpeed=" + individualSpeed
		+ ", hitpointsYield=" + generalStats.hitpointsYield + ", attackYield=" + generalStats.attackYield
		+ ", defenseYield=" + generalStats.defenseYield + ", specialAttackYield="
		+ generalStats.specialAttackYield + ", specialDefenseYield=" + generalStats.specialDefenseYield
		+ ", speedYield=" + generalStats.speedYield + ", effortHitpoints=" + effortHitpoints + ", effortAttack="
		+ effortAttack + ", effortDefense=" + effortDefense + ", effortSpecialAttack=" + effortSpecialAttack
		+ ", effortSpecialDefense=" + effortSpecialDefense + ", effortSpeed=" + effortSpeed + "]";
    }

}
