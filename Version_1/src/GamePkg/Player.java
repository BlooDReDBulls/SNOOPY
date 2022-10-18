package GamePkg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

enum Direction {
    UP,
    DOWN,
    RIGHT,
    LEFT,
    ANY
}

public class Player{
    private int x;
    private int y;

    public Direction itsDirection;

    public Direction itsLastDirection;

    public Player()
    {
        itsDirection = Direction.ANY;
        x = 5;
        y = 5;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void updatePosition()
    {
        if(itsDirection == Direction.UP)
        {
            if((y - 1) >= 0)
            {
                y -= 1;
            }
        }
        else if(itsDirection == Direction.DOWN)
        {
            if((y + 1) <= 19)
            {
                y += 1;
            }
        }
        else if(itsDirection == Direction.RIGHT)
        {
            if((x + 1) <= 9)
            {
                x += 1;
            }
        }
        else if(itsDirection == Direction.LEFT)
        {
            if((x - 1) >= 0)
            {
                x -= 1;
            }
        }
    }
}
