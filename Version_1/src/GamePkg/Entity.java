package GamePkg;

public abstract class Entity {
    int x;
    int y;
    int lastX;
    int lastY;
    boolean move;
    int identifier;
    boolean animation;
    boolean collision;
    boolean visible;
    abstract void updatePosition(int[][] map, PushBlock pushBlock);
    abstract int getX();
    abstract  int getY();
    abstract int getLastX();
    abstract int getLastY();
    abstract boolean isMove();
    abstract int getIdentifier();
    abstract boolean isAnimated();
    abstract boolean isCollision();
    abstract boolean isVisible();
    abstract void setVisible(boolean visible);
    abstract void push(Direction direction);
    abstract boolean isPushable();
}
