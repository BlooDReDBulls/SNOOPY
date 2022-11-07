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
            while((line = br.readLine()) != null)
            {
                List list = Arrays.stream((line.split(" "))).toList();
                for (int i = 0; i < list.size(); i++) {
                    initMap(x,i,Integer.parseInt((String) list.get(i)));
                }
                System.out.println(list);
                x++;
            }
            fr.close();
            initEntities();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    private void initEntities(){
        entities.clear();
        for (int i = 0; i < ligne ; i++) {
            for (int j = 0; j < colonne; j++) {
                //créé entite chaque objet
                if(map[i][j] == 1){
                    //bloc cassable
                }
                else if(map[i][j] == 2){
                    PushBlock pushBlock = new PushBlock(i, j);
                    entities.add(pushBlock);
                }
                else if(map[i][j] == 3){
                    BoobyTrap boobyTrap = new BoobyTrap(i, j);
                    entities.add(boobyTrap);
                }
                else if(map[i][j] == 4){
                    Block block = new Block(i, j);
                    entities.add(block);
                }
                else if(map[i][j] == 5){
                    //bloc tp
                    TeleportationBlock teleportationBlock = new TeleportationBlock(i, j, 1);
                    entities.add(teleportationBlock);
                }
                else if(map[i][j] == 6){
                    DriveBlock driveBlock = new DriveBlock(i, j, Direction.UP);
                    entities.add(driveBlock);
                }
                else if(map[i][j] == 8){
                    player = new Player(i, j);
                    entities.add((player));
                }
                else if(map[i][j] == 9){
                    Bird bird = new Bird(i, j);
                    entities.add(bird);
                }
                Ball ball = new Ball();
                entities.add(ball);
            }
        }
        for(Entity entity : entities)
        {
            map[entity.getX()][entity.getY()] = entity.getIdentifier();
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
