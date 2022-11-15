package GamePkg;

import java.util.ArrayList;

/**
 * @author lucas
 * @author thibaud
 * Classe permettant de créer un block piège
 * @see Entity
 */
public class BoobyTrap extends Entity{

    /**
     * Constructeur de la classe BoobyTrap
     * @param x coordonnée x du piège
     * @param y coordonnée y du piège
     */
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
