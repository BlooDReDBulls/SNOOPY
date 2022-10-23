package GamePkg;

public abstract class Entity {
    int x;
    int y;
    int identifier;
    boolean animation;
    boolean collision;
    abstract void updatePosition();
    abstract int getIdentifier();
    abstract boolean isAnimated();
    abstract boolean isCollision();
}
