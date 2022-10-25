package GamePkg;

public class DriveBlock extends Entity{

    boolean pushable;
    private Direction itsDirection;

    public Direction getItsDirection() {
        return itsDirection;
    }
    public DriveBlock(int x, int y, Direction direction)
    {
        this.x = x;
        this.y = y;
        pushable = true;
        itsDirection = direction;
        collision = true;
        animation = true;
        identifier = 6;
        move = false;
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
