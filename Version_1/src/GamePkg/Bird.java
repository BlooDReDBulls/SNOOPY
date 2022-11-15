package GamePkg;

import java.util.ArrayList;

public class Bird extends Entity {
    public Bird(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        animation = true;
        itsDirection = direction;
        collision = false;
        identifier = 9;
        move = false;
        animation = true;
        itsLastDirection = Direction.RIGHT;
        itsDirection = Direction.LEFT;
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
