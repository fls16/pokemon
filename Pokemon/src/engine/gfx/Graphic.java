package engine.gfx;

public class Graphic {

    protected interface OnRender {
	public void action(Shader shader);
    }

    protected TileSheet sheet;
    protected int x, y;
    protected int width, height;
    protected OnRender onRender;

    public Graphic(int x, int y, TileSheet sheet) {
	this.x = x;
	this.y = y;
	this.sheet = sheet;
	this.onRender = shader -> sheet.bindTile(shader, this.x, this.y);
    }

    public Graphic(int x, int y, int width, int height, TileSheet sheet) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.sheet = sheet;
	this.onRender = shader -> sheet.bindTile(shader, this.x, this.y, width, height);
    }

    public void update() {

    }

    public void render(Shader shader) {
	onRender.action(shader);
    }

}
