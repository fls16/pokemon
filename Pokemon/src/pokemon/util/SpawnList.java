package pokemon.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SpawnList {

    // pokemon encounter list
    public static List<Encounter> region_1_1 = Arrays.asList();

    // util
    private class Encounter {

	private int chance;
	private int pokemon;
	private int level;

	private Encounter() {
	};

    }

    public void init() {
	try (BufferedReader br = new BufferedReader(new FileReader("src/pokemon/data/spawnlist.csv"))) {
	    br.readLine();
	    Encounter encounter;
	    String line;

	    while ((line = br.readLine()) != null) {
		String[] parameters = line.split(";");
		encounter = new Encounter();
		encounter.chance = 4;

	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
