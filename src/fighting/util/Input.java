package fighting.util;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Created by Connor Waslo on 12/28/2016.
 */
public class Input extends GLFWKeyCallback {

    public static boolean[] keys = new boolean[266];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action != GLFW_RELEASE;
    }
}
