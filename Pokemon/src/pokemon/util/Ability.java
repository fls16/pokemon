package pokemon.util;

import pokemon.dto.BattleInfoDTO;

public class Ability {

    // private interfaces
    public interface OnUpdate {
	public void execute();
    }

    public interface OnDamageCalculation {
	public void execute(BattleInfoDTO battleInfoDTO);
    }

    // ability information
    private String name;
    private String description;

    // interface logic
    private OnUpdate onUpdate = () -> {
    };
    private OnDamageCalculation onDamageCalculation = b -> {
    };

    public Ability(String name, String description) {
	this.name = name;
	this.description = description;
    }

    // interface implementation
    public void update() {
	onUpdate.execute();
    }

    public void onDamageCalculation(BattleInfoDTO battleInfoDTO) {
	onDamageCalculation.execute(battleInfoDTO);
    }

    // getter ability information
    public String getDescription() {
	return description;
    }

    public String getName() {
	return name;
    }

    // setter interface logic
    public Ability setOnUpdate(OnUpdate onUpdate) {
	this.onUpdate = onUpdate;
	return this;
    }

    public Ability setOnDamageCalculation(OnDamageCalculation onDamageCalculation) {
	this.onDamageCalculation = onDamageCalculation;
	return this;
    }

}
