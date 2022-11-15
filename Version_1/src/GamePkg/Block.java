package GamePkg;

import java.util.ArrayList;

/**
 * @author lucas
 * @author thibaud
 * Classe permettant de créer un block incassable
 * @see Entity
 */
public class Block extends Entity{
    /**
     * Constructeur de la classe Block
     * @param y coordonnée y du block
     * @param x coordonnée x du block
     */
    public Block(int x, int y)
    {
        this.x = x;
        this.y = y;
        animation = false;
        collision = true;
        identifier = 4;
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
