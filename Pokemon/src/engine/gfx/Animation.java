package engine.gfx;

public class Animation extends Graphic {

    private int amount;
    private boolean repeat;
    private boolean running;
    private int[][] xy;
    private int pointer;
    private double elapsed_time;
    private double current_time;
    private double last_time;
    private double fps;

    public Animation(int[][] xy, TileSheet sheet, int fps) {
	super(xy[0][0], xy[1][0], sheet);
	this.xy = xy;
	if (xy[0].length == xy[1].length) {
	    this.amount = xy[0].length;
	} else {
	    throw new IllegalArgumentException("Arrays do not match in lenght!");
	}
	this.repeat = true;
	this.running = true;
	this.pointer = 0;
	this.elapsed_time = 0;
	this.current_time = 0;
	this.last_time = (double) System.nanoTime() / (double) 1000000000L;
	this.fps = 1.0d / fps;
    }

    public Animation(int[][] xy, int width, int height, TileSheet sheet, int fps) {
	super(xy[0][0], xy[1][0], width, height, sheet);
	this.xy = xy;
	if (xy[0].length == xy[1].length) {
	    this.amount = xy[0].length;
	} else {
	    throw new IllegalArgumentException("Arrays do not match in lenght!");
	}
	this.pointer = 0;
	this.elapsed_time = 0;
	this.current_time = 0;
	this.last_time = (double) System.nanoTime() / (double) 1000000000L;
	this.fps = 1.0d / fps;
    }

    @Override
    public void update() {
	if (running) {
	    this.current_time = (double) System.nanoTime() / (double) 1000000000L;
	    this.elapsed_time += current_time - last_time;
	    if (elapsed_time >= fps) {
		elapsed_time = 0;
		pointer++;
	    }
	    if (pointer >= amount) {
		pointer = 0;
		if (!repeat) {
		    running = false;
		}
	    }
	    this.last_time = current_time;
	    this.x = xy[0][pointer];
	    this.y = xy[1][pointer];
	}
    }

    public Animation repeat(boolean repeat) {
	this.repeat = repeat;
	return this;
    }

    public Animation running(boolean running) {
	this.running = running;
	return this;
    }

    public void reset() {
	this.pointer = 0;
    }

}
