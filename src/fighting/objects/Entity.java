package fighting.objects;

import fighting.objects.Object;

/**
 * Created by Connor Waslo on 12/28/2016.
 */
public abstract class Entity extends Object {

    protected float velX, velY;
    protected int health;
    protected int damage;

    protected boolean canMoveLeft, canMoveRight, canMoveUp, canMoveDown;
    protected boolean canJump, falling;
    protected boolean isCollidingEntity, isCollidingBlock;

    public Entity(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        canMoveLeft = true;
        canMoveRight = true;
        canMoveUp = true;
        canMoveDown = true;
        canJump = true;
        falling = false;
        isCollidingEntity = false;
        isCollidingBlock = false;
    }

    public abstract void update();
    public abstract void render();

    public void checkCollisionDirection(Object target) {
        // Check left
        if (x >= target.x + (target.width - 1)/* && !jumping*/) {
            canMoveLeft = false;
            if (canMoveDown)
                falling = true;
        }
        // Check right
        else if (x + width - 1 <= target.x/* && !jumping*/) {
            canMoveRight = false;
            if (canMoveDown)
                falling = true;
        }

        // Check up
        else if (y > target.y + (target.height - 1)) {
            canMoveUp = false;
            if (falling) {
                velY = 0.1f;
            }
        }
        // Check down
        else if (y  + height - 1 <= target.y) {
            canMoveDown = false;
            canJump = true;
            velY = 0.1f;
            falling = false;
        }
    }

    protected void jump() {
        velY = -0.9f;
        y += velY;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int dmg) {
        damage = dmg;
    }
}
