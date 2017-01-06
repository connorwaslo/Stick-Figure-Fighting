package fighting.entities;

import fighting.util.Input;

import java.util.ArrayList;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Connor Waslo on 12/28/2016.
 */
public class Player extends Entity {

    private static float WIDTH = 40.0f, HEIGHT = WIDTH * 2;
    private ArrayList<Enemy> enemies;

    public Player(float x, float y, ArrayList<Enemy> enemies) {
        super(x, y, WIDTH, HEIGHT);
        setColor(0f, 0f, 1f);

        this.enemies = enemies;

        velX = 0.1f;
        velY = 0.1f;
    }

    @Override
    public void update() {
        if (isColliding(enemies.get(0))) {
            checkCollisionDirection(enemies.get(0));
        }

        if (Input.keys[GLFW_KEY_LEFT] && canMoveLeft) {
            x -= velX;
            //System.out.println("Can Move Left: " + canMoveLeft);
        } else if (Input.keys[GLFW_KEY_RIGHT] && canMoveRight) {
            x += velX;
            //System.out.println("Can Move Right: " + canMoveRight);
        }

        if (Input.keys[GLFW_KEY_UP] && canMoveUp) {
            y -= velY;
            //System.out.println("Can Move Up: " + canMoveUp);
        } else if (Input.keys[GLFW_KEY_DOWN] && canMoveDown) {
            y += velY;
            //System.out.println("Can Move Down: " + canMoveDown);
        }
    }

    @Override
    public void render() {
        //glColor3f(getColor().getR(), getColor().getG(), getColor().getB());
        glColor3f(1f, 0f, 1f);
        glBegin(GL_QUADS);
            glVertex2f(x, y);
            glVertex2f(x + width, y);
            glVertex2f(x + width, y + height);
            glVertex2f(x, y + height);
        glEnd();
    }
}
