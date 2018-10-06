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
	float natureMod;

	switch (nature) {
	case Lonely:
	    natureMod = 1.1f;
	    break;
	case Adamant:
	    natureMod = 1.1f;
	    break;
	case Naughty:
	    natureMod = 1.1f;
	    break;
	case Brave:
	    natureMod = 1.1f;
	    break;

	case Bold:
	    natureMod = 0.9f;
	    break;
	case Modest:
	    natureMod = 0.9f;
	    break;
	case Calm:
	    natureMod = 0.9f;
	    break;
	case Timid:
	    natureMod = 0.9f;
	    break;

	default:
	    natureMod = 1;
	    break;
	}

	return natureMod;
    }

    public static float defenseModifier(Nature nature) {
	float natureMod;

	switch (nature) {
	case Bold:
	    natureMod = 1.1f;
	    break;
	case Impish:
	    natureMod = 1.1f;
	    break;
	case Lax:
	    natureMod = 1.1f;
	    break;
	case Relaxed:
	    natureMod = 1.1f;
	    break;

	case Lonely:
	    natureMod = 0.9f;
	    break;
	case Mild:
	    natureMod = 0.9f;
	    break;
	case Gentle:
	    natureMod = 0.9f;
	    break;
	case Hasty:
	    natureMod = 0.9f;
	    break;

	default:
	    natureMod = 1;
	    break;
	}

	return natureMod;
    }

    public static float specialAttackModifier(Nature nature) {
	float natureMod;

	switch (nature) {
	case Modest:
	    natureMod = 1.1f;
	    break;
	case Mild:
	    natureMod = 1.1f;
	    break;
	case Rash:
	    natureMod = 1.1f;
	    break;
	case Quiet:
	    natureMod = 1.1f;
	    break;

	case Adamant:
	    natureMod = 0.9f;
	    break;
	case Impish:
	    natureMod = 0.9f;
	    break;
	case Careful:
	    natureMod = 0.9f;
	    break;
	case Jolly:
	    natureMod = 0.9f;
	    break;

	default:
	    natureMod = 1;
	    break;
	}

	return natureMod;
    }

    public static float specialDefenseModifier(Nature nature) {
	float natureMod;

	switch (nature) {
	case Calm:
	    natureMod = 1.1f;
	    break;
	case Gentle:
	    natureMod = 1.1f;
	    break;
	case Careful:
	    natureMod = 1.1f;
	    break;
	case Sassy:
	    natureMod = 1.1f;
	    break;

	case Naughty:
	    natureMod = 0.9f;
	    break;
	case Lax:
	    natureMod = 0.9f;
	    break;
	case Rash:
	    natureMod = 0.9f;
	    break;
	case Naive:
	    natureMod = 0.9f;
	    break;

	default:
	    natureMod = 1;
	    break;
	}

	return natureMod;
    }

    public static float speedModifier(Nature nature) {
	float natureMod;

	switch (nature) {
	case Timid:
	    natureMod = 1.1f;
	    break;
	case Hasty:
	    natureMod = 1.1f;
	    break;
	case Jolly:
	    natureMod = 1.1f;
	    break;
	case Naive:
	    natureMod = 1.1f;
	    break;

	case Brave:
	    natureMod = 0.9f;
	    break;
	case Relaxed:
	    natureMod = 0.9f;
	    break;
	case Quiet:
	    natureMod = 0.9f;
	    break;
	case Sassy:
	    natureMod = 0.9f;
	    break;

	default:
	    natureMod = 1;
	    break;
	}

	return natureMod;
    }

}
