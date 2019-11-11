package pokemon.util;

import pokemon.util.TypeModifier.Type;

public class BasicStats {

    public enum EggGroup {
	Monster, Water_1, Water_2, Water_3, Bug, Flying, Field, Fairy, Grass, Undiscovered, Human_Like, Mineral, Amorphous, Ditto, Dragon
    }

    public enum LevelingRate {
	Slow, Medium, Fast, MediumSlow, SlowThenFast, FastThenSlow
    }

    // specific stats
    public int id;
    public String name;
    public Type type1;
    public Type type2;
    public String abilityname;
    public EggGroup eggGroup1;
    public EggGroup eggGroup2;
    public String genderRate;
    public int captureRate;
    public LevelingRate levelingRate;
    public int expYield;
    public int eggSteps;
    public float height;
    public float weight;
    public boolean legendary;

    // base stats
    public int baseHitpoints;
    public int baseAttack;
    public int baseDefense;
    public int baseSpecialAttack;
    public int baseSpecialDefense;
    public int baseSpeed;

    // ev yield
    public int hitpointsYield;
    public int attackYield;
    public int defenseYield;
    public int specialAttackYield;
    public int specialDefenseYield;
    public int speedYield;

}
