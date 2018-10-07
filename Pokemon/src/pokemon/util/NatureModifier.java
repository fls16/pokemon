package pokemon.util;

public class NatureModifier {

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

    public static float attackModifier(Nature nature) {
	switch (nature) {
	case Lonely:
	    return 1.1f;
	case Adamant:
	    return 1.1f;
	case Naughty:
	    return 1.1f;
	case Brave:
	    return 1.1f;

	case Bold:
	    return 0.9f;
	case Modest:
	    return 0.9f;
	case Calm:
	    return 0.9f;
	case Timid:
	    return 0.9f;

	default:
	    return 1;
	}
    }

    public static float defenseModifier(Nature nature) {
	switch (nature) {
	case Bold:
	    return 1.1f;
	case Impish:
	    return 1.1f;
	case Lax:
	    return 1.1f;
	case Relaxed:
	    return 1.1f;

	case Lonely:
	    return 0.9f;
	case Mild:
	    return 0.9f;
	case Gentle:
	    return 0.9f;
	case Hasty:
	    return 0.9f;

	default:
	    return 1;
	}
    }

    public static float specialAttackModifier(Nature nature) {
	switch (nature) {
	case Modest:
	    return 1.1f;
	case Mild:
	    return 1.1f;
	case Rash:
	    return 1.1f;
	case Quiet:
	    return 1.1f;

	case Adamant:
	    return 0.9f;
	case Impish:
	    return 0.9f;
	case Careful:
	    return 0.9f;
	case Jolly:
	    return 0.9f;

	default:
	    return 1;
	}
    }

    public static float specialDefenseModifier(Nature nature) {
	switch (nature) {
	case Calm:
	    return 1.1f;
	case Gentle:
	    return 1.1f;
	case Careful:
	    return 1.1f;
	case Sassy:
	    return 1.1f;

	case Naughty:
	    return 0.9f;
	case Lax:
	    return 0.9f;
	case Rash:
	    return 0.9f;
	case Naive:
	    return 0.9f;

	default:
	    return 1;
	}
    }

    public static float speedModifier(Nature nature) {
	switch (nature) {
	case Timid:
	    return 1.1f;
	case Hasty:
	    return 1.1f;
	case Jolly:
	    return 1.1f;
	case Naive:
	    return 1.1f;

	case Brave:
	    return 0.9f;
	case Relaxed:
	    return 0.9f;
	case Quiet:
	    return 0.9f;
	case Sassy:
	    return 0.9f;

	default:
	    return 1;
	}
    }

}
