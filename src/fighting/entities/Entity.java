package fighting.entities;

import fighting.util.Color;

/**
 * Created by Connor Waslo on 12/28/2016.
 */
public abstract class Entity {

    protected float x, y;
    protected float width, height;
    private Color color;
    protected float velX, velY;

    protected boolean canMoveLeft, canMoveRight, canMoveUp, canMoveDown;

    public Entity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        canMoveLeft = true;
        canMoveRight = true;
        canMoveUp = true;
        canMoveDown = true;
    }

    public abstract void update();
    public abstract void render();

    public boolean isColliding(Entity target) {
        // Check collision
        if (x <= target.x + target.width && x + width >= target.x &&
                y <= target.y + target.height && y + height >= target.y) {
            return true;
        }

        canMoveLeft = true;
        canMoveRight = true;
        canMoveUp = true;
        canMoveDown = true;

        return false;
    }

    public void checkCollisionDirection(Entity target) {
        // Check left
        if (x >= target.x && Math.abs(target.y - y) < height - 1) {
            canMoveLeft = false;
        }
        // Check right
        else if (x <= target.x && Math.abs(target.y - y) < height - 1) {
            canMoveRight = false;
        }

        // Check up
        if (y >= target.y && Math.abs(target.x - x) <= width - 1) {
            canMoveUp = false;
        }
        // Check down
        else if (y <= target.y && Math.abs(target.x - x) <= width - 1) {
            canMoveDown = false;
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(float r, float g, float b) {
        color = new Color(r, g, b);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }
}
