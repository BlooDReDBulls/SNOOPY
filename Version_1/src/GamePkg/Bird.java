package GamePkg;

import java.util.ArrayList;

public class Bird extends Entity {
    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
        animation = true;
        collision = false;
        identifier = 9;
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
        return 0;
    }

    @Override
    int getLastY() {
        return 0;
    }

    @Override
    boolean isMove() {
        return false;
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
