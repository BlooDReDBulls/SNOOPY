package GamePkg;

import java.util.Timer;
import java.util.TimerTask;

public class Ball {
    int x;
    int y;

    int lastX;

    int lastY;

    int xspeed;
    int yspeed;

    Timer speed = new Timer();

    public Ball(){
        x = 0;
        y = 0;
        xspeed = 1;
        yspeed = 1;
        speed.schedule(movement, 0, 300);
    }

    public int getLastX() {
        return lastX;
    }

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
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
