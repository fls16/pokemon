package pokemon.entities;

public abstract class Battle {

    public Pokemon current_pokemon;
    public Pokemon enemy_pokemon;
    public Player player;

    public Battle() {
    }

    public abstract void init();

}
