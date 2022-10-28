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
    private BufferedReader reader;
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

    public BufferedReader getReader() {
        return reader;
    }


    public void loadMap(int mapInt) throws IOException {
        try
        {
            File file = new File("map/2");
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
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }
    private void initEntities(){
        for (int i = 0; i < ligne ; i++) {
            for (int j = 0; j < colonne; j++) {
                //créé entite chaque objet
                if(map[i][j] == 1){
                    //bloc cassable
                }
                else if(map[i][j] == 2){
                    PushBlock pushBlock = new PushBlock(i, j);
                }
                else if(map[i][j] == 3){
                    BoobyTrap boobyTrap = new BoobyTrap(i, j);
                }
                else if(map[i][j] == 4){
                    //bloc invincible
                }
                else if(map[i][j] == 5){
                    //bloc tp
                }
                else if(map[i][j] == 6){
                    DriveBlock driveBlock = new DriveBlock(i, j, Direction.ANY);
                    entities.add(driveBlock);
                }
                else if(map[i][j] == 8){
                    Player copyPlayer = new Player(i, j);
                    player = copyPlayer;
                }
                else if(map[i][j] == 9){
                    Bird bird = new Bird(i, j);
                }
                Ball ball = new Ball();

            }
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