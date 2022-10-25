package GamePkg;

public class PushBlock extends Entity{
    boolean pushable;
    Direction direction;

    public PushBlock(int x, int y)
    {
        direction = Direction.ANY;
        this.x = x;
        this.y = y;
        pushable = true;
        collision = true;
        animation = false;
        identifier = 2;
        move = false;
    }

    public void push(Direction direction)
    {
        if(direction == Direction.LEFT)
        {
            pushable = false;
            this.y -= 1;
        }
        else if(direction == Direction.RIGHT)
        {
            pushable = false;
            this.y += 1;
        }
        else if(direction == Direction.UP)
        {
            pushable = false;
            this.x -= 1;
        }
        else if(direction == Direction.DOWN)
        {
            pushable = false;
            this.x += 1;
        }
    }

    public boolean isPushable() {
        return pushable;
    }

    @Override
    void updatePosition() {
        if(direction == Direction.LEFT)
        {
            y -= 1;
        }
        else if(direction == Direction.RIGHT)
        {
            y += 1;
        }
        else if(direction == Direction.UP)
        {
            x += 1;
        }
        else if(direction == Direction.DOWN)
        {
            x -= 1;
        }
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
}
