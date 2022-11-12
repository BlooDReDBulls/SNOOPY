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
    ArrayList<int[]> positions;
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
        positions = new ArrayList<>();
    }

    public void testBruteForce() throws IOException {
        int[][] map = new int[10][20];
        int xFile = 0;
        int yFile;
        File file = new File("map/" + 1);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null)
        {
            String[] list = line.split(" ");
            yFile = 0;
            for(String identifier : list)
            {
                map[xFile][yFile]=Integer.parseInt((String) identifier.split(":")[0]);
                yFile++;
            }
            xFile++;
        }
        fr.close();
        ArrayList<Direction> option = new ArrayList<Direction>();
        int xCopy = this.x;
        int yCopy = this.y;
        int birdCount = 0;
        positions = new ArrayList<>();
        if(testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
        {

        }
        System.out.println("1 fini");
        option = new ArrayList<Direction>();
        positions = new ArrayList<>();
        file = new File("map/" + 1);
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        xFile = 0;
        while((line = br.readLine()) != null)
        {
            String[] list = line.split(" ");
            yFile = 0;
            for(String identifier : list)
            {
                map[xFile][yFile]=Integer.parseInt((String) identifier.split(":")[0]);
                yFile++;
            }
            xFile++;
        }
        fr.close();
        if(testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
        {

        }
        System.out.println("2 fini");
        option = new ArrayList<Direction>();
        positions = new ArrayList<>();
        file = new File("map/" + 1);
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        xFile = 0;
        while((line = br.readLine()) != null)
        {
            String[] list = line.split(" ");
            yFile = 0;
            for(String identifier : list)
            {
                map[xFile][yFile]=Integer.parseInt((String) identifier.split(":")[0]);
                yFile++;
            }
            xFile++;
        }
        fr.close();
        if(testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
        {

        }
        System.out.println("3 fini");
        option = new ArrayList<Direction>();
        positions = new ArrayList<>();
        file = new File("map/" + 1);
        fr = new FileReader(file);
        br = new BufferedReader(fr);
        xFile = 0;
        while((line = br.readLine()) != null)
        {
            String[] list = line.split(" ");
            yFile = 0;
            for(String identifier : list)
            {
                map[xFile][yFile]=Integer.parseInt((String) identifier.split(":")[0]);
                yFile++;
            }
            xFile++;
        }
        fr.close();
        if(testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
        {

        }
        System.out.println("4 fini");
        System.out.println("Algo fini");
        for(ArrayList<Direction> chemin : listDirection)
        {
            System.out.println(chemin.size());
        }
    }

    public boolean testUneDirection(int[][] map, int direction, ArrayList<Direction> option, int xCopy, int yCopy, int birdCount, boolean demiTour){
        System.out.println("------------------" + option.size());
        if(option.size() >= 500)
        {
            System.out.println("Too long");
            return false;
        }
        for(int[] position : positions)
        {
            if(position[0] == xCopy && position[1] == yCopy)
            {
                System.out.println("Position already tried");
                return false;
            }
        }
        if(direction == 0)
        {
            if(map[xCopy - 1][yCopy] == 9)
            {
                map[xCopy - 1][yCopy] = 0;
                birdCount++;
                positions = new ArrayList<>();
                System.out.println("Found bird");
                System.out.println(birdCount);
                option.add(Direction.UP);
            }
            else if(map[xCopy - 1][yCopy] != 0)
            {
                System.out.println("Useless movement");
                System.out.println(xCopy + " " + yCopy);
                return false;
            }
            else
            {
                System.out.println("Move up");
                xCopy--;
                System.out.println(xCopy + " " + yCopy);
                option.add(Direction.UP);
            }
        }
        else if(direction == 1)
        {
            if(map[xCopy][yCopy + 1] == 9)
            {
                map[xCopy][yCopy + 1] = 0;
                birdCount++;
                positions = new ArrayList<>();
                System.out.println("Found bird");
                System.out.println(birdCount);
                System.out.println(positions.size());
                option.add(Direction.RIGHT);
            }
            else if(map[xCopy][yCopy + 1] != 0)
            {
                System.out.println("Useless movement");
                System.out.println(xCopy + " " + yCopy);
                return false;
            }
            else
            {
                System.out.println("Move right");
                yCopy++;
                System.out.println(xCopy + " " + yCopy);
                option.add(Direction.RIGHT);
            }
        }
        else if(direction == 2)
        {
            if(map[xCopy + 1][yCopy] == 9)
            {
                map[xCopy + 1][yCopy] = 0;
                birdCount++;
                positions = new ArrayList<>();
                System.out.println("Found bird");
                System.out.println(birdCount);
                option.add(Direction.DOWN);
            }
            else if(map[xCopy + 1][yCopy] != 0)
            {
                System.out.println("Useless movement");
                System.out.println(xCopy + " " + yCopy);
                return false;
            }
            else
            {
                System.out.println("Move down");
                xCopy++;
                System.out.println(xCopy + " " + yCopy);
                option.add(Direction.DOWN);
            }
        }
        else if(direction == 3)
        {
            if(map[xCopy][yCopy - 1] == 9)
            {
                map[xCopy][yCopy - 1] = 0;
                birdCount++;
                positions = new ArrayList<>();
                System.out.println("Found Bird");
                System.out.println(birdCount);
                option.add(Direction.LEFT);
            }
            else if(map[xCopy][yCopy - 1] != 0)
            {
                System.out.println("Useless movement");
                System.out.println(xCopy + " " + yCopy);
                return false;
            }
            else
            {
                System.out.println("Move left");
                yCopy--;
                System.out.println(xCopy + " " + yCopy);
                option.add(Direction.LEFT);
            }
        }
        if(birdCount == 4)
        {
            System.out.println("Chemin trouver");
            listDirection.add(option);
            option.remove(option.size()-1);
            return true;
        }
        if(demiTour)
        {
            if(direction == 0)
            {
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, true))
                {

                }
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
                {

                }
            }
            else if(direction == 1)
            {
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, true))
                {

                }
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
                {

                }
            }
            else if(direction == 2)
            {
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, true))
                {

                }
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
                {

                }
            }
            else if(direction == 3)
            {
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, true))
                {

                }
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
                {

                }
            }
        }
        else
        {
            if(option.size() >= 2)
            {
                if(direction == 2 && option.get(option.size() - 2) == Direction.UP)
                {
                    if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, true))
                    {

                    }
                    if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
                    {

                    }
                    if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
                    {

                    }
                }
                else if(direction == 0 && option.get(option.size() - 2) == Direction.DOWN)
                {
                    if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, true))
                    {

                    }
                    if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
                    {

                    }
                    if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
                    {

                    }
                }
                else if(direction == 1 && option.get(option.size() - 2) == Direction.LEFT)
                {
                    if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, true))
                    {

                    }
                    if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
                    {

                    }
                    if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
                    {

                    }
                }
                else if(direction == 3 && option.get(option.size() - 2) == Direction.RIGHT)
                {
                    if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, true))
                    {

                    }
                    if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
                    {

                    }
                    if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
                    {

                    }
                }
                else
                {
                    if(direction == 0)
                    {
                        if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                        if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                        if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                        if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                    }
                    else if(direction == 1)
                    {
                        if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                        if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                        if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                        if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                    }
                    else if(direction == 2)
                    {
                        if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                        if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                        if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                        if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                    }
                    else if(direction == 3)
                    {
                        if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                        if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                        if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                        if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
                        {

                        }
                    }
                }
            }
            else if(direction == 0)
            {
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
                {

                }
            }
            else if(direction == 1)
            {
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
                {

                }
            }
            else if(direction == 2)
            {
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
                {

                }
            }
            else if(direction == 3)
            {
                if(yCopy > 0 && !testUneDirection(map, 3, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(xCopy > 0 && !testUneDirection(map, 0, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(yCopy < 19 && !testUneDirection(map, 1, option, xCopy, yCopy, birdCount, false))
                {

                }
                if(xCopy < 9 && !testUneDirection(map, 2, option, xCopy, yCopy, birdCount, false))
                {

                }
            }
        }
        int[] position = new int[2];
        position[0] = xCopy;
        position[1] = yCopy;
        positions.add(position);
        option.remove(option.size()-1);
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
