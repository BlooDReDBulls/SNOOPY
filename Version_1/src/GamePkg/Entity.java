package GamePkg;

public interface Entity {
    int x = 0;
    int y = 0;
    int identifier = 0;
    boolean animation = false;
    boolean traversable = false;
    public void updatePosition();
    public void setX(int x);
    public void setY(int y);
    public void setIdentifier(int identifier);
    public void setAnimation(boolean animation);
    public void setTraversable(boolean traversable);
    public int getX();
    public int getY();
    public int getIdentifier();
    public boolean getAnimation();
    public boolean getTraversable();
}
