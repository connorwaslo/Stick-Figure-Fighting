package fighting.entities;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 * Created by Connor Waslo on 12/29/2016.
 */
public class Enemy extends Entity {

    private static final float WIDTH = 40.0f, HEIGHT = WIDTH * 2;
    private Entity target;

    public Enemy(float x, float y, Entity target) {
        super(x, y, WIDTH, HEIGHT);
        setColor(1.0f, 0.0f, 0.0f);

        this.target = target;

        velX = 0.01f;
        velY = 0.01f;
    }

    @Override
    public void update() {
        if (!isColliding(target)) {
            if (target.getX() < x) {
                x -= velX;
            } else if (target.getX() > x) {
                x += velX;
            }

            if (target.getY() < y) {
                y -= velY;
            } else if (target.getY() > y) {
                y += velY;
            }
        }
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
}
