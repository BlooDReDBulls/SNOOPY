package GamePkg;

import java.util.Timer;
import java.util.TimerTask;
public class Player extends Entity{
    private int numberBird;
    private boolean enableMove;

    public boolean unBlockMovement;
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
        unBlockMovement = true;
        enableMove = true;
        numberLife = 3;
        numberBird = 0;
        animation = true;
        collision = true;
        identifier = 8;
        move = true;
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

    @Override
    boolean isMove() {
        return move;
    }

    public int getNumberLife() {
        return numberLife;
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
    public void updatePosition(int[][] map, PushBlock pushBlock)
    {
        if(enableMove)
        {
            if(itsDirection == Direction.UP)
            {
                if(pushBlock.isPushable())
                {
                    if((x - 1) >= 0 && map[x - 1][y] != 1 && map[x - 1][y] != 4)
                    {
                        lastX = x;
                        lastY = y;
                        x -= 1;
                    }
                    else{
                        unBlockMovement = true;
                    }
                }
                else
                {
                    if((x - 1) >= 0 && map[x - 1][y] != 1 && map[x - 1][y] != 4 && map[x - 1][y] != 2)
                    {
                        lastX = x;
                        lastY = y;
                        x -= 1;
                    }
                    else{
                        unBlockMovement = true;
                    }
                }
            }
            else if(itsDirection == Direction.DOWN)
            {
                if(pushBlock.isPushable())
                {
                    if((x + 1) <= 9 && map[x + 1][y] != 1 && map[x + 1][y] != 4)
                    {
                        lastX = x;
                        lastY = y;
                        x += 1;
                    }
                    else{
                        unBlockMovement = true;
                    }
                }
                else
                {
                    if((x + 1) <= 9 && map[x + 1][y] != 1 && map[x + 1][y] != 4 && map[x + 1][y] != 2)
                    {
                        lastX = x;
                        lastY = y;
                        x += 1;
                    }
                    else{
                        unBlockMovement = true;
                    }
                }
            }
            else if(itsDirection == Direction.RIGHT)
            {
                if(pushBlock.isPushable())
                {
                    if((y + 1) <= 19 && map[x][y + 1] != 1 && map[x][y + 1] != 4)
                    {
                        lastX = x;
                        lastY = y;
                        y += 1;
                    }
                    else{
                        unBlockMovement = true;
                    }
                }
                else
                {
                    if((y + 1) <= 19 && map[x][y + 1] != 2 && map[x][y + 1] != 1 && map[x][y + 1] != 4)
                    {
                        lastX = x;
                        lastY = y;
                        y += 1;
                    }
                    else{
                        unBlockMovement = true;
                    }
                }
            }
            else if(itsDirection == Direction.LEFT)
            {
                if(pushBlock.isPushable())
                {
                    if((y - 1) >= 0 && map[x][y - 1] != 1 && map[x][y - 1] != 4)
                    {
                        lastX = x;
                        lastY = y;
                        y -= 1;
                    }
                    else{
                        unBlockMovement = true;
                    }
                }
                else
                {
                    if((y - 1) >= 0 && map[x][y - 1] != 2 && map[x][y - 1] != 1 && map[x][y - 1] != 4)
                    {
                        lastX = x;
                        lastY = y;
                        y -= 1;
                    }
                    else{
                        unBlockMovement = true;
                    }
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

    @Override
    boolean isVisible() {
        return false;
    }

    @Override
    void setVisible(boolean visible) {

    }

    public void bird(){
        numberBird += 1;
        System.out.println(numberBird);
    }
    public boolean win(){
        if(numberBird == 4){
            System.out.println("Next Level");
            numberBird = 0;
            return true;
        }
        return false;
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
