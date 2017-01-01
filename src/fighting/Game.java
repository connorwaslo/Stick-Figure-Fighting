package fighting;

import fighting.entities.Enemy;
import fighting.entities.Player;
import fighting.util.Input;
import fighting.level.Level;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Created by Connor Waslo on 12/24/2016.
 */
public class Game {

    private long window;
    private GLFWKeyCallback keyCallback;

    private Level level;
    /*private Player player;
    private Enemy enemy;*/

    public void run() {
        try {
            init();
            loop();
        } finally {
            glfwTerminate();
        }
    }

    private void init() {
        if (glfwInit() != GL_TRUE)
            throw new IllegalStateException("GLFW did not initialize correctly");

        // Init window
        glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
        window = glfwCreateWindow(800, 600, "Stick Figure Fighting", NULL, NULL);

        // Check Window init
        if (window == NULL)
            throw new RuntimeException("Window did not init correctly");

        // Set callbacks here
        keyCallback = new Input();
        glfwSetKeyCallback(window, keyCallback);

        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - 800) / 2, (vidmode.height() - 600) / 2);

        // Set context for OpenGL
        glfwMakeContextCurrent(window);

        glfwShowWindow(window);

        sync(60);

        // Entity initiation
        /*player = new Player(100.0f, 100.0f);
        enemy = new Enemy(600.0f, 300.0f, player);*/
        level = new Level();
    }

    private void update() {
        // Handle input
        /*player.update();
        enemy.update();*/
        level.update();

        glfwPollEvents();
    }

    private void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        /*player.render();
        enemy.render();*/
        level.render();

        glfwSwapBuffers(window);
    }

    private void loop() {
        // Register context with LWJGL3
        GL.createCapabilities();

        // Set projection
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, 800, 600, 0, -1, 1);

        // Game loop
        while (glfwWindowShouldClose(window) != GL_TRUE) {
            update();
            render();
        }
    }

    public static void main(String[] args) {
        new Game().run();
    }

    private void sync(int fps) {
        if (fps <= 0) return;

        long variableYieldTime = 1000000000, lastTime = 0;

        long sleepTime = 1000000000 / fps; // nanoseconds to sleep this frame
        // yieldTime + remainder micro & nano seconds if smaller than sleepTime
        long yieldTime = Math.min(sleepTime, variableYieldTime + sleepTime % (1000*1000));
        long overSleep = 0; // time the sync goes over by

        try {
            while (true) {
                long t = System.nanoTime() - lastTime;

                if (t < sleepTime - yieldTime) {
                    Thread.sleep(1);
                }else if (t < sleepTime) {
                    // burn the last few CPU cycles to ensure accuracy
                    Thread.yield();
                }else {
                    overSleep = t - sleepTime;
                    break; // exit while loop
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lastTime = System.nanoTime() - Math.min(overSleep, sleepTime);

            // auto tune the time sync should yield
            if (overSleep > variableYieldTime) {
                // increase by 200 microseconds (1/5 a ms)
                variableYieldTime = Math.min(variableYieldTime + 200*1000, sleepTime);
            }
            else if (overSleep < variableYieldTime - 200*1000) {
                // decrease by 2 microseconds
                variableYieldTime = Math.max(variableYieldTime - 2*1000, 0);
            }
        }
    }
}
