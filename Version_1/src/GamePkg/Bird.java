package GamePkg;

import java.util.ArrayList;

/**
 * @author lucas
 * @author thibaud
 * Classe permettant de créer un oiseau
 * @see Entity
 */
public class Bird extends Entity {
    /**
     * Constructeur de la classe Bird
     * @param direction direction de l'oiseau pour son animation
     * @param x coordonnée x de l'oiseau
     * @param y coordonnée y de l'oiseau
     */
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
