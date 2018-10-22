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
    private static long idCounter = 0;
    public BasicStats basicStats;

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
    public int maxHitpoints;
    public int attack;
    public int defense;
    public int specialAttack;
    public int specialDefense;
    public int speed;

    // iv's
    public int individualHitpoints = GlobalData.random.nextInt(32);
    public int individualAttack = GlobalData.random.nextInt(32);
    public int individualDefense = GlobalData.random.nextInt(32);
    public int individualSpecialAttack = GlobalData.random.nextInt(32);
    public int individualSpecialDefense = GlobalData.random.nextInt(32);
    public int individualSpeed = GlobalData.random.nextInt(32);

    // ev's
    public int effortHitpoints = 0;
    public int effortAttack = 0;
    public int effortDefense = 0;
    public int effortSpecialAttack = 0;
    public int effortSpecialDefense = 0;
    public int effortSpeed = 0;

    public Pokemon() {
    }

    public static Pokemon create(BasicStats basicStats, int level) {
	Pokemon pokemon = new Pokemon();

	pokemon.basicStats = basicStats;
	pokemon.hiddenId = idCounter++;
	pokemon.nickname = basicStats.name;
	pokemon.level = level;

	pokemon.setNature();
	pokemon.setAbility(basicStats.abilityname);
	pokemon.setGender(basicStats.genderRate);
	pokemon.calculateMaximumStats();

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

    private void setGender(String genderRate) {
	if (genderRate.equals("-")) {
	    gender = Gender.N;
	} else {
	    float genderRatio = Float.parseFloat(genderRate);
	    if (GlobalData.random.nextFloat() * 100 < genderRatio) {
		gender = Gender.M;
	    } else {
		gender = Gender.W;
	    }
	}
    }

    public void calculateMaximumStats() {
	int oldMaxHitpoints = maxHitpoints;

	maxHitpoints = (int) (((int) ((((2 * basicStats.baseHitpoints) + individualHitpoints
		+ ((int) (effortHitpoints / 4))) * level) / 100)) + level + 10);

	currentHitpoints = currentHitpoints + maxHitpoints - oldMaxHitpoints;

	attack = (int) ((((int) ((((2 * basicStats.baseAttack) + individualAttack + ((int) (effortAttack / 4))) * level)
		/ 100)) + 5) * NatureModifier.attackModifier(nature));

	defense = (int) ((((int) ((((2 * basicStats.baseDefense) + individualDefense + ((int) (effortDefense / 4)))
		* level) / 100)) + 5) * NatureModifier.defenseModifier(nature));

	specialAttack = (int) ((((int) ((((2 * basicStats.baseSpecialAttack) + individualSpecialAttack
		+ ((int) (effortSpecialAttack / 4))) * level) / 100)) + 5)
		* NatureModifier.specialAttackModifier(nature));

	specialDefense = (int) ((((int) ((((2 * basicStats.baseSpecialDefense) + individualSpecialDefense
		+ ((int) (effortSpecialDefense / 4))) * level) / 100)) + 5)
		* NatureModifier.specialDefenseModifier(nature));

	speed = (int) ((((int) ((((2 * basicStats.baseSpeed) + individualSpeed + ((int) (effortSpeed / 4))) * level)
		/ 100)) + 5) * NatureModifier.speedModifier(nature));
    }

    public void onDamageCalculation(BattleInfoDTO battleInfoDTO) {
	ability.onDamageCalculation(battleInfoDTO);
    }

    public void createEgg() {
	// TO-DO
	// ...
	// eggSteps -= GlobalData.random.nextInt(301);
    }

    public void gainExp(BattleInfoDTO battleInfoDTO) {
	// int exp = battleInfoDTO.getEnemyPokemon.expYield;
	// gainedExp = (exp * a * b * c) / d
	// if (currentExp + gainedExp > maxExp) {
	// levelUp();
	// currentExp2 = gainedExp + currentExp - maxExp;
	// gainExp(currentExp2);
    }

    public void levelUp() {
	level++;
	calculateMaximumStats();
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
	return "Pokemon [hiddenId=" + hiddenId + ", id=" + basicStats.id + ", name=" + basicStats.name + ", nickname="
		+ nickname + ", level=" + level + ", currentHitpoints=" + currentHitpoints + ", nature=" + nature
		+ ", type1=" + basicStats.type1 + ", type2=" + basicStats.type2 + ", ability=" + ability.getName()
		+ ", eggGroup1=" + basicStats.eggGroup1 + ", eggGroup2=" + basicStats.eggGroup2 + ", gender=" + gender
		+ ", captureRate=" + basicStats.captureRate + ", levelingRate=" + basicStats.levelingRate
		+ ", expYield=" + basicStats.expYield + ", eggSteps=" + basicStats.eggSteps + ", height="
		+ basicStats.height + ", weight=" + basicStats.weight + ", friendship=" + friendship + ", legendary="
		+ basicStats.legendary + ", maxHitpoints=" + maxHitpoints + ", attack=" + attack + ", defense="
		+ defense + ", specialAttack=" + specialAttack + ", specialDefense=" + specialDefense + ", speed="
		+ speed + ", baseHitpoints=" + basicStats.baseHitpoints + ", baseAttack=" + basicStats.baseAttack
		+ ", baseDefense=" + basicStats.baseDefense + ", baseSpecialAttack=" + basicStats.baseSpecialAttack
		+ ", baseSpecialDefense=" + basicStats.baseSpecialDefense + ", baseSpeed=" + basicStats.baseSpeed
		+ ", individualHitpoints=" + individualHitpoints + ", individualAttack=" + individualAttack
		+ ", individualDefense=" + individualDefense + ", individualSpecialAttack=" + individualSpecialAttack
		+ ", individualSpecialDefense=" + individualSpecialDefense + ", individualSpeed=" + individualSpeed
		+ ", hitpointsYield=" + basicStats.hitpointsYield + ", attackYield=" + basicStats.attackYield
		+ ", defenseYield=" + basicStats.defenseYield + ", specialAttackYield=" + basicStats.specialAttackYield
		+ ", specialDefenseYield=" + basicStats.specialDefenseYield + ", speedYield=" + basicStats.speedYield
		+ ", effortHitpoints=" + effortHitpoints + ", effortAttack=" + effortAttack + ", effortDefense="
		+ effortDefense + ", effortSpecialAttack=" + effortSpecialAttack + ", effortSpecialDefense="
		+ effortSpecialDefense + ", effortSpeed=" + effortSpeed + "]";
    }

}
