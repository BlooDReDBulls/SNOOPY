package GamePkg;

public class PushBlock extends Block{
    boolean pushable;

    public PushBlock(int x, int y)
    {
        super();
        this.x = x;
        this.y = y;
        pushable = true;
    }

    public void push(String direction)
    {
        if(direction == "Left")
        {
            pushable = false;
            this.x -= 1;
        }
        else if(direction == "Right")
        {
            pushable = false;
            this.x += 1;
        }
        else if(direction == "Up")
        {
            pushable = false;
            this.y -= 1;
        }
        else
        {
            pushable = false;
            this.y -= 1;
        }
    }

    public boolean isPushable() {
        return pushable;
    }
}
