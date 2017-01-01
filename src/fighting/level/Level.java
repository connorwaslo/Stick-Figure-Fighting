package fighting.level;

import fighting.entities.Enemy;
import fighting.entities.Player;

import java.util.ArrayList;

/**
 * Created by Connor Waslo on 12/30/2016.
 */
public class Level {

    private ArrayList<Enemy> enemies;
    private Player player;

    public Level() {
        enemies = new ArrayList<>();

        player = new Player(100.0f, 100.0f, enemies);

        enemies.add(new Enemy(400.0f, 400.0f, player));
    }

    public void update() {
        for (Enemy e : enemies) {
            e.update();
        }

        player.update();
    }

    public void render() {
        for (Enemy e : enemies) {
            e.render();
        }

        player.render();
    }
}
