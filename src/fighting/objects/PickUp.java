package fighting.objects;

/**
 * Created by Connor Waslo on 1/8/2017.
 */
public abstract class PickUp extends Object {

    private boolean isActive;

    protected void activate() {
        isActive = true;
    }

    protected void deactivate() {
        isActive = false;
    }
}
