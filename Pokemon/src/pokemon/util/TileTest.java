package pokemon.util;

import java.util.List;

import pokemon.util.Region.Encounter;

public class TileTest {

    public int region = 1;
    public int area = 2;
    List<Encounter> encounter_map = GlobalData.region_map.get(region).getEncounterList(area);

}