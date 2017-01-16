package fighting.objects.entities;

import fighting.objects.Arrow;
import fighting.objects.Block;
import fighting.objects.Entity;
import fighting.util.Input;

import java.util.ArrayList;

import static fighting.level.Level.GRAVITY;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Connor Waslo on 12/28/2016.
 */
public class Player extends Entity {

    private static float WIDTH = 40.0f, HEIGHT = WIDTH * 2;
    private ArrayList<Enemy> enemies;
    private ArrayList<Block> blocks;
    private Arrow arrow;

    private int dir;

    public Player(float x, float y, ArrayList<Enemy> enemies, ArrayList<Block> blocks) {
        super(x, y, WIDTH, HEIGHT);
        setColor(0f, 0f, 1f);
        setHealth(10); // Arbitrary number to be determined later in game design
        setDamage(1);  // ^

        this.enemies = enemies;
        this.blocks = blocks;

        velX = 0.3f;
        dir = 1;
    }

    @Override
    public void update() {
        // Check through every enemy for collision
        for (Enemy e : enemies) {
            if (isColliding(e)) {
                checkCollisionDirection(e);
                isCollidingEntity = true;
            }
        }

        // Check through every block for collision
        for (Block b : blocks) {
            if (isColliding(b)) {
                checkCollisionDirection(b);
                isCollidingBlock = true;
            }
        }

        // If not colliding with ANYTHING
        if (!isCollidingEntity && !isCollidingBlock) {
            canMoveLeft = true;
            canMoveRight = true;
            canMoveUp = true;
            canJump = false;
            falling = true;
        }

        checkInput();

        if (falling) {
            y += velY;
            velY += GRAVITY;
        }

        if (arrow != null) {
            arrow.update();

            // Check range
            if (Math.abs((int) (arrow.getX() - x)) > arrow.getRange()) {
                arrow = null;
            }
        }

        // Set all possible movement to true prior to next loop (subject to change based on collision next iteration)
        canMoveLeft = true;
        canMoveRight = true;
        canMoveUp = true;
        canMoveDown = true;

        isCollidingBlock = false;
        isCollidingEntity = false;
    }

    private void checkInput() {
        // Movement
        if (Input.keys[GLFW_KEY_LEFT] && canMoveLeft) {
            x -= velX;
            dir = -1;
        } else if (Input.keys[GLFW_KEY_RIGHT] && canMoveRight) {
            x += velX;
            dir = 1;
        }

        if (Input.keys[GLFW_KEY_UP] && canMoveUp && canJump && !falling/* && !falling && !jumping*/) {
            jump();
        }

        // Attack
        if (Input.keys[GLFW_KEY_SPACE]) {
            arrow = new Arrow(x, y + (height / 2), dir);
            arrow.setEnviroment(enemies, blocks, this);
        }
    }

    @Override
    public void render() {
        //glColor3f(getColor().getR(), getColor().getG(), getColor().getB());
        glBegin(GL_QUADS);
            glColor3f(1f, 1f, 1f);
            glVertex2f(x, y);

            glColor3f(0f, 1f, 0f);
            glVertex2f(x + width, y);

            glColor3f(0f, 0f, 1f);
            glVertex2f(x + width, y + height);

            glColor3f(1f, 0f, 0f);
            glVertex2f(x, y + height);
        glEnd();

        if (arrow != null) {
            arrow.render();
        }
    }
}
