package GamePkg;

import java.util.ArrayList;

/**
 * @author lucas
 * @author thibaud
 * Classe permettant de créer un block cassable
 * @see Entity
 */
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
