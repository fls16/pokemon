package pokemon.entities;

import pokemon.util.Nature;
import pokemon.util.Type;

public class Bulbasaur extends Pokemon {

	// dynamic stats
	private int id = 1;
	private Type type1 = Type.Grass;
	private Type type2 = Type.Poison;

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

	// ev's
	private int effortHitpoints;
	private int effortAttack;
	private int effortDefense;
	private int effortSpecialAttack;
	private int effortSpecialDefense;
	private int effortSpeed;

	public Bulbasaur(int level) {
		this.level = level;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getCurrentHitpoints() {
		return currentHitpoints;
	}

	public void setCurrentHitpoints(int currentHitpoints) {
		this.currentHitpoints = currentHitpoints;
	}

	public Type getType1() {
		return type1;
	}

	public void setType1(Type type1) {
		this.type1 = type1;
	}

	public Type getType2() {
		return type2;
	}

	public void setType2(Type type2) {
		this.type2 = type2;
	}

	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}

	public int getMaxhitpoints() {
		return maxhitpoints;
	}

	public void setMaxhitpoints(int maxhitpoints) {
		this.maxhitpoints = maxhitpoints;
	}

	public int getBaseHitpoints() {
		return baseHitpoints;
	}

	public void setBaseHitpoints(int baseHitpoints) {
		this.baseHitpoints = baseHitpoints;
	}

	public int getIndividualHitpoints() {
		return individualHitpoints;
	}

	public void setIndividualHitpoints(int individualHitpoints) {
		this.individualHitpoints = individualHitpoints;
	}

	public int getEffortHitpoints() {
		return effortHitpoints;
	}

	public void setEffortHitpoints(int effortHitpoints) {
		this.effortHitpoints = effortHitpoints;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
