package fighting.level;

import fighting.Game;
import fighting.objects.Block;
import fighting.objects.entities.Enemy;
import fighting.objects.entities.Player;
import fighting.util.Texture;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Connor Waslo on 12/30/2016.
 */
public class Level {

    public static final float GRAVITY = 0.003f;

    private ArrayList<Enemy> enemies;
    private Player player;

    private Texture tex;
    private String path;

    private ArrayList<Block> blocks;

    public Level() {
        enemies = new ArrayList<>();
        blocks = new ArrayList<>();
        path = "res/TestPic.png";
        tex = new Texture(path);

        player = new Player(500.0f, 100.0f, enemies, blocks);

        enemies.add(new Enemy(400.0f, 100.0f, player, blocks));
        enemies.add(new Enemy(600f, 300f, player, blocks));
        blocks.add(new Block(500, 0, 800, 100));
        blocks.add(new Block(390, 400, 130, 200));
    }

    public void update() {
        for (Enemy e : enemies) {
            e.update();
        }

        player.update();

        // Check enemies for death
        for (int i = 0; i < enemies.size(); i++) {
            if (!enemies.get(i).isAlive()) {
                enemies.remove(i);
                i--;
            }
        }
    }

    public void render() {
        // Sets texture vertices to white so that level texture is uninfluenced by proceeding quads
        glEnable(GL_TEXTURE_2D);

        tex.bind();
        glColor3f(1f, 1f, 1f);
        glBegin(GL_QUADS);
            glTexCoord2f(0, 0);
            glVertex2f(0f, 0f);

            glTexCoord2f(1, 0);
            glVertex2f(Game.WIDTH, 0f);

            glTexCoord2f(1, 1);
            glVertex2f(Game.WIDTH, Game.HEIGHT);

            glTexCoord2f(0, 1);
            glVertex2f(0f, Game.HEIGHT);
        glEnd();

        glDisable(GL_TEXTURE_2D);

        for (Block b : blocks) {
            b.render();
        }
        for (Enemy e : enemies) {
            e.render();
        }

        player.render();
    }

    // THIS ALGORITHM LOCKS UP EVERY SINGLE TIME -- TODO: ... fix it
    /*private void createBlocks(int[][] coords) {
        // Block index
        int i = 0;
        for (int r = 0; r < coords.length; r++) {
            for (int c = 0; c < coords[0].length; c++) {
                if (coords[r][c] == 0) {
                    if (i == 0) {
                        Block b = new Block();
                        i++;
                        findBlockCoords(coords, b, r, c);
                    } else if (i != 0 && !blockExists(r, c)) {
                        Block b = new Block();
                        i++;
                        findBlockCoords(coords, b, r, c);

                        // Sets r to the end coordinate
                        r = blocks.get(i - 1).getEndCoords()[0];
                        //c = blocks.get(i).getEndCoords()[1];
                    }
                }
            }
        }
    }*/

    // Recursion is hard
    private void findBlockCoords(int[][] coords, Block b, int r, int c) {
        if (b.getInitCoords()[0] == -1) {
            b.setInitCoord(r, c);
        } else {
            // Check pixel to the right
            if (coords[r][c + 1] == 0 && !blockExists(r, c + 1)) {
                findBlockCoords(coords, b, r, c + 1);
                return;
            } else {
                // Sets end column
                /*b.setEndCoord(-1, c);
*/
                // Check pixel below
                if (coords[r + 1][c] == 0 && !blockExists(r + 1, c)) {
                    findBlockCoords(coords, b, r + 1, c);
                    return;
                } else {
                    b.setEndCoord(r, c);
                }
            }
        }

        blocks.add(b);
    }

    private boolean blockExists(int r, int c) {
        for (Block b : blocks) {
            if (b.contains(r, c)) {
                return true;
            }
        }

        return false;
    }

    /*private void createBlocks(int[][] coords) {
        for (int r = 0; r < tex.getColorVals().length; r++) {
            for (int c = 0; c < tex.getColorVals()[0].length; c++) {
                findBlockCoords(coords, r, c);
            }
        }
    }

    public void findBlockCoords(int[][] coords, int r, int c) {
        //System.out.println("This is working?");
        if (blocks.size() > 0) {
            for (Block bl : blocks) {
                if (bl.contains(r, c)) {
                    System.out.println("Nope");
                    return;
                }
            }
        }

        //System.out.println("This is working?");

        // If the init coordinate is not already set
        if (coords[r][c] == 0) {
            Block b = new Block();
            if (b.getInitCoords()[0] == -1 && b.getInitCoords()[1] == -1) {
                b.setInitCoord(r, c);

                // Assuming all blocks are rects, must be black pixel directly right of initial pixel
                findBlockCoords(coords, r, c + 1);
            } else {
                // If the pixel directly right is black
                if (coords[r][c + 1] == 0) {
                    findBlockCoords(coords, r, c + 1);
                } else if (coords[r + 1][c] == 0) { // If the pixel below is black
                    //System.out.println("Searching in else statement...");
                    findBlockCoords(coords, r + 1, c);
                } else { // If this pixel is the bottom right in a black rect
                    System.out.println("Setting end pixel");
                    b.setEndCoord(r, c);
                    blocks.add(b);
                }
            }

            System.out.println("Adding block");
            System.out.println(blocks.size());
        }
    }*/
}
