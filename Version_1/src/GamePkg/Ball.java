package GamePkg;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Ball extends Entity{
    int xspeed;
    int yspeed;

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

    public TimerTask movement = new TimerTask() {
        @Override
        public void run() {
            lastX = x;
            lastY = y;
            int[][] map = new int[10][20];
            ArrayList<Entity> entities = new ArrayList<Entity>();
            updatePosition(map, entities);
        }
    };

    @Override
    void updatePosition(int[][] map, ArrayList<Entity> entities) {
        if (x + xspeed > 9 || x + xspeed < 0){
            xspeed=-xspeed;
        }
        /*else if(x != 0 && x != 9 && (map[x - 1][y] == 1 || map[x + 1][y] == 1 || map[x - 1][y] == 2 || map[x + 1][y] == 2 || map[x - 1][y] == 3 || map[x + 1][y] == 3 || map[x - 1][y] == 4 || map[x + 1][y] == 4))
        {
            xspeed=-xspeed;
        }*/
        if(y + yspeed > 19 || y + yspeed < 0){
            yspeed=-yspeed;
        }
        /*if(y != 0 && y != 19 && (map[x][y - 1] == 1 || map[x][y + 1] == 1 || map[x][y - 1] == 2 || map[x][y + 1] == 2 || map[x][y - 1] == 3 || map[x][y + 1] == 3 || map[x][y - 1] == 4 || map[x][y + 1] == 4))
        {
            yspeed=-yspeed;
        }*/
        /*if(x != 0 && x != 9 && y != 0 && y != 19 && (map[x - 1][y - 1] == 1 || map[x + 1][y + 1] == 1 || map[x - 1][y + 1] == 1 || map[x - 1][y + 1] == 1 || map[x - 1][y - 1] == 2 || map[x + 1][y + 1] == 2 || map[x + 1][y - 1] == 2 || map[x - 1][y + 1] == 2 || map[x - 1][y - 1] == 3 || map[x + 1][y + 1] == 3 || map[x + 1][y - 1] == 3 || map[x - 1][y + 1] == 3 || map[x - 1][y - 1] == 4 || map[x + 1][y + 1] == 4 || map[x + 1][y - 1] == 4 || map[x - 1][y + 1] == 4))
        {
            xspeed=-xspeed;
            yspeed=-yspeed;
        }*/
        x+=xspeed;
        y+=yspeed;
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
