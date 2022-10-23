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

public class Player extends Entity{
    private int numberBird;
    private boolean enableMove;

    public boolean bl;
    private int numberLife;
    private boolean invincible;

    public Direction itsDirection;

    public Direction itsLastDirection;

    Timer speed = new Timer();
    Timer dead = new Timer();

    public Player()
    {
        itsDirection = Direction.ANY;
        x = 5;
        y = 5;
        bl = true;
        enableMove = true;
        numberLife = 3;
        numberBird = 0;
        animation = true;
        collision = true;
        identifier = 8;
    }

    public TimerTask movement = new TimerTask() {
        @Override
        public void run() {
            enableMove = !enableMove;
        }
    };
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

    public int getNumberLife() {
        return numberLife;
    }

    @Override
    public void updatePosition()
    {
        if(enableMove)
        {
            if(itsDirection == Direction.UP)
            {
                if((x - 1) >= 0)
                {
                    x -= 1;
                }
                else{
                    bl = true;
                }
            }
            else if(itsDirection == Direction.DOWN)
            {
                if((x + 1) <= 9)
                {
                    x += 1;
                }
                else{
                    bl = true;
                }
            }
            else if(itsDirection == Direction.RIGHT)
            {
                if((y + 1) <= 19)
                {
                    y += 1;
                }
                else{
                    bl = true;
                }
            }
            else if(itsDirection == Direction.LEFT)
            {
                if((y - 1) >= 0)
                {
                    y -= 1;
                }
                else{
                    bl = true;
                }
            }
            //enableMove = !enableMove;
            /*speed.schedule(new TimerTask() {
                @Override
                public void run() {
                    enableMove = !enableMove;
                }}, 100);*/
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

    public void bird(){
        numberBird += 1;
        System.out.println(numberBird);
    }
    public void kill() {
        if (!invincible) {
            invincible=true;
            numberLife -= 1;
            if(numberLife==0){
                while (true);
            }
            dead.schedule(new TimerTask() {
                @Override
                public void run() {
                    invincible = false;
                }
            }, 1500);
        }
    }
}
