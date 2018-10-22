package engine.gui;

import engine.Camera;
import engine.Window;
import engine.gfx.Assets;
import engine.gfx.Shader;
import engine.gfx.TileSheet;
import engine.input.Input;
import engine.math.Vector2f;

public class Image extends GUIElement {

    public Image(float x, float y, float width, float height, TileSheet tile_sheet, int texture_x, int texture_y) {
	super(x, y, width, height);
	this.tile_sheet = tile_sheet;
	this.texture_x = texture_x;
	this.texture_y = texture_y;
    }

    @Override
    public void onUpdate(float delta, Window window, Input input) {

    }

    @Override
    public void onRender(Camera camera, Shader shader, Window window, Vector2f position) {
	tile_sheet.bindTile(shader, texture_x, texture_y);
	Assets.model.render();
    }

}
