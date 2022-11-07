package GamePkg;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map {
    private Player player = new Player(0, 0);
    private int currentMap = 1;
    private int[][] map;
    private final int ligne = 10;
    private final int colonne = 20;

    private ArrayList<Entity> entities = new ArrayList();
    public Map(){
        map = new int[ligne][colonne];
    }
    public int getCurrentMap() {
        return currentMap;
    }
    public void setCurrentMap(int num){
        currentMap += num;
    }

    public void loadMap(int mapInt) {
        try
        {
            entities.clear();
            File file = new File("map/" + mapInt);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            int x = 0;
            int y;
            while((line = br.readLine()) != null)
            {
                String[] list = line.split(" ");
                y = 0;
                for(String identifier : list)
                {
                    initMap(x,y,Integer.parseInt((String) identifier.split(":")[0]));
                    if(Integer.parseInt((String) identifier.split(":")[0]) == 1)
                    {
                        BreakableBlock breakableBlock = new BreakableBlock(x, y);
                        entities.add(breakableBlock);
                    }
                    else if(Integer.parseInt((String) identifier.split(":")[0]) == 2)
                    {
                        PushBlock pushBlock = new PushBlock(x, y);
                        entities.add(pushBlock);
                    }
                    else if(Integer.parseInt((String) identifier.split(":")[0]) == 3)
                    {
                        BoobyTrap boobyTrap = new BoobyTrap(x, y);
                        entities.add(boobyTrap);
                    }
                    else if(Integer.parseInt((String) identifier.split(":")[0]) == 4)
                    {
                        Block block = new Block(x, y);
                        entities.add(block);
                    }
                    else if(Integer.parseInt((String) identifier.split(":")[0]) == 5)
                    {
                        TeleportationBlock teleportationBlock = new TeleportationBlock(x, y, Integer.parseInt((String) identifier.split(":")[1]));
                        entities.add(teleportationBlock);
                    }
                    else if(Integer.parseInt((String) identifier.split(":")[0]) == 6)
                    {
                        DriveBlock driveBlock = new DriveBlock(x, y, Direction.values()[Integer.parseInt((String) identifier.split(":")[1])]);
                        entities.add(driveBlock);
                    }
                    else if(Integer.parseInt((String) identifier.split(":")[0]) == 8)
                    {
                        player = new Player(x, y);
                        entities.add((player));
                    }
                    else if(Integer.parseInt((String) identifier.split(":")[0]) == 9)
                    {
                        Bird bird = new Bird(x, y, Direction.values()[Integer.parseInt((String) identifier.split(":")[1])]);
                        entities.add(bird);
                    }
                    y++;
                }
                System.out.println(list);
                x++;
            }
            fr.close();
            Ball ball = new Ball();
            entities.add(ball);
            for(Entity entity : entities)
            {
                map[entity.getX()][entity.getY()] = entity.getIdentifier();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    private void initMap(int x, int y, int valeur){
        this.map[x][y]=valeur;
    }
    public int[][] getMap() {

        return map;
    }

    public Player getPlayer() {
        return player;
    }
}
