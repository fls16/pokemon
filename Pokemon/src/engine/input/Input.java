package engine.input;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;

import engine.math.Vector2f;

public class Input {

    private long window;

    private boolean[] keys;
    private boolean[] mouse_buttons;

    public static final int W = GLFW.GLFW_KEY_W;
    public static final int A = GLFW.GLFW_KEY_A;
    public static final int S = GLFW.GLFW_KEY_S;
    public static final int D = GLFW.GLFW_KEY_D;
    public static final int SPACE = GLFW.GLFW_KEY_SPACE;
    public static final int ENTER = GLFW.GLFW_KEY_ENTER;
    public static final int E = GLFW.GLFW_KEY_E;
    public static final int Q = GLFW.GLFW_KEY_Q;
    public static final int R = GLFW.GLFW_KEY_R;
    public static final int ESC = GLFW.GLFW_KEY_ESCAPE;
    public static final int STRG = GLFW.GLFW_KEY_LEFT_CONTROL;
    public static final int L_SHIFT = GLFW.GLFW_KEY_LEFT_SHIFT;
    public static final int R_SHIFT = GLFW.GLFW_KEY_RIGHT_SHIFT;
    public static final int BACK = GLFW.GLFW_KEY_BACKSPACE;
    public static final int N1 = GLFW.GLFW_KEY_1;
    public static final int N2 = GLFW.GLFW_KEY_2;
    public static final int N3 = GLFW.GLFW_KEY_3;
    public static final int N4 = GLFW.GLFW_KEY_4;
    public static final int N5 = GLFW.GLFW_KEY_5;
    public static final int N6 = GLFW.GLFW_KEY_6;
    public static final int N7 = GLFW.GLFW_KEY_7;
    public static final int N8 = GLFW.GLFW_KEY_8;
    public static final int N9 = GLFW.GLFW_KEY_9;
    public static final int N0 = GLFW.GLFW_KEY_0;

    public static final int M1 = GLFW.GLFW_MOUSE_BUTTON_1;
    public static final int M2 = GLFW.GLFW_MOUSE_BUTTON_2;

    public Input(long window) {
	this.window = window;
	this.keys = new boolean[GLFW.GLFW_KEY_LAST];
	this.mouse_buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    }

    public void update() {
	for (int i = 32; i < GLFW.GLFW_KEY_LAST; i++) {
	    keys[i] = isKeyDown(i);
	}
	for (int i = 0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) {
	    mouse_buttons[i] = isMouseButtonDown(i);
	}
    }

    public int getKeyDown() {
	for (int i = 32; i < GLFW.GLFW_KEY_LAST; i++) {
	    if (keys[i]) {
		return i;
	    }
	}
	return 0;
    }

    public boolean isKeyDown(int key) {
	return GLFW.glfwGetKey(window, key) == 1;
    }

    public boolean isKeyPressed(int key) {
	return isKeyDown(key) && !keys[key];
    }

    public boolean isKeyReleased(int key) {
	return !isKeyDown(key) && keys[key];
    }

    public boolean isMouseButtonDown(int button) {
	return GLFW.glfwGetMouseButton(window, button) == 1;
    }

    public boolean isMouseButtonPressed(int button) {
	return isMouseButtonDown(button) && !mouse_buttons[button];
    }

    public boolean isMouseButtonReleased(int button) {
	return !isMouseButtonDown(button) && mouse_buttons[button];
    }

    public Vector2f getCursorPos() {
	DoubleBuffer posX = BufferUtils.createDoubleBuffer(1);
	DoubleBuffer posY = BufferUtils.createDoubleBuffer(1);
	GLFW.glfwGetCursorPos(window, posX, posY);
	return new Vector2f((float) posX.get(), (float) posY.get());
    }

}
