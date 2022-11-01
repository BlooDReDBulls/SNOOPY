package GamePkg;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Ball extends Entity{
    int xspeed;
    int yspeed;

    boolean unableMovement;

    Timer speed = new Timer();

    public Ball(){
        animation = false;
        collision = false;
        identifier = 7;
        move = true;
        x = 0;
        y = 0;
        xspeed = 1;
        yspeed = 1;
        unableMovement = true;
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
    boolean isVisible() {
        return false;
    }

    @Override
    void setVisible(boolean visible) {

    }

    @Override
    void push(Direction direction) {

    }

    @Override
    boolean isPushable() {
        return false;
    }

    @Override
    public int getLastX() {
        return lastX;
    }
    @Override
    public int getLastY() {
        return lastY;
    }

    @Override
    void updatePosition(int[][] map, ArrayList<Entity> entities) {
        if(unableMovement)
        {
            lastX = x;
            lastY = y;
            x+=xspeed;
            y+=yspeed;
            if (x + xspeed > 9 || x + xspeed < 0){
                xspeed=-xspeed;
            }
            if(y + yspeed > 19 || y + yspeed < 0){
                yspeed=-yspeed;
            }
            if(map[x + xspeed][y] == 1 || map[x + xspeed][y] == 2 || map[x + xspeed][y] == 3 || map[x + xspeed][y] == 4)
            {
                xspeed=-xspeed;
            }
            if(map[x][y + yspeed] == 1 || map[x][y + yspeed] == 2 || map[x][y + yspeed] == 3 || map[x][y + yspeed] == 4)
            {
                yspeed=-yspeed;
            }
            if(map[x + xspeed][y + yspeed] == 1 || map[x + xspeed][y + yspeed] == 2 || map[x + xspeed][y + yspeed] == 3 || map[x + xspeed][y + yspeed] == 4)
            {
                xspeed=-xspeed;
                yspeed=-yspeed;
            }
            unableMovement = false;
            speed.schedule(new TimerTask() {
                @Override
                public void run() {
                    unableMovement = true;
                }
            }, 300);
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
