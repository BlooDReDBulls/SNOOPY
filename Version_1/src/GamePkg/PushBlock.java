package GamePkg;

public class PushBlock extends Block{
    boolean pushable;

    public PushBlock(int x, int y)
    {
        super(x, y);
        pushable = true;
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
}
