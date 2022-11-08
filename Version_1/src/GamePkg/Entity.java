package GamePkg;

import java.util.ArrayList;

public abstract class Entity {
    int x;
    int y;
    int lastX;
    int lastY;
    boolean move;
    int identifier;
    boolean animation;
    boolean collision;
    boolean visible = true;
    public Direction itsDirection = Direction.ANY;
    public Direction itsLastDirection = Direction.RIGHT;
    public boolean activated;

    abstract void updatePosition(int[][] map, ArrayList<Entity> entities);
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public int getLastX() {
        return lastX;
    }

    public int getLastY(){
        return lastY;
    }
    public boolean isMove(){
        return move;
    }
    public int getIdentifier(){
        return identifier;
    }
    public boolean isAnimated(){
        return animation;
    }
    abstract boolean isCollision();

    public boolean isVisible(){
        return visible;
    }
    public void setVisible(boolean visible){
        this.visible = visible;
    }
    public void push(Direction direction){
        this.itsDirection = direction;
    }
    abstract boolean isPushable();
    abstract int getTeleportationIdentifier();

}
