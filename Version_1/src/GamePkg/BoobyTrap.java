package GamePkg;

public class BoobyTrap extends Entity{

    public BoobyTrap(int x, int y)
    {
        this.x = x;
        this.y = y;
        collision = true;
        animation = false;
        identifier = 3;
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
    void updatePosition() {

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


}
