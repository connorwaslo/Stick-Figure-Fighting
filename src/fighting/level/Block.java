package fighting.level;

/**
 * Created by Connor Waslo on 1/2/2017.
 */
public class Block {

    private int[][] coords;
    private int width, height;

    public Block(int r1, int c1, int r2, int c2) {
        coords = new int[2][2];
        width = c2 - c1;
        height = r2 - r1;
    }

    public Block() {
        coords = new int[2][2];
        coords[0][0] = -1;
        coords[0][1] = -1;

        width = 0;
        height = 0;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
