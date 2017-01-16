package fighting.objects;

import fighting.util.Color;

/**
 * Created by Connor Waslo on 1/6/2017.
 */
public abstract class Object {

    protected float x, y;
    protected float width, height;
    protected Color color;

    public boolean isColliding(Object target) {
        // Check collision
        if (x < target.x + target.width && x + width > target.x &&
                y < target.y + target.height && y + height > target.y) {
            return true;
        }

        return false;
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
