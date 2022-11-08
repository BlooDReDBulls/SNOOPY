package GamePkg;

import java.util.ArrayList;

public class TeleportationBlock extends Entity{

    int teleportationIdentifier;

    public TeleportationBlock(int x, int y, int teleportationIdentifier)
    {
        this.x = x;
        this.y = y;
        this.teleportationIdentifier = teleportationIdentifier;
        identifier = 5;
        animation = true;
        collision = false;
        move = false;
        activated = true;
        itsDirection = Direction.UP;
        itsLastDirection = Direction.UP;
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
        return teleportationIdentifier;
    }
}
