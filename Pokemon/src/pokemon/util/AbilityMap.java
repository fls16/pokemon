package pokemon.util;

import java.util.HashMap;
import java.util.Map;

public class AbilityMap {

    public static final Map<String, Ability> abilities = new HashMap<>();

    static {

	// option 1
	Ability wonderGuard = new Ability("Wonder_Guard", "Only supereffective moves will hit.");
	wonderGuard.setOnUpdate(() -> {
	    // some code
	});
	wonderGuard.setOnDamageCalculation(b -> {
	    // some code
	});
	abilities.put(wonderGuard.getName(), wonderGuard);

	// option 2
	abilities.put("Wonder_Guard",
		new Ability("Wonder_Guard", "Only supereffective moves will hit.").setOnUpdate(() -> {
		    // some code
		}).setOnDamageCalculation(b -> {
		    // some code
		}));

    }

}
