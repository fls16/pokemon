package pokemon.entities;

public abstract class Battle {

    public Pokemon currentPokemon;
    public Pokemon enemyPokemon;
    public Player player;

    public Battle() {
    }

    public abstract void init();

}
