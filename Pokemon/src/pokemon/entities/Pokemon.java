package pokemon.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import pokemon.dto.BattleInfoDTO;
import pokemon.util.Ability;
import pokemon.util.AbilityMap;
import pokemon.util.NatureModifier;

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
    public int id;
    public String name;
    public String nickname;
    public int level;
    public int currentHitpoints;
    public Nature nature;
    public Type type1;
    public Type type2;
    public String abilityname;
    public Ability ability;
    public EggGroup eggGroup1;
    public EggGroup eggGroup2;
    public String genderRate;
    public Gender gender;
    public int captureRate;
    public LevelingRate levelingRate;
    public int expYield;
    public int eggSteps;
    public float height;
    public float weight;
    public boolean legendary;

    // calculated stats
    public int maxhitpoints;
    public int attack;
    public int defense;
    public int specialAttack;
    public int specialDefense;
    public int speed;

    // base stats
    public int baseHitpoints;
    public int baseAttack;
    public int baseDefense;
    public int baseSpecialAttack;
    public int baseSpecialDefense;
    public int baseSpeed;

    // iv's
    public int individualHitpoints = random.nextInt(32);
    public int individualAttack = random.nextInt(32);
    public int individualDefense = random.nextInt(32);
    public int individualSpecialAttack = random.nextInt(32);
    public int individualSpecialDefense = random.nextInt(32);
    public int individualSpeed = random.nextInt(32);

    // iv yield
    public int hitpointsYield;
    public int attackYield;
    public int defenseYield;
    public int specialAttackYield;
    public int specialDefenseYield;
    public int speedYield;

    // ev's
    public int effortHitpoints = 0;
    public int effortAttack = 0;
    public int effortDefense = 0;
    public int effortSpecialAttack = 0;
    public int effortSpecialDefense = 0;
    public int effortSpeed = 0;

    public Pokemon() {
    }

    public Pokemon create(Pokemon tempPokemon, int level) {
	Pokemon finalPokemon = new Pokemon();
	finalPokemon.hiddenId = idCounter;
	idCounter++;
	finalPokemon.id = tempPokemon.id;
	finalPokemon.name = tempPokemon.name;
	finalPokemon.nickname = tempPokemon.name;
	finalPokemon.level = level;
	finalPokemon.type1 = tempPokemon.type1;
	finalPokemon.type2 = tempPokemon.type2;
	finalPokemon.eggGroup1 = tempPokemon.eggGroup1;
	finalPokemon.eggGroup2 = tempPokemon.eggGroup2;
	finalPokemon.captureRate = tempPokemon.captureRate;
	finalPokemon.levelingRate = tempPokemon.levelingRate;
	finalPokemon.expYield = tempPokemon.expYield;
	finalPokemon.eggSteps = tempPokemon.eggSteps;
	finalPokemon.height = tempPokemon.height;
	finalPokemon.weight = tempPokemon.weight;
	finalPokemon.legendary = tempPokemon.legendary;
	finalPokemon.baseHitpoints = tempPokemon.baseHitpoints;
	finalPokemon.baseAttack = tempPokemon.baseAttack;
	finalPokemon.baseDefense = tempPokemon.baseDefense;
	finalPokemon.baseSpecialAttack = tempPokemon.baseSpecialAttack;
	finalPokemon.baseSpecialDefense = tempPokemon.baseSpecialDefense;
	finalPokemon.baseSpeed = tempPokemon.baseSpeed;
	finalPokemon.hitpointsYield = tempPokemon.hitpointsYield;
	finalPokemon.attackYield = tempPokemon.attackYield;
	finalPokemon.defenseYield = tempPokemon.defenseYield;
	finalPokemon.specialAttackYield = tempPokemon.specialAttackYield;
	finalPokemon.specialDefenseYield = tempPokemon.specialDefenseYield;
	finalPokemon.speedYield = tempPokemon.speedYield;

	finalPokemon.setNature();
	finalPokemon.setAbility(tempPokemon.abilityname);
	finalPokemon.setGender(tempPokemon.genderRate);
	finalPokemon.setEggSteps();
	finalPokemon.calculateMaximumStats();

	return finalPokemon;
    }

    public void onDamageCalculation(BattleInfoDTO battleInfoDTO) {
	ability.onDamageCalculation(battleInfoDTO);
    }

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

    private void setNature() {
	List<Nature> natures = Arrays.asList(Nature.values());
	this.nature = natures.get(random.nextInt(25));
    }

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

    private void setEggSteps() {
	this.eggSteps -= random.nextInt(301);
    }

    public void calculateMaximumStats() {
	int oldmaxhitpoints = maxhitpoints;

	maxhitpoints = (int) (((int) ((((2 * baseHitpoints) + individualHitpoints + ((int) (effortHitpoints / 4)))
		* level) / 100)) + level + 10);

	currentHitpoints = currentHitpoints + maxhitpoints - oldmaxhitpoints;

	attack = (int) ((((int) ((((2 * baseAttack) + individualAttack + ((int) (effortAttack / 4))) * level) / 100))
		+ 5) * NatureModifier.attackModifier(nature));

	defense = (int) ((((int) ((((2 * baseDefense) + individualDefense + ((int) (effortDefense / 4))) * level)
		/ 100)) + 5) * NatureModifier.defenseModifier(nature));

	specialAttack = (int) ((((int) ((((2 * baseSpecialAttack) + individualSpecialAttack
		+ ((int) (effortSpecialAttack / 4))) * level) / 100)) + 5)
		* NatureModifier.specialAttackModifier(nature));

	specialDefense = (int) ((((int) ((((2 * baseSpecialDefense) + individualSpecialDefense
		+ ((int) (effortSpecialDefense / 4))) * level) / 100)) + 5)
		* NatureModifier.specialDefenseModifier(nature));

	speed = (int) ((((int) ((((2 * baseSpeed) + individualSpeed + ((int) (effortSpeed / 4))) * level) / 100)) + 5)
		* NatureModifier.speedModifier(nature));
    }

    @Override
    public String toString() {
	return "Pokemon [hiddenId=" + hiddenId + ", id=" + id + ", name=" + name + ", nickname=" + nickname + ", level="
		+ level + ", currentHitpoints=" + currentHitpoints + ", nature=" + nature + ", type1=" + type1
		+ ", type2=" + type2 + ", ability=" + ability.getName() + ", eggGroup1=" + eggGroup1 + ", eggGroup2="
		+ eggGroup2 + ", gender=" + gender + ", captureRate=" + captureRate + ", levelingRate=" + levelingRate
		+ ", expYield=" + expYield + ", eggSteps=" + eggSteps + ", height=" + height + ", weight=" + weight
		+ ", legendary=" + legendary + ", maxhitpoints=" + maxhitpoints + ", attack=" + attack + ", defense="
		+ defense + ", specialAttack=" + specialAttack + ", specialDefense=" + specialDefense + ", speed="
		+ speed + ", baseHitpoints=" + baseHitpoints + ", baseAttack=" + baseAttack + ", baseDefense="
		+ baseDefense + ", baseSpecialAttack=" + baseSpecialAttack + ", baseSpecialDefense="
		+ baseSpecialDefense + ", baseSpeed=" + baseSpeed + ", individualHitpoints=" + individualHitpoints
		+ ", individualAttack=" + individualAttack + ", individualDefense=" + individualDefense
		+ ", individualSpecialAttack=" + individualSpecialAttack + ", individualSpecialDefense="
		+ individualSpecialDefense + ", individualSpeed=" + individualSpeed + ", hitpointsYield="
		+ hitpointsYield + ", attackYield=" + attackYield + ", defenseYield=" + defenseYield
		+ ", specialAttackYield=" + specialAttackYield + ", specialDefenseYield=" + specialDefenseYield
		+ ", speedYield=" + speedYield + ", effortHitpoints=" + effortHitpoints + ", effortAttack="
		+ effortAttack + ", effortDefense=" + effortDefense + ", effortSpecialAttack=" + effortSpecialAttack
		+ ", effortSpecialDefense=" + effortSpecialDefense + ", effortSpeed=" + effortSpeed + "]";
    }

}
