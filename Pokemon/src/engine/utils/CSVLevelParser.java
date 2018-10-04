package engine.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import engine.entity.Tile;
import engine.level.Level;

public class CSVLevelParser {

    public static void save(Level level) {
	String file_name = "level/" + level.getName() + ".csv";
	try (BufferedWriter bw = new BufferedWriter(new FileWriter(file_name))) {
	    String seperator = "\t";
	    String new_line = "\n";

	    StringBuilder data = new StringBuilder();
	    data.append(level.getWidth()).append(seperator).append(level.getHeight()).append(new_line);

	    for (int y = 0; y < level.getHeight(); y++) {
		StringBuilder row_data = new StringBuilder();
		for (int x = 0; x < level.getWidth(); x++) {
		    row_data.append(level.getTileAt(x, y).getId()).append(seperator);
		}
		data.append(row_data).append(new_line);
	    }

	    bw.write(data.toString());

	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public static Level load(String level_name) {
	String file_name = "level/" + level_name + ".csv";
	Level level = null;
	String line = "";
	String cvs_split_by = "\t";

	try (BufferedReader br = new BufferedReader(new FileReader(file_name))) {

	    line = br.readLine();
	    String[] meta_data = line.split(cvs_split_by);

	    int width = Integer.parseInt(meta_data[0].trim());
	    int height = Integer.parseInt(meta_data[1].trim());

	    level = new Level(level_name, width, height);

	    for (int y = 0; y < height; y++) {
		line = br.readLine();
		String[] id_data = line.split(cvs_split_by);

		for (int x = 0; x < width; x++) {
		    byte id = Byte.parseByte(id_data[x]);
		    level.setTile(Tile.tiles[id], x, y);
		}

	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}

	return level;
    }

}
