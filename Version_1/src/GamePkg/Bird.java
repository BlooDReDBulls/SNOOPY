package GamePkg;

import java.util.ArrayList;

public class Bird extends Entity {
    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
        animation = true;
        collision = false;
        identifier = 9;
        visible = true;
        move = false;
        animation = true;
    }

    @Override
    void updatePosition(int[][] map, ArrayList<Entity> entities) {

    }

    @Override
    boolean isCollision() {
        return collision;
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
