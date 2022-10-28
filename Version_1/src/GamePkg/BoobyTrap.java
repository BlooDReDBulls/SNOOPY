package GamePkg;

import java.util.ArrayList;

public class BoobyTrap extends Entity{

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
    public int getX()
    {
        return x;
    }
    @Override
    public int getY()
    {
        return y;
    }

    @Override
    int getLastX() {
        return 0;
    }

    @Override
    int getLastY() {
        return 0;
    }

    @Override
    boolean isMove() {
        return move;
    }


    @Override
    void updatePosition(int[][] map, ArrayList<Entity> entities) {

    }

    @Override
    int getIdentifier() {
        return identifier;
    }

    @Override
    boolean isAnimated() {
        return animation;
    }

    @Override
    boolean isCollision() {
        return collision;
    }

    @Override
    boolean isVisible() {
        return false;
    }

    @Override
    void setVisible(boolean visible) {

    }

    @Override
    void push(Direction direction) {

    }

    @Override
    boolean isPushable() {
        return false;
    }


}
