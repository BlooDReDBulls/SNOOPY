package GamePkg;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class Player extends Entity{
    private int numberBird;
    public int itsScore;
    public boolean unBlockMovement;
    Timer playerMovementTimer = new Timer();
    boolean unableMovement;
    private int x;
    private int lastY;
    private int y;
    ArrayList<ArrayList<Direction>> listDirection;

    public Player(int x, int y)
    {
        this.x = x;
        this.y = y;
        unBlockMovement = true;
        numberBird = 0;
        animation = true;
        collision = true;
        identifier = 8;
        move = true;
        unableMovement = true;
        listDirection = new ArrayList<>();
    }

    public void testBruteForce(int[][] map)
    {
        for(int i = 0 ; i < 10 ; i++)
        {
            for(int j = 0 ; j < 20 ; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println(" ");
        }
        ArrayList<Direction> option = new ArrayList<Direction>();
        int xCopy = x;
        int yCopy = y;
        int birdCount = 0;
        if(testUneDirection(map, 0, option, xCopy, yCopy, birdCount))
        {
            listDirection.add(option);
        }
        option = new ArrayList<Direction>();
        if(testUneDirection(map, 2, option, xCopy, yCopy, birdCount))
        {
            listDirection.add(option);
        }
        option = new ArrayList<Direction>();
        if(testUneDirection(map, 3, option, xCopy, yCopy, birdCount))
        {
            listDirection.add(option);
        }
        option = new ArrayList<Direction>();
        if(testUneDirection(map, 1, option, xCopy, yCopy, birdCount))
        {
            listDirection.add(option);
        }
        System.out.println("Algo fini");
        for(ArrayList<Direction> chemin : listDirection)
        {
            System.out.println(chemin.size());
        }
    }

    public boolean testUneDirection(int[][] map, int direction, ArrayList<Direction> option, int xCopy, int yCopy, int birdCount)
    {
        if(direction == 0)
        {
            if (xCopy - 1 < 0)
            {
                return false;
            }
            else if(map[xCopy - 1][yCopy] == 9)
            {
                birdCount++;
                System.out.println(birdCount);
                option.add(Direction.UP);
            }
            else if(map[xCopy - 1][yCopy] != 0)
            {
                return false;
            }
            else
            {
                xCopy--;
                option.add(Direction.UP);
            }
        }
        else if(direction == 1)
        {
            if (yCopy + 1 > 19)
            {
                return false;
            }
            else if(map[xCopy][yCopy + 1] == 9)
            {
                birdCount++;
                System.out.println(birdCount);
                option.add(Direction.RIGHT);
            }
            else if(map[xCopy][yCopy + 1] != 0)
            {
                return false;
            }
            else
            {
                yCopy++;
                option.add(Direction.RIGHT);
            }
        }
        else if(direction == 2)
        {
            if (xCopy + 1 > 9)
            {
                return false;
            }
            else if(map[xCopy + 1][yCopy] == 9)
            {
                birdCount++;
                System.out.println(birdCount);
                option.add(Direction.DOWN);
            }
            else if(map[xCopy + 1][yCopy] != 0)
            {
                return false;
            }
            else
            {
                xCopy++;
                option.add(Direction.DOWN);
            }
        }
        else if(direction == 3)
        {
            if (yCopy - 1 < 0)
            {
                return false;
            }
            else if(map[xCopy][yCopy - 1] == 9)
            {
                birdCount++;
                System.out.println(birdCount);
                option.add(Direction.LEFT);
            }
            else if(map[xCopy][yCopy - 1] != 0)
            {
                return false;
            }
            else
            {
                yCopy--;
                option.add(Direction.LEFT);
            }
        }
        Direction directionCopy = Direction.ANY;
        boolean inverse = false;
        for (Direction lastDirection : option)
        {
            if(inverse)
            {
                if(directionCopy == lastDirection)
                {
                    return false;
                }
                else
                {
                    inverse = false;
                    directionCopy = lastDirection;
                }
            }
            if(directionCopy == Direction.ANY)
            {
                directionCopy = lastDirection;
            }
            else if((directionCopy == Direction.UP && lastDirection == Direction.DOWN) || (directionCopy == Direction.DOWN && lastDirection == Direction.UP) || (directionCopy == Direction.RIGHT && lastDirection == Direction.LEFT) || (directionCopy == Direction.LEFT && lastDirection == Direction.RIGHT))
            {
                inverse = true;
            }
            else
            {
                directionCopy = lastDirection;
            }
        }
        if(birdCount == 4)
        {
            return true;
        }
        if(testUneDirection(map, 0, option, xCopy, yCopy, birdCount))
        {
            return true;
        }
        if(testUneDirection(map, 2, option, xCopy, yCopy, birdCount))
        {
            return true;
        }
        if(testUneDirection(map, 3, option, xCopy, yCopy, birdCount))
        {
            return true;
        }
        if(testUneDirection(map, 1, option, xCopy, yCopy, birdCount))
        {
            return true;
        }
        return false;
    }

    @Override
    public void updatePosition(int[][] map, ArrayList<Entity> entities)
    {
        if(unableMovement)
        {
            if(itsDirection == Direction.UP)
            {
                if((x - 1) >= 0 && map[x - 1][y] != 1 && map[x - 1][y] != 4)
                {
                    if(map[x - 1][y] == 2)
                    {
                        for(Entity entity : entities)
                        {
                            if(entity.getIdentifier() == 2 && entity.getX() == x - 1 && entity.getY() == y && entity.isPushable())
                            {
                                lastX = x;
                                lastY = y;
                                x -= 1;
                            }
                            else if(entity.getIdentifier() == 2 && entity.getX() == x - 1 && entity.getY() == y && !entity.isPushable())
                            {
                                unBlockMovement = true;
                            }
                        }
                    }
                    else
                    {
                        lastX = x;
                        lastY = y;
                        x -= 1;
                    }
                }
                else
                {
                    unBlockMovement = true;
                }
            }
            else if(itsDirection == Direction.DOWN)
            {
                if((x + 1) <= 9 && map[x + 1][y] != 1 && map[x + 1][y] != 4)
                {
                    if(map[x + 1][y] == 2)
                    {
                        for(Entity entity : entities)
                        {
                            if(entity.getIdentifier() == 2 && entity.getX() == x + 1 && entity.getY() == y && entity.isPushable())
                            {
                                lastX = x;
                                lastY = y;
                                x += 1;
                            }
                            else if(entity.getIdentifier() == 2 && entity.getX() == x + 1 && entity.getY() == y && !entity.isPushable())
                            {
                                unBlockMovement = true;
                            }
                        }
                    }
                    else
                    {
                        lastX = x;
                        lastY = y;
                        x += 1;
                    }
                }
                else
                {
                    unBlockMovement = true;
                }
            }
            else if(itsDirection == Direction.RIGHT)
            {
                if((y + 1) <= 19 && map[x][y + 1] != 1 && map[x][y + 1] != 4)
                {
                    if(map[x][y + 1] == 2)
                    {
                        for(Entity entity : entities)
                        {
                            if(entity.getIdentifier() == 2 && entity.getX() == x && entity.getY() == y + 1 && entity.isPushable())
                            {
                                lastX = x;
                                lastY = y;
                                y += 1;
                            }
                            else if(entity.getIdentifier() == 2 && entity.getX() == x && entity.getY() == y + 1 && !entity.isPushable())
                            {
                                unBlockMovement = true;
                            }
                        }
                    }
                    else
                    {
                        lastX = x;
                        lastY = y;
                        y += 1;
                    }
                }
                else
                {
                    unBlockMovement = true;
                }
            }
            else if(itsDirection == Direction.LEFT)
            {
                if((y - 1) >= 0 && map[x][y - 1] != 1 && map[x][y - 1] != 4)
                {
                    if(map[x][y - 1] == 2)
                    {
                        for(Entity entity : entities)
                        {
                            if(entity.getIdentifier() == 2 && entity.getX() == x && entity.getY() == y - 1 && entity.isPushable())
                            {
                                lastX = x;
                                lastY = y;
                                y -= 1;
                            }
                            else if(entity.getIdentifier() == 2 && entity.getX() == x && entity.getY() == y - 1 && !entity.isPushable())
                            {
                                unBlockMovement = true;
                            }
                        }
                    }
                    else
                    {
                        lastX = x;
                        lastY = y;
                        y -= 1;
                    }
                }
                else
                {
                    unBlockMovement = true;
                }
            }
            unableMovement = false;
            if(itsDirection != Direction.ANY)
            {
                itsLastDirection = itsDirection;
                itsDirection = Direction.ANY;
            }
            playerMovementTimer.schedule(new TimerTask(){
                @Override
                public void run() {
                    unableMovement = true;
                }
            }, 250);
        }
    }
    @Override
    boolean isCollision() {
        return collision;
    }

    @Override
    boolean isPushable() {
        return false;
    }

    @Override
    int getTeleportationIdentifier() {
        return 0;
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

    public void addScore(int seconds){
        itsScore += 100 * seconds;
        System.out.println("Score totale : " + itsScore);
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public int getLastX() {
        return lastX;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    public int getLastY() {
        return lastY;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }
}
