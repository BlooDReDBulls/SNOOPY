package GamePkg;

import java.util.ArrayList;

public class BreakableBlock extends Entity{
    public BreakableBlock(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.identifier = 1;
        collision = true;
        animation = false;
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
    boolean isPushable() {
        return false;
    }

    @Override
    int getTeleportationIdentifier() {
        return 0;
    }
}
