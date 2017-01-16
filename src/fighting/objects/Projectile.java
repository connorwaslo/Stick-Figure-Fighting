package fighting.objects;

import fighting.objects.entities.Enemy;
import fighting.objects.entities.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Connor Waslo on 1/8/2017.
 */
public abstract class Projectile extends Object {

    protected float vel;
    protected int dir;
    protected int range;
    protected int damage;

    protected ArrayList<Enemy> enemies;
    protected ArrayList<Block> blocks;
    protected Player player;

    public Projectile(float x, float y, float width, float height, float vel, int dir) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.vel = vel;
        this.dir = dir;
    }

    public void setEnviroment(ArrayList<Enemy> enemies, ArrayList<Block> blocks, Player player) {
        this.enemies = enemies;
        this.blocks = blocks;
        this.player = player;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getRange() {
        return range;
    }
}
