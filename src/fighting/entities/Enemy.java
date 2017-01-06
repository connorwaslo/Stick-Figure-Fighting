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
        setColor(1f, 0f, 0f);

        this.target = target;

        velX = 0.03f;
        velY = 0.03f;
    }

    @Override
    public void update() {
        if (isColliding(target))
            checkCollisionDirection(target);

        if (target.getX() < x && canMoveLeft) {
               x -= velX;
           } else if (target.getX() > x && canMoveRight) {
               x += velX;
           }

        if (target.getY() < y && canMoveUp) {
            y -= velY;
        } else if (target.getY() > y && canMoveDown) {
            y += velY;
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
