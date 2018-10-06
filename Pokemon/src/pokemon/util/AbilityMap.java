package pokemon.util;

import java.util.HashMap;
import java.util.Map;

public class AbilityMap {

    public static final Map<String, Ability> abilities = new HashMap<>();

    static {
	abilities.put("Air Lock", new Ability("Air Lock", "Eliminates the effects of weather.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Arena Trap", new Ability("Arena Trap", "Prevents the foe from fleeing.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Battle Armor", new Ability("Battle Armor", "The Pok�mon is protected against critical hits.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Blaze", new Ability("Blaze", "Powers up Fire-type moves in a pinch.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Chlorophyll", new Ability("Chlorophyll", "Boosts the Pok�mon's Speed in sunshine.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Clear Body", new Ability("Clear Body", "Prevents other Pok�mon from lowering its stats.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Cloud Nine", new Ability("Cloud Nine", "Eliminates the effects of weather.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Color Change", new Ability("Color Change", "Changes the Pok�mon's type to the foe's move.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Compound Eyes", new Ability("Compound Eyes", "The Pok�mon's accuracy is boosted.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Cute Charm", new Ability("Cute Charm", "Contact with the Pok�mon may cause infatuation.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Damp", new Ability("Damp", "Prevents the use of self-destructing moves.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Drizzle", new Ability("Drizzle", "The Pok�mon makes it rain when it enters a battle.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Drought", new Ability("Drought", "Turns the sunlight harsh when the Pok�mon enters a battle.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Early Bird", new Ability("Early Bird", "The Pok�mon awakens quickly from sleep.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Effect Spore", new Ability("Effect Spore", "Contact may poison or cause paralysis or sleep.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Flame Body", new Ability("Flame Body", "Contact with the Pok�mon may burn the attacker.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Flash Fire", new Ability("Flash Fire", "It powers up Fire-type moves if it's hit by one.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Forecast", new Ability("Forecast", "Castform transforms with the weather.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Guts", new Ability("Guts", "Boosts Attack if there is a status problem.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Huge Power", new Ability("Huge Power", "Raises the Pok�mon's Attack stat.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Hustle", new Ability("Hustle", "Boosts the Attack stat, but lowers accuracy.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Hyper Cutter", new Ability("Hyper Cutter", "Prevents other Pok�mon from lowering Attack stat.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Illuminate", new Ability("Illuminate", "Raises the likelihood of meeting wild Pok�mon.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Immunity", new Ability("Immunity", "Prevents the Pok�mon from getting poisoned.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Inner Focus", new Ability("Inner Focus", "The Pok�mon is protected from flinching.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Insomnia", new Ability("Insomnia", "Prevents the Pok�mon from falling asleep.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Intimidate", new Ability("Intimidate", "Lowers the foe's Attack stat.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Keen Eye", new Ability("Keen Eye", "Prevents other Pok�mon from lowering accuracy.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Levitate", new Ability("Levitate", "Gives immunity to Ground type moves.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Lightning Rod",
		new Ability("Lightning Rod", "Draws in all Electric-type moves to up Sp. Attack.")//
			.setOnUpdate(() -> {
			    // some code
			}).setOnDamageCalculation(b -> {
			    // some code
			})//
	);
	abilities.put("Limber", new Ability("Limber", "The Pok�mon is protected from paralysis.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Liquid Ooze", new Ability("Liquid Ooze", "Damages attackers using any draining move.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Magma Armor", new Ability("Magma Armor", "Prevents the Pok�mon from becoming frozen.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Magnet Pull", new Ability("Magnet Pull", "Prevents Steel-type Pok�mon from escaping.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Marvel Scale", new Ability("Marvel Scale", "Ups Defense if there is a status problem.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Minus", new Ability("Minus", "Ups Sp. Atk if another Pok�mon has Plus or Minus.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Natural Cure", new Ability("Natural Cure", "All status problems heal when it switches out.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Oblivious", new Ability("Oblivious", "Prevents it from becoming infatuated.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Overgrow", new Ability("Overgrow", "Powers up Grass-type moves in a pinch.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Own Tempo", new Ability("Own Tempo", "Prevents the Pok�mon from becoming confused.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Pickup", new Ability("Pickup", "The Pok�mon may pick up items.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Plus", new Ability("Plus", "Ups Sp. Atk if another Pok�mon has Plus or Minus.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Poison Point", new Ability("Poison Point", "Contact with the Pok�mon may poison the attacker.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Pressure", new Ability("Pressure", "The Pok�mon raises the foe's PP usage.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Pure Power", new Ability("Pure Power", "Raises the Pok�mon's Attack stat.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Rain Dish", new Ability("Rain Dish", "The Pok�mon gradually regains HP in rain.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Rock Head", new Ability("Rock Head", "Protects the Pok�mon from recoil damage.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Rough Skin", new Ability("Rough Skin", "Inflicts damage to the attacker on contact.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Run Away", new Ability("Run Away", "Enables a sure getaway from wild Pok�mon.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Sand Stream", new Ability("Sand Stream", "The Pok�mon summons a sandstorm in battle.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Sand Veil", new Ability("Sand Veil", "Boosts the Pok�mon's evasion in a sandstorm.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Serene Grace", new Ability("Serene Grace", "Boosts the likelihood of added effects appearing.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Shadow Tag", new Ability("Shadow Tag", "Prevents the foe from escaping.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Shed Skin", new Ability("Shed Skin", "The Pok�mon may heal its own status problems.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Shell Armor", new Ability("Shell Armor", "The Pok�mon is protected against critical hits.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Shield Dust", new Ability("Shield Dust", "Blocks the added effects of attacks taken.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Soundproof", new Ability("Soundproof", "Gives immunity to�sound-based�moves.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Speed Boost", new Ability("Speed Boost", "Its Speed stat is gradually boosted.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Static", new Ability("Static", "Contact with the Pok�mon may cause paralysis.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Stench", new Ability("Stench", "The stench may cause the target to flinch.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Sticky Hold", new Ability("Sticky Hold", "Protects the Pok�mon from item theft.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Sturdy", new Ability("Sturdy", "It cannot be knocked out with one hit.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Suction Cups", new Ability("Suction Cups", "Negates all moves that force switching out.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Swarm", new Ability("Swarm", "Powers up Bug-type moves in a pinch.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Swift Swim", new Ability("Swift Swim", "Boosts the Pok�mon's Speed in rain.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Synchronize", new Ability("Synchronize", "Passes a burn, poison, or paralysis to the foe.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Thick Fat", new Ability("Thick Fat", "Ups resistance to Fire- and Ice-type moves.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Torrent", new Ability("Torrent", "Powers up Water-type moves in a pinch.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Trace", new Ability("Trace", "The Pok�mon copies a foe's Ability.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Truant", new Ability("Truant", "Pok�mon can't attack on consecutive turns.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Vital Spirit", new Ability("Vital Spirit", "Prevents the Pok�mon from falling asleep.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Volt Absorb", new Ability("Volt Absorb", "Restores HP if hit by an Electric-type move.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Water Absorb", new Ability("Water Absorb", "Restores HP if hit by a Water-type move.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Water Veil", new Ability("Water Veil", "Prevents the Pok�mon from getting a burn.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("White Smoke", new Ability("White Smoke", "Prevents other Pok�mon from lowering its stats.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Wonder Guard", new Ability("Wonder Guard", "Only supereffective moves will hit.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Adaptability", new Ability("Adaptability", "Powers up moves of the same type.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Aftermath", new Ability("Aftermath", "Damages the attacker landing the finishing hit.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Anger Point", new Ability("Anger Point", "Maxes Attack after taking a critical hit.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Anticipation", new Ability("Anticipation", "Senses a foe's dangerous moves.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Bad Dreams", new Ability("Bad Dreams", "Reduces a sleeping foe's HP.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Download", new Ability("Download", "Adjusts power according to a foe's defenses.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Dry Skin", new Ability("Dry Skin", "Reduces HP if it is hot. Water restores HP.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Filter", new Ability("Filter", "Reduces damage from supereffective attacks.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Flower Gift", new Ability("Flower Gift", "Powers up party Pok�mon when it is sunny.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Forewarn", new Ability("Forewarn", "Determines what moves a foe has.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Frisk", new Ability("Frisk", "The Pok�mon can check a foe's held item.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Gluttony", new Ability("Gluttony", "Encourages the early use of a held Berry.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Heatproof", new Ability("Heatproof", "Weakens the power of Fire-type moves.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Honey Gather", new Ability("Honey Gather", "The Pok�mon may gather Honey from somewhere.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Hydration", new Ability("Hydration", "Heals status problems if it is raining.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Ice Body", new Ability("Ice Body", "The Pok�mon gradually regains HP in a hailstorm.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Iron Fist", new Ability("Iron Fist", "Boosts the power of punching moves.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Klutz", new Ability("Klutz", "The Pok�mon can't use any held items.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Leaf Guard", new Ability("Leaf Guard", "Prevents problems with status in sunny weather.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Magic Guard", new Ability("Magic Guard", "The Pok�mon only takes damage from attacks.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Mold Breaker", new Ability("Mold Breaker", "Moves can be used regardless of Abilities.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Motor Drive", new Ability("Motor Drive", "Raises Speed if hit by an Electric-type move.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Multitype", new Ability("Multitype", "Changes type to match the held Plate.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("No Guard", new Ability("No Guard", "Ensures attacks by or against the Pok�mon land.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Normalize", new Ability("Normalize", "All the Pok�mon's moves become the Normal type.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Poison Heal", new Ability("Poison Heal", "Restores HP if the Pok�mon is poisoned.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Quick Feet", new Ability("Quick Feet", "Boosts Speed if there is a status problem.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Reckless", new Ability("Reckless", "Powers up moves that have recoil damage.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Rivalry", new Ability("Rivalry", "Deals more damage to a Pok�mon of same gender.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Scrappy", new Ability("Scrappy", "Enables moves to hit Ghost-type Pok�mon.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Simple", new Ability("Simple", "Doubles all stat changes.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Skill Link", new Ability("Skill Link", "Increases the frequency of multi-strike moves.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Slow Start", new Ability("Slow Start", "Temporarily halves Attack and Speed.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Sniper", new Ability("Sniper", "Powers up moves if they become critical hits.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Snow Cloak", new Ability("Snow Cloak", "Raises evasion in a hailstorm.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Snow Warning", new Ability("Snow Warning", "The Pok�mon summons a hailstorm in battle.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Solar Power", new Ability("Solar Power", "In sunshine, Sp. Atk is boosted but HP decreases.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Solid Rock", new Ability("Solid Rock", "Reduces damage from supereffective attacks.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Stall", new Ability("Stall", "The Pok�mon moves after all other Pok�mon do.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Steadfast", new Ability("Steadfast", "Raises Speed each time the Pok�mon flinches.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Storm Drain", new Ability("Storm Drain", "Draws in all Water-type moves to up Sp. Attack.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Super Luck", new Ability("Super Luck", "Heightens the critical-hit ratios of moves.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Tangled Feet", new Ability("Tangled Feet", "Raises evasion if the Pok�mon is confused.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Technician", new Ability("Technician", "Powers up the Pok�mon's weaker moves.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Tinted Lens", new Ability("Tinted Lens", "Powers up �not very effective� moves.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Unaware", new Ability("Unaware", "Ignores any stat changes in the Pok�mon.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
	abilities.put("Unburden", new Ability("Unburden", "Raises Speed if a held item is used.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
    }

}
