package GamePkg;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class Player extends Entity{
    private int numberBird;
    public boolean unBlockMovement;
    private int numberLife;
    private boolean invincible;
    public Direction itsLastDirection;

    boolean unableMovement;
    Timer dead = new Timer();

    public Player(int x, int y)
    {
        itsDirection = Direction.ANY;
        this.x = x;
        this.y = y;
        unBlockMovement = true;
        numberLife = 3;
        numberBird = 0;
        animation = true;
        collision = true;
        identifier = 8;
        move = true;
        unableMovement = true;
    }

    @Override
    public int getX()
    {
        return x;
    }

    public void setUnableMovement(boolean unableMovement) {
        this.unableMovement = unableMovement;
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
    public void updatePosition(int[][] map, ArrayList<Entity> entities)
    {
        if(unableMovement)
        {
            for(Entity entity : entities)
            {
                if(itsDirection == Direction.UP)
                {
                    if(entity.getIdentifier() == 1 || entity.getIdentifier() == 3 || entity.getIdentifier() == 4)
                    {
                        if((x - 1) >= 0 && map[x - 1][y] != entity.getIdentifier())
                        {
                            lastX = x;
                            lastY = y;
                            x -= 1;
                        }
                        else
                        {
                            unBlockMovement = true;
                        }
                    }
                    else if(entity.getIdentifier() == 2)
                    {
                        if(!entity.isPushable())
                        {
                            if((x - 1) >= 0 && map[x - 1][y] != entity.getIdentifier())
                            {
                                lastX = x;
                                lastY = y;
                                x -= 1;
                            }
                            else
                            {
                                unBlockMovement = true;
                            }
                        }
                    }
                }
                else if(itsDirection == Direction.DOWN)
                {
                    if(entity.getIdentifier() == 1 || entity.getIdentifier() == 3 || entity.getIdentifier() == 4)
                    {
                        if((x + 1) <= 9 && map[x + 1][y] != entity.getIdentifier())
                        {
                            lastX = x;
                            lastY = y;
                            x += 1;
                        }
                        else
                        {
                            unBlockMovement = true;
                        }
                    }
                    else if(entity.getIdentifier() == 2)
                    {
                        if(!entity.isPushable())
                        {
                            if((x + 1) <= 9 && map[x + 1][y] != entity.getIdentifier())
                            {
                                lastX = x;
                                lastY = y;
                                x += 1;
                            }
                            else
                            {
                                unBlockMovement = true;
                            }
                        }
                    }
                }
                else if(itsDirection == Direction.RIGHT)
                {
                    if(entity.getIdentifier() == 1 || entity.getIdentifier() == 3 || entity.getIdentifier() == 4)
                    {
                        if((y + 1) <= 19 && map[x][y + 1] != entity.getIdentifier())
                        {
                            lastX = x;
                            lastY = y;
                            y += 1;
                        }
                        else
                        {
                            unBlockMovement = true;
                        }
                    }
                    else if(entity.getIdentifier() == 2)
                    {
                        if(!entity.isPushable())
                        {
                            if((y + 1) >= 0 && map[x][y + 1] != entity.getIdentifier())
                            {
                                lastX = x;
                                lastY = y;
                                y += 1;
                            }
                            else
                            {
                                unBlockMovement = true;
                            }
                        }
                    }
                }
                else if(itsDirection == Direction.LEFT)
                {
                    if(entity.getIdentifier() == 1 || entity.getIdentifier() == 3 || entity.getIdentifier() == 4)
                    {
                        if((y - 1) >= 0 && map[x][y - 1] != entity.getIdentifier())
                        {
                            lastX = x;
                            lastY = y;
                            y -= 1;
                        }
                        else
                        {
                            unBlockMovement = true;
                        }
                    }
                    else if(entity.getIdentifier() == 2)
                    {
                        if(!entity.isPushable())
                        {
                            if((y - 1) >= 0 && map[x][y - 1] != entity.getIdentifier())
                            {
                                lastX = x;
                                lastY = y;
                                y -= 1;
                            }
                            else
                            {
                                unBlockMovement = true;
                            }
                        }
                    }
                }
            }
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

    @Override
    void push(Direction direction) {

    }

    @Override
    boolean isPushable() {
        return false;
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
