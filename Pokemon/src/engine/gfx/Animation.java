package engine.gfx;

public class Animation {

	private TileSheet sheet;
	private int row, amount;
	private int pointer;

	private double elapsed_time;
	private double current_time;
	private double last_time;
	private double fps;

	public Animation(int row, int amount, TileSheet sheet, int fps) {
		this.row = row;
		this.amount = amount;
		this.pointer = 0;
		this.elapsed_time = 0;
		this.current_time = 0;
		this.last_time = (double) System.nanoTime() / (double) 1000000000L;
		this.fps = 1.0d / fps;
		this.sheet = sheet;
	}

	public void bind(Shader shader) {
		this.current_time = (double) System.nanoTime() / (double) 1000000000L;
		this.elapsed_time += current_time - last_time;
		if (elapsed_time >= fps) {
			elapsed_time = 0;
			pointer++;
		}
		if (pointer >= amount) {
			pointer = 0;
		}
		this.last_time = current_time;
		sheet.bindTile(shader, pointer, row);
	}

}
