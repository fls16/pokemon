package pokemon.util;

public class Ability {

    // private interfaces
    public interface OnUpdate {
	public void execute();
    }

    public interface OnDamageCalculation {
	public void execute(BattleInfoDTO battle_info_DTO);
    }

    // ability information
    public String name;
    public String description;

    // interface logic
    private OnUpdate on_update = () -> {
    };
    private OnDamageCalculation on_damage_calculation = b -> {
    };

    public Ability(String name, String description) {
	this.name = name;
	this.description = description;
    }

    // interface implementation
    public void update() {
	on_update.execute();
    }

    public void onDamageCalculation(BattleInfoDTO battle_info_DTO) {
	on_damage_calculation.execute(battle_info_DTO);
    }

    // setter interface logic
    public Ability setOnUpdate(OnUpdate on_update) {
	this.on_update = on_update;
	return this;
    }

    public Ability setOnDamageCalculation(OnDamageCalculation on_damage_calculation) {
	this.on_damage_calculation = on_damage_calculation;
	return this;
    }

}
