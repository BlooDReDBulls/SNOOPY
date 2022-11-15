package GamePkg;

import java.io.IOException;
import java.util.ArrayList;

public class AI {
    int x;
    int y;
    public ArrayList<ArrayList<Direction>> listDirection;
    boolean chemin;

    public AI(int algorithmChoice, int x, int y, int[][] map, ArrayList<Entity> entities, Ball ball) throws IOException {
        this.x = x;
        this.y = y;
        listDirection = new ArrayList<>();
        chemin = false;
        if(algorithmChoice == 1)
        {
            testBruteForce(map, entities, ball);
        }
    }

    public void testBruteForce(int[][] map, ArrayList<Entity> entities, Ball ball) throws IOException {
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
        if(testUneDirection(map, 0, option, xCopy, yCopy, birdCount, positions, entities, ball))
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
            if(testUneDirection(map, 2, option, xCopy, yCopy, birdCount, positions, entities, ball))
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
                if(testUneDirection(map, 3, option, xCopy, yCopy, birdCount, positions, entities, ball))
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
                    if(testUneDirection(map, 1, option, xCopy, yCopy, birdCount, positions, entities, ball))
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
        ball.x = 0;
        ball.y = 0;
        ball.xspeed = 1;
        ball.yspeed = 1;
        ball.start();
        for(Entity entity : entities)
        {
            if(entity.getIdentifier() == 9)
            {
                entity.visible = true;
            }
            else if(entity.getIdentifier() == 2)
            {
                entity.setPushable(true);
            }
        }
    }

    public boolean testUneDirection(int[][] map, int direction, ArrayList<Direction> option, int xCopy, int yCopy, int birdCount, ArrayList<int[]>positions, ArrayList<Entity> entities, Ball ball){
        if(!chemin)
        {
            ball.updatePositionIA(map);
            map[ball.getLastX()][ball.getLastY()] = 0;
            map[ball.getX()][ball.getY()] = 7;
            for(Entity entity : entities)
            {
                if(entity.getIdentifier() == 5 || entity.getIdentifier() == 6)
                {
                    map[entity.getX()][entity.getY()] = entity.getIdentifier();
                }
                else if(entity.getIdentifier() == 9 && entity.isVisible())
                {
                    map[entity.getX()][entity.getY()] = entity.getIdentifier();
                }
            }
            System.out.println("------------------" + option.size());
            ball.updatePosition(map, entities);
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
            boolean push = false;
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
                    for(Entity bird : entities)
                    {
                        if(bird.getIdentifier() == 9 && bird.getX() == xCopy && bird.getY() == yCopy)
                        {
                            bird.visible = false;
                        }
                    }
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
                            if(!entity.isPushable())
                            {
                                entity.setPushable(false);
                                push = true;
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
                                        option.remove(option.size()-1);
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
                                        option.remove(option.size()-1);
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
                                        option.remove(option.size()-1);
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
                                        option.remove(option.size()-1);
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
                    for(Entity bird : entities)
                    {
                        if(bird.getIdentifier() == 9 && bird.getX() == xCopy && bird.getY() == yCopy)
                        {
                            bird.visible = false;
                        }
                    }
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
                            if(!entity.isPushable())
                            {
                                entity.setPushable(false);
                                push = true;
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
                                        option.remove(option.size()-1);
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
                                        option.remove(option.size()-1);
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
                                        option.remove(option.size()-1);
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
                                        option.remove(option.size()-1);
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
                    for(Entity bird : entities)
                    {
                        if(bird.getIdentifier() == 9 && bird.getX() == xCopy && bird.getY() == yCopy)
                        {
                            bird.visible = false;
                        }
                    }
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
                            if(!entity.isPushable())
                            {
                                entity.setPushable(false);
                                push = true;
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
                                        option.remove(option.size()-1);
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
                                        option.remove(option.size()-1);
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
                                        option.remove(option.size()-1);
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
                                        option.remove(option.size()-1);
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
                    yCopy--;
                    birdCount++;
                    for(Entity bird : entities)
                    {
                        if(bird.getIdentifier() == 9 && bird.getX() == xCopy && bird.getY() == yCopy)
                        {
                            bird.visible = false;
                        }
                    }
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
                            if(!entity.isPushable())
                            {
                                entity.setPushable(false);
                                push = true;
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
                                        option.remove(option.size()-1);
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
                                        option.remove(option.size()-1);
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
                                        option.remove(option.size()-1);
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
                                        option.remove(option.size()-1);
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
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
            }
            else if(direction == 1)
            {
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
            }
            else if(direction == 2)
            {
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
            }
            else if(direction == 3)
            {
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, positions, entities, ball))
                {

                }
            }
            if(!chemin)
            {
                option.remove(option.size()-1);
                if(foundBird)
                {
                    for(Entity bird : entities)
                    {
                        if(bird.getIdentifier() == 9 && bird.getX() == xCopy && bird.getY() == yCopy)
                        {
                            bird.visible = true;
                        }
                    }
                }
                if(push)
                {
                    for(Entity entity : entities)
                    {
                        if(entity.getIdentifier() == 2 && entity.getX() == xCopy && entity.getY() == yCopy)
                        {
                            entity.setPushable(true);
                            map[xCopy][yCopy] = 2;
                        }
                    }
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
}
