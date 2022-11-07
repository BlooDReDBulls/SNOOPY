package GamePkg;

import java.util.ArrayList;

public class Block extends Entity{
    public Block(int x, int y)
    {
        this.x = x;
        this.y = y;
        animation = false;
        collision = true;
        identifier = 4;
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
