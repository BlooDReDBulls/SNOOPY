package GamePkg;

import java.util.ArrayList;

public class DriveBlock extends Entity{

    boolean pushable;
    public DriveBlock(int x, int y, Direction direction)
    {
        this.x = x;
        this.y = y;
        pushable = true;
        itsDirection = direction;
        collision = false;
        animation = true;
        identifier = 6;
        move = false;
        visible = true;
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
