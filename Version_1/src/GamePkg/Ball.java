package GamePkg;

public class Ball {
    int x;
    int y;

    int xspeed;
    int yspeed;

    public Ball(){
        x = 0;
        y = 0;
        xspeed = 1;
        yspeed = 1;
    }

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
