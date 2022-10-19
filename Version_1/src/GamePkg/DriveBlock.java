package GamePkg;

public class DriveBlock extends Block{

    boolean pushable;
    private Direction itsDirection;

    public Direction getItsDirection() {
        return itsDirection;
    }
    public DriveBlock(int x, int y, Direction direction)
    {
        super(x, y);
        pushable = true;
        itsDirection = direction;
    }
}
