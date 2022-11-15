package GamePkg;

import java.util.ArrayList;

public class PushBlock extends Entity{
    boolean pushable;
    Direction direction;

    public PushBlock(int x, int y)
    {
        this.x = x;
        this.y = y;
        pushable = true;
        collision = true;
        animation = false;
        identifier = 2;
        move = false;
    }
    @Override
    public void push(Direction direction)
    {
        if(direction == Direction.LEFT)
        {
            pushable = false;
            this.y -= 1;
        }
        else if(direction == Direction.RIGHT)
        {
            pushable = false;
            this.y += 1;
        }
        else if(direction == Direction.UP)
        {
            pushable = false;
            this.x -= 1;
        }
        else if(direction == Direction.DOWN)
        {
            pushable = false;
            this.x += 1;
        }
    }
    @Override
    public boolean isPushable() {
        return pushable;
    }

    @Override
    int getTeleportationIdentifier() {
        return 0;
    }

    @Override
    void updatePosition(int[][] map, ArrayList<Entity> entities) {
        if(direction == Direction.LEFT)
        {
            y -= 1;
        }
        else if(direction == Direction.RIGHT)
        {
            y += 1;
        }
        else if(direction == Direction.UP)
        {
            x += 1;
        }
        else if(direction == Direction.DOWN)
        {
            x -= 1;
        }
    }


    @Override
    boolean isCollision() {
        return collision;
    }

    @Override
    void setPushable(boolean pushable) {
        this.pushable = pushable;
    }


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

}
