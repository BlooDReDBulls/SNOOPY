package GamePkg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player{
    int x;
    int y;

    public Player()
    {
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

    public void left()
    {
        if((x - 1) >= 0)
        {
            x -= 1;
        }
    }

    public void right()
    {
        if((x + 1) <= 9)
        {
            x += 1;
        }
    }

    public void up()
    {
        if((y - 1) >= 0)
        {
            y -= 1;
        }
    }

    public void down()
    {
        if((y + 1) <= 19)
        {
            y += 1;
        }
    }
}
