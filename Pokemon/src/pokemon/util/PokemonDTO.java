package pokemon.util;

import java.util.Random;

public class PokemonDTO {

    // util
    Random random = new Random();

    // specific stats
    public int id;
    public String name;
    public String type1;
    public String type2;
    public String abilityname;
    public String eggGroup1;
    public String eggGroup2;
    public String genderRate;
    public int captureRate;
    public String levelingRate;
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

    // iv yield
    public int hitpointsYield;
    public int attackYield;
    public int defenseYield;
    public int specialAttackYield;
    public int specialDefenseYield;
    public int speedYield;

}
