package GamePkg;

import java.io.*;
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
    boolean chemin;

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
        chemin = false;
    }

    public void testBruteForce(int[][] map, ArrayList<Entity> entities) throws IOException {
        ArrayList<Direction> option = new ArrayList<Direction>();
        int xCopy = this.x;
        int yCopy = this.y;
        int birdCount = 0;
        ArrayList<int[]> positions = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++)
        {
            for(int j = 0 ; j < 20 ; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println(" ");
        }
        if(testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false, positions, entities))
        {

        }
        if(listDirection.size() > 0)
        {
            System.out.println("Algo fini");
            System.out.println(listDirection.get(0).size());
        }
        else
        {
            positions = new ArrayList<>();
            if(testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false, positions, entities))
            {

            }
            if(listDirection.size() > 0)
            {
                System.out.println("Algo fini");
                System.out.println(listDirection.get(0).size());
            }
            else
            {
                positions = new ArrayList<>();
                if(testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(listDirection.size() > 0)
                {
                    System.out.println("Algo fini");
                    System.out.println(listDirection.get(0).size());
                }
                else
                {
                    positions = new ArrayList<>();
                    if(testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false, positions, entities))
                    {

                    }
                    if(listDirection.size() > 0)
                    {
                        System.out.println("Algo fini");
                        System.out.println(listDirection.get(0).size());
                    }
                    else
                    {
                        System.out.println("Algo échoué");
                    }
                }
            }
        }
    }

    public boolean testUneDirection(int[][] map, int direction, ArrayList<Direction> option, int xCopy, int yCopy, int birdCount, boolean demiTour, ArrayList<int[]>positions, ArrayList<Entity> entities){
        if(!chemin)
        {
            System.out.println("------------------" + option.size());
            int nbTimes = 0;
            for(int[] position : positions)
            {
                if(position[0] == xCopy && position[1] == yCopy)
                {
                    nbTimes++;
                }
                if(nbTimes > 2)
                {
                    System.out.println("Position already tried");
                    return false;
                }
            }
            boolean foundBird = false;
            if(option.size() >= 500)
            {
                System.out.println("Too long");
                return false;
            }
            if(direction == 0)
            {
                if(map[xCopy - 1][yCopy] == 9)
                {
                    map[xCopy - 1][yCopy] = 0;
                    foundBird = true;
                    xCopy--;
                    birdCount++;
                    positions = new ArrayList<>();
                    System.out.println("Found bird");
                    System.out.println(birdCount);
                    option.add(Direction.UP);
                }
                else if(map[xCopy - 1][yCopy] == 1)
                {
                    System.out.println("Break block");
                    System.out.println(xCopy + " " + yCopy);
                    map[xCopy - 1][yCopy] = 0;
                    option.add(Direction.ANY);
                }
                else if(map[xCopy - 1][yCopy] == 2)
                {
                    for(Entity entity : entities)
                    {
                        if (entity.getX() == xCopy - 1 && entity.getY() == yCopy)
                        {
                            if(entity.isPushable())
                            {
                                System.out.println("Move up and push");
                                xCopy--;
                                System.out.println(xCopy + " " + yCopy);
                                option.add(Direction.UP);
                                int[] position = new int[2];
                                position[0] = xCopy;
                                position[1] = yCopy;
                                positions.add(position);
                                map[xCopy - 1][yCopy] = 1;
                            }
                            else
                            {
                                System.out.println("Useless movement");
                                System.out.println(xCopy + " " + yCopy);
                                return false;
                            }
                        }
                    }
                }
                else if(map[xCopy - 1][yCopy] == 3 || map[xCopy - 1][yCopy] == 4 || map[xCopy - 1][yCopy] == 7)
                {
                    System.out.println("Useless movement");
                    System.out.println(xCopy + " " + yCopy);
                    return false;
                }
                else if(map[xCopy - 1][yCopy] == 5)
                {
                    for(Entity entity : entities)
                    {
                        if (entity.getX() == xCopy - 1 && entity.getY() == yCopy)
                        {
                            for(Entity endPoint : entities)
                            {
                                if(endPoint.getTeleportationIdentifier() == entity.getTeleportationIdentifier())
                                {
                                    System.out.println("Move up and teleport");
                                    xCopy = endPoint.getX();
                                    yCopy = endPoint.getY();
                                    System.out.println(xCopy + " " + yCopy);
                                    option.add(Direction.UP);
                                    int[] position = new int[2];
                                    position[0] = xCopy;
                                    position[1] = yCopy;
                                    positions.add(position);
                                }
                            }
                        }
                    }
                }
                else if(map[xCopy - 1][yCopy] == 6)
                {
                    for(Entity entity : entities)
                    {
                        if (entity.getX() == xCopy - 1 && entity.getY() == yCopy)
                        {
                            System.out.println("Move up and drive");
                            xCopy--;
                            System.out.println(xCopy + " " + yCopy);
                            option.add(Direction.UP);
                            int[] position = new int[2];
                            position[0] = xCopy;
                            position[1] = yCopy;
                            positions.add(position);
                            if (entity.itsDirection == Direction.UP)
                            {
                                while(xCopy > 0 && map[xCopy - 1][yCopy] != 1 && map[xCopy - 1][yCopy] != 4 && map[xCopy - 1][yCopy] != 2)
                                {
                                    xCopy--;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                            else if (entity.itsDirection == Direction.DOWN)
                            {
                                while(xCopy < 9 && map[xCopy + 1][yCopy] != 1 && map[xCopy + 1][yCopy] != 4 && map[xCopy + 1][yCopy] != 2)
                                {
                                    xCopy++;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                            else if (entity.itsDirection == Direction.RIGHT)
                            {
                                while(yCopy < 19 && map[xCopy][yCopy + 1] != 1 && map[xCopy][yCopy + 1] != 4 && map[xCopy][yCopy + 1] != 2)
                                {
                                    yCopy++;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                            else if (entity.itsDirection == Direction.LEFT)
                            {
                                while(yCopy > 0 && map[xCopy][yCopy - 1] != 1 && map[xCopy][yCopy - 1] != 4 && map[xCopy][yCopy - 1] != 2)
                                {
                                    yCopy--;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Move up");
                    xCopy--;
                    System.out.println(xCopy + " " + yCopy);
                    option.add(Direction.UP);
                    int[] position = new int[2];
                    position[0] = xCopy;
                    position[1] = yCopy;
                    positions.add(position);
                }
            }
            else if(direction == 1)
            {
                if(map[xCopy][yCopy + 1] == 9)
                {
                    map[xCopy][yCopy + 1] = 0;
                    foundBird = true;
                    yCopy++;
                    birdCount++;
                    positions = new ArrayList<>();
                    System.out.println("Found bird");
                    System.out.println(birdCount);
                    option.add(Direction.RIGHT);
                }
                else if(map[xCopy][yCopy + 1] == 1)
                {
                    System.out.println("Break block");
                    System.out.println(xCopy + " " + yCopy);
                    map[xCopy][yCopy + 1] = 0;
                    option.add(Direction.ANY);
                }
                else if(map[xCopy][yCopy + 1] == 2)
                {
                    for(Entity entity : entities)
                    {
                        if (entity.getX() == xCopy && entity.getY() == yCopy + 1)
                        {
                            if(entity.isPushable())
                            {
                                System.out.println("Move right and push");
                                yCopy++;
                                System.out.println(xCopy + " " + yCopy);
                                option.add(Direction.RIGHT);
                                int[] position = new int[2];
                                position[0] = xCopy;
                                position[1] = yCopy;
                                positions.add(position);
                                map[xCopy][yCopy + 1] = 1;
                            }
                            else
                            {
                                System.out.println("Useless movement");
                                System.out.println(xCopy + " " + yCopy);
                                return false;
                            }
                        }
                    }
                }
                else if(map[xCopy][yCopy + 1] == 3 || map[xCopy][yCopy + 1] == 4 || map[xCopy][yCopy + 1] == 7)
                {
                    System.out.println("Useless movement");
                    System.out.println(xCopy + " " + yCopy);
                    return false;
                }
                else if(map[xCopy][yCopy + 1] == 5)
                {
                    for(Entity entity : entities)
                    {
                        if (entity.getX() == xCopy && entity.getY() == yCopy + 1)
                        {
                            for(Entity endPoint : entities)
                            {
                                if(endPoint.getTeleportationIdentifier() == entity.getTeleportationIdentifier())
                                {
                                    System.out.println("Move right and teleport");
                                    xCopy = endPoint.getX();
                                    yCopy = endPoint.getY();
                                    System.out.println(xCopy + " " + yCopy);
                                    option.add(Direction.RIGHT);
                                    int[] position = new int[2];
                                    position[0] = xCopy;
                                    position[1] = yCopy;
                                    positions.add(position);
                                }
                            }
                        }
                    }
                }
                else if(map[xCopy][yCopy + 1] == 6)
                {
                    for(Entity entity : entities)
                    {
                        if (entity.getX() == xCopy && entity.getY() == yCopy + 1)
                        {
                            System.out.println("Move right and drive");
                            yCopy++;
                            System.out.println(xCopy + " " + yCopy);
                            option.add(Direction.RIGHT);
                            int[] position = new int[2];
                            position[0] = xCopy;
                            position[1] = yCopy;
                            positions.add(position);
                            if (entity.itsDirection == Direction.UP)
                            {
                                while(xCopy > 0 && map[xCopy - 1][yCopy] != 1 && map[xCopy - 1][yCopy] != 4 && map[xCopy - 1][yCopy] != 2)
                                {
                                    xCopy--;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                            else if (entity.itsDirection == Direction.DOWN)
                            {
                                while(xCopy < 9 && map[xCopy + 1][yCopy] != 1 && map[xCopy + 1][yCopy] != 4 && map[xCopy + 1][yCopy] != 2)
                                {
                                    xCopy++;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                            else if (entity.itsDirection == Direction.RIGHT)
                            {
                                while(yCopy < 19 && map[xCopy][yCopy + 1] != 1 && map[xCopy][yCopy + 1] != 4 && map[xCopy][yCopy + 1] != 2)
                                {
                                    yCopy++;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                            else if (entity.itsDirection == Direction.LEFT)
                            {
                                while(yCopy > 0 && map[xCopy][yCopy - 1] != 1 && map[xCopy][yCopy - 1] != 4 && map[xCopy][yCopy - 1] != 2)
                                {
                                    yCopy--;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Move right");
                    yCopy++;
                    System.out.println(xCopy + " " + yCopy);
                    option.add(Direction.RIGHT);
                    int[] position = new int[2];
                    position[0] = xCopy;
                    position[1] = yCopy;
                    positions.add(position);
                }
            }
            else if(direction == 2)
            {
                if(map[xCopy + 1][yCopy] == 9)
                {
                    map[xCopy + 1][yCopy] = 0;
                    foundBird = true;
                    xCopy++;
                    birdCount++;
                    positions = new ArrayList<>();
                    System.out.println("Found bird");
                    System.out.println(birdCount);
                    option.add(Direction.DOWN);
                }
                else if(map[xCopy + 1][yCopy] == 1)
                {
                    System.out.println("Break block");
                    System.out.println(xCopy + " " + yCopy);
                    map[xCopy + 1][yCopy] = 0;
                    option.add(Direction.ANY);
                }
                else if(map[xCopy + 1][yCopy] == 2)
                {
                    for(Entity entity : entities)
                    {
                        if (entity.getX() == xCopy + 1 && entity.getY() == yCopy)
                        {
                            if(entity.isPushable())
                            {
                                System.out.println("Move down and push");
                                xCopy++;
                                System.out.println(xCopy + " " + yCopy);
                                option.add(Direction.DOWN);
                                int[] position = new int[2];
                                position[0] = xCopy;
                                position[1] = yCopy;
                                positions.add(position);
                                map[xCopy + 1][yCopy] = 1;
                            }
                            else
                            {
                                System.out.println("Useless movement");
                                System.out.println(xCopy + " " + yCopy);
                                return false;
                            }
                        }
                    }
                }
                else if(map[xCopy + 1][yCopy] == 3 || map[xCopy + 1][yCopy] == 4 || map[xCopy + 1][yCopy] == 7)
                {
                    System.out.println("Useless movement");
                    System.out.println(xCopy + " " + yCopy);
                    return false;
                }
                else if(map[xCopy + 1][yCopy] == 5)
                {
                    for(Entity entity : entities)
                    {
                        if (entity.getX() == xCopy + 1 && entity.getY() == yCopy)
                        {
                            for(Entity endPoint : entities)
                            {
                                if(endPoint.getTeleportationIdentifier() == entity.getTeleportationIdentifier())
                                {
                                    System.out.println("Move down and teleport");
                                    xCopy = endPoint.getX();
                                    yCopy = endPoint.getY();
                                    System.out.println(xCopy + " " + yCopy);
                                    option.add(Direction.DOWN);
                                    int[] position = new int[2];
                                    position[0] = xCopy;
                                    position[1] = yCopy;
                                    positions.add(position);
                                }
                            }
                        }
                    }
                }
                else if(map[xCopy + 1][yCopy] == 6)
                {
                    for(Entity entity : entities)
                    {
                        if (entity.getX() == xCopy + 1 && entity.getY() == yCopy)
                        {
                            System.out.println("Move down and drive");
                            xCopy++;
                            System.out.println(xCopy + " " + yCopy);
                            option.add(Direction.DOWN);
                            int[] position = new int[2];
                            position[0] = xCopy;
                            position[1] = yCopy;
                            positions.add(position);
                            if (entity.itsDirection == Direction.UP)
                            {
                                while(xCopy > 0 && map[xCopy - 1][yCopy] != 1 && map[xCopy - 1][yCopy] != 4 && map[xCopy - 1][yCopy] != 2)
                                {
                                    xCopy--;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                            else if (entity.itsDirection == Direction.DOWN)
                            {
                                while(xCopy < 9 && map[xCopy + 1][yCopy] != 1 && map[xCopy + 1][yCopy] != 4 && map[xCopy + 1][yCopy] != 2)
                                {
                                    xCopy++;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                            else if (entity.itsDirection == Direction.RIGHT)
                            {
                                while(yCopy < 19 && map[xCopy][yCopy + 1] != 1 && map[xCopy][yCopy + 1] != 4 && map[xCopy][yCopy + 1] != 2)
                                {
                                    yCopy++;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                            else if (entity.itsDirection == Direction.LEFT)
                            {
                                while(yCopy > 0 && map[xCopy][yCopy - 1] != 1 && map[xCopy][yCopy - 1] != 4 && map[xCopy][yCopy - 1] != 2)
                                {
                                    yCopy--;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Move down");
                    xCopy++;
                    System.out.println(xCopy + " " + yCopy);
                    option.add(Direction.DOWN);
                    int[] position = new int[2];
                    position[0] = xCopy;
                    position[1] = yCopy;
                    positions.add(position);
                }
            }
            else if(direction == 3)
            {
                if(map[xCopy][yCopy - 1] == 9)
                {
                    map[xCopy][yCopy - 1] = 0;
                    foundBird = true;
                    yCopy++;
                    birdCount++;
                    positions = new ArrayList<>();
                    System.out.println("Found bird");
                    System.out.println(birdCount);
                    option.add(Direction.LEFT);
                }
                else if(map[xCopy][yCopy - 1] == 1)
                {
                    System.out.println("Break block");
                    System.out.println(xCopy + " " + yCopy);
                    map[xCopy][yCopy - 1] = 0;
                    option.add(Direction.ANY);
                }
                else if(map[xCopy][yCopy - 1] == 2)
                {
                    for(Entity entity : entities)
                    {
                        if (entity.getX() == xCopy && entity.getY() == yCopy - 1)
                        {
                            if(entity.isPushable())
                            {
                                System.out.println("Move left and push");
                                yCopy--;
                                System.out.println(xCopy + " " + yCopy);
                                option.add(Direction.LEFT);
                                int[] position = new int[2];
                                position[0] = xCopy;
                                position[1] = yCopy;
                                positions.add(position);
                                map[xCopy][yCopy - 1] = 1;
                            }
                            else
                            {
                                System.out.println("Useless movement");
                                System.out.println(xCopy + " " + yCopy);
                                return false;
                            }
                        }
                    }
                }
                else if(map[xCopy][yCopy - 1] == 3 || map[xCopy][yCopy - 1] == 4 || map[xCopy][yCopy - 1] == 7)
                {
                    System.out.println("Useless movement");
                    System.out.println(xCopy + " " + yCopy);
                    return false;
                }
                else if(map[xCopy][yCopy - 1] == 5)
                {
                    for(Entity entity : entities)
                    {
                        if (entity.getX() == xCopy && entity.getY() == yCopy - 1)
                        {
                            for(Entity endPoint : entities)
                            {
                                if(endPoint.getTeleportationIdentifier() == entity.getTeleportationIdentifier())
                                {
                                    System.out.println("Move left and teleport");
                                    xCopy = endPoint.getX();
                                    yCopy = endPoint.getY();
                                    System.out.println(xCopy + " " + yCopy);
                                    option.add(Direction.LEFT);
                                    int[] position = new int[2];
                                    position[0] = xCopy;
                                    position[1] = yCopy;
                                    positions.add(position);
                                }
                            }
                        }
                    }
                }
                else if(map[xCopy][yCopy - 1] == 6)
                {
                    for(Entity entity : entities)
                    {
                        if (entity.getX() == xCopy && entity.getY() == yCopy - 1)
                        {
                            System.out.println("Move left and drive");
                            yCopy--;
                            System.out.println(xCopy + " " + yCopy);
                            option.add(Direction.LEFT);
                            int[] position = new int[2];
                            position[0] = xCopy;
                            position[1] = yCopy;
                            positions.add(position);
                            if (entity.itsDirection == Direction.UP)
                            {
                                while(xCopy > 0 && map[xCopy - 1][yCopy] != 1 && map[xCopy - 1][yCopy] != 4 && map[xCopy - 1][yCopy] != 2)
                                {
                                    xCopy--;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                            else if (entity.itsDirection == Direction.DOWN)
                            {
                                while(xCopy < 9 && map[xCopy + 1][yCopy] != 1 && map[xCopy + 1][yCopy] != 4 && map[xCopy + 1][yCopy] != 2)
                                {
                                    xCopy++;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                            else if (entity.itsDirection == Direction.RIGHT)
                            {
                                while(yCopy < 19 && map[xCopy][yCopy + 1] != 1 && map[xCopy][yCopy + 1] != 4 && map[xCopy][yCopy + 1] != 2)
                                {
                                    yCopy++;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                            else if (entity.itsDirection == Direction.LEFT)
                            {
                                while(yCopy > 0 && map[xCopy][yCopy - 1] != 1 && map[xCopy][yCopy - 1] != 4 && map[xCopy][yCopy - 1] != 2)
                                {
                                    yCopy--;
                                    if(map[xCopy][yCopy] == 3 || map[xCopy][yCopy] == 7)
                                    {
                                        System.out.println("Dead");
                                        System.out.println(xCopy + " " + yCopy);
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("Move left");
                    yCopy--;
                    System.out.println(xCopy + " " + yCopy);
                    option.add(Direction.LEFT);
                    int[] position = new int[2];
                    position[0] = xCopy;
                    position[1] = yCopy;
                    positions.add(position);
                }
            }
            if(birdCount == 4)
            {
                System.out.println("Chemin trouver");
                listDirection.add(option);
                chemin=true;
                return true;
            }
            if(direction == 0)
            {
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
            }
            else if(direction == 1)
            {
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
            }
            else if(direction == 2)
            {
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
            }
            else if(direction == 3)
            {
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false, positions, entities))
                {

                }
            }
            if(!chemin)
            {
                option.remove(option.size()-1);
                if(foundBird)
                {
                    map[xCopy][yCopy] = 9;
                }
            }
            positions = new ArrayList<>();
            return false;
        }
        else
        {
            return true;
        }
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
