package GamePkg;

import java.util.Timer;
import java.util.TimerTask;

enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT,
    ANY
}

public class Player{
    private int x;
    private int y;

    private boolean enableMove;

    public boolean bl;

    public Direction itsDirection;

    public Direction itsLastDirection;

    Timer speed = new Timer();

    public Player()
    {
        itsDirection = Direction.ANY;
        x = 5;
        y = 5;
        bl = true;
        enableMove = true;
    }

    public TimerTask movement = new TimerTask() {
        @Override
        public void run() {
            enableMove = !enableMove;
        }
    };

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
        if(enableMove)
        {
            if(itsDirection == Direction.UP)
            {
                if((y - 1) >= 0)
                {
                    y -= 1;
                }
                else{
                    bl = true;
                }
            }
            else if(itsDirection == Direction.DOWN)
            {
                if((y + 1) <= 19)
                {
                    y += 1;
                }
                else{
                    bl = true;
                }
            }
            else if(itsDirection == Direction.RIGHT)
            {
                if((x + 1) <= 9)
                {
                    x += 1;
                }
                else{
                    bl = true;
                }
            }
            else if(itsDirection == Direction.LEFT)
            {
                if((x - 1) >= 0)
                {
                    x -= 1;
                }
                else{
                    bl = true;
                }
            }
            enableMove = !enableMove;
            speed.schedule(new TimerTask() {
                @Override
                public void run() {
                    enableMove = !enableMove;
                }}, 200);
        }
    }
}
