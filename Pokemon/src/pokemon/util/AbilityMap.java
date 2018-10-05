package pokemon.util;

import java.util.HashMap;
import java.util.Map;

public class AbilityMap {

    public static final Map<String, Ability> abilities = new HashMap<>();

    static {
	abilities.put("Wonder Guard", new Ability("Wonder Guard", "Only supereffective moves will hit.")//
		.setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		})//
	);
    }

}
