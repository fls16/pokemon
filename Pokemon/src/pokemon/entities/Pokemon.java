package pokemon.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import pokemon.util.NatureModifier;

public class Pokemon {

    private enum Type {
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

    private enum Gender {
	M, W
    }

    // util
    Random random = new Random();

    // specific stats
    private int id;
    private String name;
    private String nickname;
    private int level = 1;
    private int currentHitpoints;
    private Type type1;
    private Type type2;
    private Nature nature;
    private Gender gender;

    // calculated stats
    private int maxhitpoints;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;

    // base stats
    private int baseHitpoints;
    private int baseAttack;
    private int baseDefense;
    private int baseSpecialAttack;
    private int baseSpecialDefense;
    private int baseSpeed;

    // iv's
    private int individualHitpoints = random.nextInt(32);
    private int individualAttack = random.nextInt(32);
    private int individualDefense = random.nextInt(32);
    private int individualSpecialAttack = random.nextInt(32);
    private int individualSpecialDefense = random.nextInt(32);
    private int individualSpeed = random.nextInt(32);

    // ev's
    private int effortHitpoints;
    private int effortAttack;
    private int effortDefense;
    private int effortSpecialAttack;
    private int effortSpecialDefense;
    private int effortSpeed;

    public Pokemon(int level) {
	setNature();
	calculateMaximumStats();

	currentHitpoints = maxhitpoints;
    }

    public static Pokemon create() {
	return null;
    }

    private void setNature() {
	List<Nature> natures = Arrays.asList(Nature.values());
	this.nature = natures.get(random.nextInt(25));
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
