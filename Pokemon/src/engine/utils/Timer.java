package engine.utils;

import engine.Action;

public class Timer {

	private Action action;
	private int counter;
	private float execute_time;

	public Timer() {
		this.action = () -> {
		};
		this.counter = 0;
		this.execute_time = 60;
	}

	public Timer(Action action) {
		this.action = action;
		this.counter = 0;
		this.execute_time = 60;
	}

	public Timer(float execute_time, Action action) {
		this.action = action;
		this.counter = 0;
		this.execute_time = 60 / execute_time;
	}

	public void update(float delta) {
		counter += delta;
		if (counter >= execute_time) {
			counter = 0;
			action.perform();
		}
	}

	public Timer setOnAction(float execute_time, Action action) {
		this.execute_time /= execute_time;
		this.action = action;
		return this;
	}

	public Timer setOnAction(Action action) {
		this.action = action;
		return this;
	}

	public Timer setExecuteTime(float execute_time) {
		this.execute_time /= execute_time;
		return this;
	}

}
