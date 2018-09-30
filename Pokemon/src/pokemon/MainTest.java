package pokemon;

import pokemon.entities.Bulbasaur;

public class MainTest {

	public static void main(String[] args) {
		Bulbasaur bulbasaur = new Bulbasaur(5);
		System.out.println("Level: " + bulbasaur.getLevel());
		System.out.println("BaseHP: " + bulbasaur.getBaseHitpoints());
		System.out.println("EffHP: " + bulbasaur.getEffortHitpoints());
		System.out.println("IndHP: " + bulbasaur.getIndividualHitpoints());
		System.out.println("CurHP: " + bulbasaur.getCurrentHitpoints());
		System.out.println("MaxHP: " + bulbasaur.getMaxhitpoints());
		System.out.println("Name: " + bulbasaur.getNickname());
		System.out.println("Id: " + bulbasaur.getId());
		System.out.println("Nature: " + bulbasaur.getNature());
		System.out.println("Type1: " + bulbasaur.getType1());
		System.out.println("Type2: " + bulbasaur.getType2());

	}

}
