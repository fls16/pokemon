package pokemon.entities;

import pokemon.util.Nature;
import pokemon.util.Type;

public class Bulbasaur extends Pokemon {

	// dynamic stats
	private int id;
	private String nickname;
	private int level;
	private int currentHitpoints;
	private Type type1;
	private Type type2;
	private Nature nature;

	private int maxhitpoints;
	private int attack;
	private int defense;
	private int specialAttack;
	private int specialDefense;
	private int speed;

	// base stats
	private int baseHitpoints = 45;
	private int baseAttack = 49;
	private int baseDefense = 49;
	private int baseSpecialAttack = 65;
	private int baseSpecialDefense = 65;
	private int baseSpeed = 45;

	// iv's
	private int individualHitpoints;
	private int individualAttack;
	private int individualDefense;
	private int individualSpecialAttack;
	private int individualSpecialDefense;
	private int individualSpeed;

	// ev's
	private int effortHitpoints;
	private int effortAttack;
	private int effortDefense;
	private int effortSpecialAttack;
	private int effortSpecialDefense;
	private int effortSpeed;

	public Bulbasaur(String nickname, int level) {
		super(nickname, level);
		this.nickname = nickname;
		this.level = level;
	}

}
