package fighting.objects.entities;

import fighting.objects.Block;
import fighting.objects.Entity;

import java.util.ArrayList;

import static fighting.level.Level.GRAVITY;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 * Created by Connor Waslo on 12/29/2016.
 */
public class Enemy extends Entity {

    private static final float WIDTH = 40.0f, HEIGHT = WIDTH * 2;
    private Entity target;
    private ArrayList<Block> blocks;

    private boolean willDie = false;

    public Enemy(float x, float y, Entity target, ArrayList<Block> blocks) {
        super(x, y, WIDTH, HEIGHT);
        setColor(1f, 0f, 0f);
        setHealth(2);
        setDamage(1);

        this.target = target;
        this.blocks = blocks;

        velX = 0.03f;
        velY = 0.03f;
    }

    @Override
    public void update() {
        for (Block b : blocks) {
            if (isColliding(b)) {
                checkCollisionDirection(b);
                isCollidingBlock = true;
            }
        }

        if (isColliding(target)) {
            checkCollisionDirection(target);
            isCollidingEntity = true;
        }

        // If not colliding with ANYTHING
        if (!isCollidingEntity && !isCollidingBlock) {
            canMoveLeft = true;
            canMoveRight = true;
            canMoveUp = true;
            canJump = false;
            falling = true;
        }

        if (target.getX() < x && canMoveLeft) {
            x -= velX;
        } else if (target.getX() > x && canMoveRight) {
            x += velX;
        }

        if (!canMoveLeft || !canMoveRight) {
            if (!canMoveDown && !falling) {
                jump();
            }
        }

        if (falling) {
            y += velY;
            velY += GRAVITY;
        }

        // Set all possible movement to true prior to next loop (subject to change based on collision next iteration)
        canMoveLeft = true;
        canMoveRight = true;
        canMoveUp = true;
        canMoveDown = true;

        isCollidingBlock = false;
        isCollidingEntity = false;
    }

    @Override
    public void render() {
        glColor3f(getColor().getR(), getColor().getG(), getColor().getB());
        glBegin(GL_QUADS);
            glVertex2f(x, y);
            glVertex2f(x + width, y);
            glVertex2f(x + width, y + height);
            glVertex2f(x, y + height);
        glEnd();
    }

    public void markForDeath() {
        willDie = true;
    }

    public boolean isAlive() {
        if (willDie)
            return false;

        return true;
    }
}
