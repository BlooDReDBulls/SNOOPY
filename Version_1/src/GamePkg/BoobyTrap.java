package GamePkg;

import java.util.ArrayList;

public class BoobyTrap extends Entity{

    public BoobyTrap(int x, int y)
    {
        this.x = x;
        this.y = y;
        collision = false;
        animation = false;
        identifier = 3;
        move = false;
    }


    @Override
    void updatePosition(int[][] map, ArrayList<Entity> entities) {

    }

    @Override
    boolean isCollision() {
        return collision;
    }

    @Override
    void setPushable(boolean pushable) {

    }

    @Override
    boolean isPushable() {
        return false;
    }

    @Override
    int getTeleportationIdentifier() {
        return 0;
    }
}
