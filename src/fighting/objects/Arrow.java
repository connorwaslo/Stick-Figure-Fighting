package fighting.objects;

import fighting.objects.entities.Enemy;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Connor Waslo on 1/8/2017.
 */
public class Arrow extends Projectile {

    private static final float VELOCITY = 0.5f;

    public Arrow(float x, float y, int dir) {
        super(x, y, 20f, 4f, VELOCITY, dir);
        this.width = 20f;
        this.height = 4f;
        this.range = 500;
        this.damage = 3;
    }

    public void update() {
        x += VELOCITY * dir;

        for (Enemy e : enemies) {
            if (isColliding(e)) {
                e.markForDeath();
            }
        }
    }

    public void render() {
        glBegin(GL_TRIANGLES);
        glColor3f(0.48f, 0.32f, 0.19f);
            glVertex2f(x, y);

            glColor3f(1f, 1f, 1f);
            glVertex2f(x + width * dir, y + (height / 2));

            glColor3f(0.48f, 0.32f, 0.19f);
            glVertex2f(x, y + height);
        glEnd();
    }
}
