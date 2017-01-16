package fighting.objects;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Connor Waslo on 1/2/2017.
 */
public class Block extends Object {

    private int[][] coords;
    //private int width, height;

    public Block(int r1, int c1, int width, int height) {
        coords = new int[2][2];
        x = c1;
        y = r1;

        this.width = width;
        this.height = height;
    }

    public Block() {
        coords = new int[2][2];
        coords[0][0] = -1;
        coords[0][1] = -1;

        width = 0;
        height = 0;
    }

    public void render() {
        glColor4f(0f, 0f, 0f, 1f);
        glBegin(GL_QUADS);
            glVertex2f(x, y);
            glVertex2f(x + width, y);
            glVertex2f(x + width, y + height);
            glVertex2f(x, y + height);
        glEnd();
    }

    public void setInitCoord(int r, int c) {
        coords[0][0] = r;
        coords[0][1] = c;
    }

    public void setEndCoord(int r, int c) {
        coords[1][0] = r;
        coords[1][1] = c;
    }

    public int[] getInitCoords() {
        int[] coord = {coords[0][0], coords[0][1]};
        return coord;
    }

    public int[] getEndCoords() {
        int[] coord = {coords[1][0], coords[1][1]};
        return coord;
    }

    public boolean contains(int r, int c) {
        if (r > coords[0][0] && r < coords[1][0])
            if (c > coords[0][1] && c < coords[1][1])
                return true;

        return false;
    }
}
