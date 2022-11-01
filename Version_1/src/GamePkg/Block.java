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
    int getX() {
        return x;
    }

    @Override
    int getY() {
        return y;
    }

    @Override
    int getLastX() {
        return lastX;
    }

    @Override
    int getLastY() {
        return lastY;
    }

    @Override
    boolean isMove() {
        return move;
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
        this.visible = visible;
    }

    @Override
    void push(Direction direction) {

    }

    @Override
    boolean isPushable() {
        return false;
    }
}
