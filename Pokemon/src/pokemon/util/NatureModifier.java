package pokemon.util;

public class NatureModifier {

	public static float attackModifier(Nature nature) {
		float natureMod;

		switch (nature) {
		case Lonely:
			natureMod = 1.1f;
		case Adamant:
			natureMod = 1.1f;
		case Naughty:
			natureMod = 1.1f;
		case Brave:
			natureMod = 1.1f;

		case Bold:
			natureMod = 0.9f;
		case Modest:
			natureMod = 0.9f;
		case Calm:
			natureMod = 0.9f;
		case Timid:
			natureMod = 0.9f;

		default:
			natureMod = 1;
		}

		return natureMod;
	}

	public static float defenseModifier(Nature nature) {
		float natureMod;

		switch (nature) {
		case Bold:
			natureMod = 1.1f;
		case Impish:
			natureMod = 1.1f;
		case Lax:
			natureMod = 1.1f;
		case Relaxed:
			natureMod = 1.1f;

		case Lonely:
			natureMod = 0.9f;
		case Mild:
			natureMod = 0.9f;
		case Gentle:
			natureMod = 0.9f;
		case Hasty:
			natureMod = 0.9f;

		default:
			natureMod = 1;
		}

		return natureMod;
	}

	public static float specialAttackModifier(Nature nature) {
		float natureMod;

		switch (nature) {
		case Modest:
			natureMod = 1.1f;
		case Mild:
			natureMod = 1.1f;
		case Rash:
			natureMod = 1.1f;
		case Quiet:
			natureMod = 1.1f;

		case Adamant:
			natureMod = 0.9f;
		case Impish:
			natureMod = 0.9f;
		case Careful:
			natureMod = 0.9f;
		case Jolly:
			natureMod = 0.9f;

		default:
			natureMod = 1;
		}

		return natureMod;
	}

	public static float specialDefenseModifier(Nature nature) {
		float natureMod;

		switch (nature) {
		case Calm:
			natureMod = 1.1f;
		case Gentle:
			natureMod = 1.1f;
		case Careful:
			natureMod = 1.1f;
		case Sassy:
			natureMod = 1.1f;

		case Naughty:
			natureMod = 0.9f;
		case Lax:
			natureMod = 0.9f;
		case Rash:
			natureMod = 0.9f;
		case Naive:
			natureMod = 0.9f;

		default:
			natureMod = 1;
		}

		return natureMod;
	}

	public static float speedModifier(Nature nature) {
		float natureMod;

		switch (nature) {
		case Timid:
			natureMod = 1.1f;
		case Hasty:
			natureMod = 1.1f;
		case Jolly:
			natureMod = 1.1f;
		case Naive:
			natureMod = 1.1f;

		case Brave:
			natureMod = 0.9f;
		case Relaxed:
			natureMod = 0.9f;
		case Quiet:
			natureMod = 0.9f;
		case Sassy:
			natureMod = 0.9f;

		default:
			natureMod = 1;
		}

		return natureMod;
	}

}
