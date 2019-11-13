package pokemon.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Region {

    public enum Weather {
	CLEAR, SUNLIGHT, RAIN, SANDSTORM, HAIL, FOG
    }

    public Weather weather = Weather.CLEAR;

    public class Encounter {
	public int chance;
	public int pokemon;
	public int level;
    }

    public Map<Integer, List<Encounter>> area_map_day = new HashMap<>();
    public Map<Integer, List<Encounter>> area_map_night = new HashMap<>();
    public Map<Integer, List<Encounter>> area_map_sunlight = new HashMap<>();
    public Map<Integer, List<Encounter>> area_map_rain = new HashMap<>();
    public Map<Integer, List<Encounter>> area_map_sandstorm = new HashMap<>();
    public Map<Integer, List<Encounter>> area_map_hail = new HashMap<>();
    public Map<Integer, List<Encounter>> area_map_fog = new HashMap<>();

    public void init() {
	List<String> weather_conditions = new ArrayList<>();
	weather_conditions.add("day");
	weather_conditions.add("night");
	weather_conditions.add("sunlight");
	weather_conditions.add("rain");
	weather_conditions.add("sandstorm");
	weather_conditions.add("hail");
	weather_conditions.add("fog");
	for (String weather : weather_conditions) {
	    initEncounterMap(weather);
	}
    }

    private void initEncounterMap(String weather) {
	try (BufferedReader br = new BufferedReader(new FileReader("src/pokemon/data/spawnmap_" + weather + ".csv"))) {
	    br.readLine();
	    Region region = new Region();
	    List<Encounter> encounter_list = new ArrayList<>();
	    Encounter encounter;
	    int region_id;
	    int temp_region_id = 1; // starting value of our regions is "1"
	    int area_id;
	    int temp_area_id = 1; // starting value of our areas is "1"
	    String line;

	    while ((line = br.readLine()) != null) {
		encounter = new Encounter();
		String[] parameters = line.split(";");
		region_id = Integer.parseInt(parameters[0]);
		area_id = Integer.parseInt(parameters[1]);

		// if new region begins
		if (region_id != temp_region_id) {
		    if (weather.equals("day")) {
			region.area_map_day.put(temp_area_id, encounter_list);
		    } else if (weather.equals("night")) {
			region.area_map_night.put(temp_area_id, encounter_list);
		    } else if (weather.equals("sunlight")) {
			region.area_map_sunlight.put(temp_area_id, encounter_list);
		    } else if (weather.equals("rain")) {
			region.area_map_rain.put(temp_area_id, encounter_list);
		    } else if (weather.equals("sandstorm")) {
			region.area_map_sandstorm.put(temp_area_id, encounter_list);
		    } else if (weather.equals("hail")) {
			region.area_map_hail.put(temp_area_id, encounter_list);
		    } else if (weather.equals("fog")) {
			region.area_map_fog.put(temp_area_id, encounter_list);
		    }

		    GlobalData.region_map.put(temp_region_id, region);

		    region = new Region();
		    temp_region_id = region_id;

		    encounter_list = new ArrayList<>();
		    temp_area_id = area_id;
		}
		// if new area begins
		if (area_id != temp_area_id) {
		    if (weather.equals("day")) {
			region.area_map_day.put(temp_area_id, encounter_list);
			// System.out.println(region.area_map_day.get(1)); TODO
		    } else if (weather.equals("night")) {
			region.area_map_night.put(temp_area_id, encounter_list);
		    } else if (weather.equals("sunlight")) {
			region.area_map_sunlight.put(temp_area_id, encounter_list);
		    } else if (weather.equals("rain")) {
			region.area_map_rain.put(temp_area_id, encounter_list);
		    } else if (weather.equals("sandstorm")) {
			region.area_map_sandstorm.put(temp_area_id, encounter_list);
		    } else if (weather.equals("hail")) {
			region.area_map_hail.put(temp_area_id, encounter_list);
		    } else if (weather.equals("fog")) {
			region.area_map_fog.put(temp_area_id, encounter_list);
		    }

		    encounter_list = new ArrayList<>();
		    temp_area_id = area_id;
		}

		encounter.chance = Integer.parseInt(parameters[2]);
		encounter.pokemon = Integer.parseInt(parameters[3]);
		encounter.level = Integer.parseInt(parameters[4]);
		encounter_list.add(encounter);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

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