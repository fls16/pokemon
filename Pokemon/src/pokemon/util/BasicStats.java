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
    public EggGroup egg_group1;
    public EggGroup egg_group2;
    public String gender_rate;
    public int capture_rate;
    public LevelingRate leveling_rate;
    public int exp_yield;
    public int egg_steps;
    public float height;
    public float weight;
    public boolean legendary;

    // base stats
    public int base_hitpoints;
    public int base_attack;
    public int base_defense;
    public int base_special_attack;
    public int base_special_defense;
    public int base_speed;

    // ev yield
    public int hitpoints_yield;
    public int attack_yield;
    public int defense_yield;
    public int special_attack_yield;
    public int special_defense_yield;
    public int speed_yield;

    @Override
    public String toString() {
	return " || BasicStats [id=" + id + ", name=" + name + ", type1=" + type1 + ", type2=" + type2
		+ ", abilityname=" + abilityname + ", egg_group1=" + egg_group1 + ", egg_group2=" + egg_group2
		+ ", gender_rate=" + gender_rate + ", capture_rate=" + capture_rate + ", leveling_rate=" + leveling_rate
		+ ", exp_yield=" + exp_yield + ", egg_steps=" + egg_steps + ", height=" + height + ", weight=" + weight
		+ ", legendary=" + legendary + ", base_hitpoints=" + base_hitpoints + ", base_attack=" + base_attack
		+ ", base_defense=" + base_defense + ", base_special_attack=" + base_special_attack
		+ ", base_special_defense=" + base_special_defense + ", base_speed=" + base_speed + ", hitpoints_yield="
		+ hitpoints_yield + ", attack_yield=" + attack_yield + ", defense_yield=" + defense_yield
		+ ", special_attack_yield=" + special_attack_yield + ", special_defense_yield=" + special_defense_yield
		+ ", speed_yield=" + speed_yield + "]";
    }

}
