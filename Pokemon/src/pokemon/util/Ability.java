package pokemon.util;

import java.util.HashMap;
import java.util.Map;

public abstract class Ability {

    public static final Map<String, Ability> abilities = new HashMap<>();

    static {

	abilities.put("Wonder_Guard", new Ability("Wonder_Guard", "Only supereffective moves will hit.") {

	    @Override
	    public void update() {

	    }

	    @Override
	    public void onDamageCalculation() {

	    }
	});

    }

    private String name;
    private String description;

    public Ability(String name, String description) {
	this.name = name;
	this.description = description;
    }

    public abstract void update();

    public abstract void onDamageCalculation();

}
