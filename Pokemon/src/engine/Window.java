package engine;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.GLFW_FALSE;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.glfw.GLFW.GLFW_TRUE;
import static org.lwjgl.glfw.GLFW.GLFW_VISIBLE;
import static org.lwjgl.glfw.GLFW.glfwDestroyWindow;
import static org.lwjgl.glfw.GLFW.glfwGetWindowSize;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwSwapInterval;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.system.MemoryStack.stackPush;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallbackI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import engine.input.Input;

public class Window {

	private long window;
	public int width, height;
	private String title;
	private boolean fullscreen;
	private boolean vsync;
	private Camera camera;

	private Input input;

	public Window(int width, int height, String title, boolean fullscreen, boolean vsync) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.fullscreen = fullscreen;
		this.vsync = vsync;
		this.camera = new Camera(width, height);
	}

	public void createWindow() {
		GLFW.glfwDefaultWindowHints(); // optional, the current window hints are already the default
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
		glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable

		window = GLFW.glfwCreateWindow(width, height, title, fullscreen ? GLFW.glfwGetPrimaryMonitor() : 0, 0);
		if (window == MemoryUtil.NULL)
			throw new RuntimeException("Failed to create the GLFW window");

		GLFW.glfwShowWindow(window);
		GLFW.glfwMakeContextCurrent(window);

		glfwSwapInterval(vsync ? 1 : 0);// Enable v-sync

		input = new Input(window);

		if (!fullscreen) {
			try (MemoryStack stack = stackPush()) {
				IntBuffer pWidth = stack.mallocInt(1);
				IntBuffer pHeight = stack.mallocInt(1);
				glfwGetWindowSize(window, pWidth, pHeight);
				GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
				glfwSetWindowPos(window, (vidmode.width() - pWidth.get(0)) / 2,
						(vidmode.height() - pHeight.get(0)) / 2);
			}
		}
	}

	public void update() {
		input.update();
		GLFW.glfwPollEvents();
	}

	public Input getInput() {
		return input;
	}

	public void requestClose() {
		GLFW.glfwSetWindowShouldClose(window, true);
	}

	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(window);
	}

	public void swapBuffers() {
		glfwSwapBuffers(window);
	}

	public void cleanUp() {
		glfwFreeCallbacks(window);
		glfwDestroyWindow(window);
	}

	public Camera getCamera() {
		return camera;
	}

	public void setOnResize(GLFWWindowSizeCallbackI callback) {
		GLFW.glfwSetWindowSizeCallback(window, callback);
	}

}
