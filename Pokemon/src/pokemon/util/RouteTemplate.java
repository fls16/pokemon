package pokemon.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pokemon.util.Route.Encounter;

public class RouteTemplate {

    public static void init() {
	initAreaMap(1);
	initAreaMap(2);
	initAreaMap(3);
    }

    private static void initAreaMap(int route_id) {
	try (BufferedReader br = new BufferedReader(new FileReader("src/pokemon/data/route_" + route_id + ".csv"))) {
	    br.readLine();
	    int temp_area_id = Integer.parseInt(br.readLine().substring(1, 2));
	    int area_id = temp_area_id;
	    int weather = Integer.parseInt(br.readLine().substring(1, 2));

	    Route route = new Route();
	    route.name = "route" + route_id;
	    List<Encounter> encounter_list = new ArrayList<>();
	    Encounter encounter;
	    String line;

	    while ((line = br.readLine()) != null) {
		if (line.startsWith("#")) {
		    temp_area_id = Integer.parseInt(line.substring(1, 2));
		} else if (line.startsWith("-")) {
		    if (weather == 0) {
			route.area_map_day.put(area_id, encounter_list);
		    } else if (weather == 1) {
			route.area_map_night.put(area_id, encounter_list);
		    } else if (weather == 2) {
			route.area_map_sunlight.put(area_id, encounter_list);
		    } else if (weather == 3) {
			route.area_map_rain.put(area_id, encounter_list);
		    } else if (weather == 4) {
			route.area_map_sandstorm.put(area_id, encounter_list);
		    } else if (weather == 5) {
			route.area_map_hail.put(area_id, encounter_list);
		    } else if (weather == 6) {
			route.area_map_fog.put(area_id, encounter_list);
		    }

		    area_id = temp_area_id;
		    weather = Integer.parseInt(line.substring(1, 2));
		    encounter_list = new ArrayList<>();
		} else {
		    String[] parameters = line.split(";");

		    encounter = new Encounter();
		    encounter.chance = Integer.parseInt(parameters[0]);
		    encounter.pokemon = Integer.parseInt(parameters[1]);
		    encounter.level1 = Integer.parseInt(parameters[2]);
		    if (parameters.length == 4) {
			encounter.level2 = Integer.parseInt(parameters[3]);
		    }

		    encounter_list.add(encounter);
		}
	    }

	    GlobalData.route_map.put(route_id, route);

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
