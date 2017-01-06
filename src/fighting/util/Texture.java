package fighting.util;

import de.matthiasmann.twl.utils.PNGDecoder;

import javax.imageio.ImageIO;
import java.io.*;
import java.nio.ByteBuffer;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Connor Waslo on 1/1/2017.
 */
public class Texture {

    private int id;
    private int width, height;
    private int[][] colorVals;

    public Texture(String path) {
        set(path);
        colorVals = getPixels(path);
    }

    public void set(String path) {
        try {
            InputStream in = new FileInputStream(path);

            try {
                PNGDecoder decoder = new PNGDecoder(in);

                width = decoder.getWidth();
                height = decoder.getHeight();

                ByteBuffer buf = ByteBuffer.allocateDirect(4 * width * height);
                decoder.decode(buf, 4 * width, PNGDecoder.Format.RGBA);
                buf.flip();

                id = glGenTextures();
                glBindTexture(GL_TEXTURE_2D, id);

                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0,
                        GL_RGBA, GL_UNSIGNED_BYTE, buf);

                glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
                glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            } catch(IOException e) {
                e.printStackTrace();
            }

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }

    // Used to get edges of photo with edge detection applied
    // This assumes that edges will be marked before hand with black (0, 0, 0 : r, g, b)
    // But this method will be too inefficient, so assume it's a placeholder
    private int[][] getPixels(String path) {
        BufferedImage img;
        int[] pixels;

        try {
            img = ImageIO.read(new File(path));

            pixels = new int[img.getHeight() * img.getWidth()];
            img.getRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth());

            for (int r = 0; r < img.getHeight(); r++) {
                for (int c = 0; c < img.getWidth(); c++) {
                    int pixel = pixels[r * img.getWidth() + c];

                    int red = (pixel >> 16) & 0xFF;
                    int green = (pixel >> 8) & 0xFF;
                    int blue = pixel & 0xFF;

                    pixels[r * img.getWidth() + c] = red + green + blue;
                }
            }

            // Conversion to 2D array
            int[][] pic = new int[img.getHeight()][img.getWidth()];
            for (int r = 0; r < pic.length; r++) {
                for (int c = 0; c < pic[0].length; c++) {
                    pic[r][c] = pixels[r * pic[0].length + c];
                }
            }

            return pic;
        } catch (IOException e) {
            e.printStackTrace();
        }

        throw new ArrayIndexOutOfBoundsException("Shit's fucked in the getBlackPixels method, man.");
    }

    public int[][] getColorVals() {
        return colorVals;
    }
}
