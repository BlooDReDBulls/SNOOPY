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
    public int getX() {
        return x;
    }
    @Override
    public int getY() {
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
        return visible;
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

    @Override
    int getTeleportationIdentifier() {
        return 0;
    }
}
