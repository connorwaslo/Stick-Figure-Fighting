package fighting.util;

/**
 * Created by Connor Waslo on 12/28/2016.
 */
public class Color {

    private float r, g, b, a;

    public static Color BLACK = new Color(0.0f, 0.0f, 0.0f);
    public static Color WHITE = new Color(1.0f, 1.0f, 1.0f);

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
        a = 1.0f;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }

    public float getA() {
        return a;
    }
}
