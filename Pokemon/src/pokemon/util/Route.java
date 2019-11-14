package pokemon.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Route {

    public enum Weather {
	CLEAR, SUNLIGHT, RAIN, SANDSTORM, HAIL, FOG
    }

    public Weather weather = Weather.CLEAR;
    public String name;

    public static class Encounter {
	public int chance;
	public int pokemon;
	public int level1;
	public int level2;
    }

    public Map<Integer, List<Encounter>> area_map_day = new HashMap<>();
    public Map<Integer, List<Encounter>> area_map_night = new HashMap<>();
    public Map<Integer, List<Encounter>> area_map_sunlight = new HashMap<>();
    public Map<Integer, List<Encounter>> area_map_rain = new HashMap<>();
    public Map<Integer, List<Encounter>> area_map_sandstorm = new HashMap<>();
    public Map<Integer, List<Encounter>> area_map_hail = new HashMap<>();
    public Map<Integer, List<Encounter>> area_map_fog = new HashMap<>();

    public List<Encounter> getEncounterList(int area) {
	switch (weather) {
	case CLEAR:
	    return GlobalData.day == true ? area_map_day.get(area) : area_map_night.get(area);
	case SUNLIGHT:
	    return area_map_sunlight.get(area);
	case RAIN:
	    return area_map_rain.get(area);
	case SANDSTORM:
	    return area_map_sandstorm.get(area);
	case HAIL:
	    return area_map_hail.get(area);
	case FOG:
	    return area_map_fog.get(area);
	default:
	    return area_map_day.get(area);
	}
    }

}