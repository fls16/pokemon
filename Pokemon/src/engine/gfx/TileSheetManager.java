package engine.gfx;

import java.util.HashMap;
import java.util.Map;

public class TileSheetManager {

    private Map<String, TileSheet> tile_sheets;

    public TileSheetManager() {
	tile_sheets = new HashMap<>();
    }

    public TileSheet getTileSheet(String name) {
	return tile_sheets.get(name);
    }

    public void addTileSheet(TileSheet tile_sheet) {
	tile_sheets.put(tile_sheet.getName(), tile_sheet);
    }

    public void removeTileSheet(String name) {
	tile_sheets.remove(name);
    }

}
