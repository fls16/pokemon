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
	    String tile_splitter = ",";
	    String new_line = "\n";

	    StringBuilder data = new StringBuilder();
	    data.append(level.getWidth()).append(seperator).append(level.getHeight()).append(new_line);

	    for (int y = 0; y < level.getHeight(); y++) {
		StringBuilder row_data = new StringBuilder();
		for (int x = 0; x < level.getWidth(); x++) {
		    row_data.append(level.getPrimaryTileAt(x, y).getId());
		    if (level.getSecondaryTileAt(x, y).getId() != 0) {
			row_data.append(tile_splitter).append(level.getSecondaryTileAt(x, y).getId()).append(seperator);
		    }

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
	String tile_splitter = ",";
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
		    String data = id_data[x];
		    if (data.contains(tile_splitter)) {
			String[] tempValues = id_data[x].split(tile_splitter);
			short primaryID = Short.parseShort(tempValues[0]);
			short SecoundaryID = Short.parseShort(tempValues[1]);
			level.setPrimaryTile(Tile.tiles[primaryID], x, y);
			level.setSecondaryTile(Tile.tiles[SecoundaryID], x, y);
		    } else {
			short primaryID = Short.parseShort(id_data[x]);
			level.setPrimaryTile(Tile.tiles[primaryID], x, y);
		    }
		}

	    }
	    System.out.println(level.getSecondaryTileAt(1, 1));

	} catch (IOException e) {
	    e.printStackTrace();
	}

	return level;
    }

}
