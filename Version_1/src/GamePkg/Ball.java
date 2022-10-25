package GamePkg;

import java.util.Timer;
import java.util.TimerTask;

public class Ball extends Entity{
    int xspeed;
    int yspeed;

    Timer speed = new Timer();

    public Ball(){
        animation = false;
        collision = true;
        identifier = 7;
        move = true;
        x = 0;
        y = 0;
        xspeed = 1;
        yspeed = 1;
        speed.schedule(movement, 0, 300);
    }
    @Override
    public int getIdentifier()
    {
        return identifier;
    }
    @Override
    public boolean isAnimated()
    {
        return animation;
    }
    @Override
    public boolean isCollision()
    {
        return collision;
    }
    @Override
    public int getLastX() {
        return lastX;
    }
    @Override
    public int getLastY() {
        return lastY;
    }

    public TimerTask movement = new TimerTask() {
        @Override
        public void run() {
            lastX = x;
            lastY = y;
            updatePosition();
        }
    };

    @Override
    public void updatePosition(){
        x+=xspeed;
        y+=yspeed;
        if (x == 9 | x==0){
            xspeed=-xspeed;
        }
        if(y == 19 | y ==0){
            yspeed=-yspeed;
        }
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
    boolean isMove() {
        return move;
    }
}
