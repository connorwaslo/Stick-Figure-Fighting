package fighting.util;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

/**
 * Created by Connor Waslo on 12/28/2016.
 */
public class Input extends GLFWKeyCallback {

    // Size dictated by values of GLFW keys -- arrows highest value is 265, space is 32
    public static boolean[] keys = new boolean[266];

    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        if (key == GLFW_KEY_LEFT || key == GLFW_KEY_RIGHT)
            keys[key] = action != GLFW_RELEASE;
        else
            keys[key] = action == GLFW_PRESS;
    }
}
