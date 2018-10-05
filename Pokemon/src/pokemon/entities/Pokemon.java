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
    public int level = 1;
    public int currentHitpoints;
    public Nature nature;
    public Type type1;
    public Type type2;
    public Ability ability;
    public EggGroup eggGroup1;
    public EggGroup eggGroup2;
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
	hiddenId = idCounter;
	idCounter++;
    }

    public Pokemon create(int id, int level, Pokemon pokemon) {
	this.level = level;

	pokemon.ability;
	setAbility(abilityName);

	setNature();
	setGender();
	calculateMaximumStats();
	currentHitpoints = maxhitpoints;

	return null;
    }

    public void onDamageCalculation(BattleInfoDTO battleInfoDTO) {
	ability.onDamageCalculation(battleInfoDTO);
    }

    private void setAbility(String ability) {
	this.ability = AbilityMap.abilities.get(ability);
    }

    private void setNature() {
	List<Nature> natures = Arrays.asList(Nature.values());
	this.nature = natures.get(random.nextInt(25));
    }

    private void setGender() {

    }

    private void calculateMaximumStats() {
	maxhitpoints = (int) (((int) ((((2 * baseHitpoints) + individualHitpoints + ((int) (effortHitpoints / 4)))
		* level) / 100)) + level + 10);

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

}
