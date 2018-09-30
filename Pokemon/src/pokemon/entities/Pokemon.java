package pokemon.entities;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import pokemon.util.Nature;
import pokemon.util.NatureModifier;
import pokemon.util.Type;

public abstract class Pokemon {

	// util
	Random random = new Random();

	// specific stats
	protected int id;
	protected String nickname = this.getClass().getSimpleName();
	protected int level = 1;
	protected int currentHitpoints;
	protected Type type1;
	protected Type type2;
	protected Nature nature;

	// calculated stats
	protected int maxhitpoints;
	protected int attack;
	protected int defense;
	protected int specialAttack;
	protected int specialDefense;
	protected int speed;

	// base stats
	protected int baseHitpoints;
	protected int baseAttack;
	protected int baseDefense;
	protected int baseSpecialAttack;
	protected int baseSpecialDefense;
	protected int baseSpeed;

	// iv's
	protected int individualHitpoints = random.nextInt(32);
	protected int individualAttack = random.nextInt(32);
	protected int individualDefense = random.nextInt(32);
	protected int individualSpecialAttack = random.nextInt(32);
	protected int individualSpecialDefense = random.nextInt(32);
	protected int individualSpeed = random.nextInt(32);

	// ev's
	protected int effortHitpoints;
	protected int effortAttack;
	protected int effortDefense;
	protected int effortSpecialAttack;
	protected int effortSpecialDefense;
	protected int effortSpeed;

	public Pokemon() {
		setNature();
		calculateMaximumStats();

		currentHitpoints = maxhitpoints;
	}

	private void setNature() {
		Random random = new Random();
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
