package GamePkg;

import Graphics.*;
import Graphics.Observable;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Game implements Observable{

    int[][] map = new int[10][20];
    int currentMap = 1;
    Timer timerController = new Timer();
    Player player = new Player();
    DriveBlock driveBlock = new DriveBlock(2, 8, Direction.UP);

    BoobyTrap boobyTrap = new BoobyTrap(6, 12);

    Ball ball = new Ball();
    Bird bird = new Bird(5, 1);
    PushBlock pushBlock = new PushBlock(1, 2);

    Timer playerMovementTimer = new Timer();

    ArrayList<Entity> entities = new ArrayList<Entity>();
    List<Observateur> observateurs;

    KeyAdapter keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if(player.unBlockMovement){
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    player.itsDirection = Direction.LEFT;
                }
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    player.itsDirection = Direction.RIGHT;
                }
                else if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    player.itsDirection = Direction.UP;
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    player.itsDirection = Direction.DOWN;
                }
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyPressed(e);
            if(player.unBlockMovement)
            {
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    player.itsLastDirection = Direction.LEFT;
                    player.itsDirection = Direction.ANY;
                }
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    player.itsLastDirection = Direction.RIGHT;
                    player.itsDirection = Direction.ANY;
                }
                else if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    player.itsLastDirection = Direction.UP;
                    player.itsDirection = Direction.ANY;
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    player.itsLastDirection = Direction.DOWN;
                    player.itsDirection = Direction.ANY;
                }
            }
        }
    };


    private BufferedReader reader;



    public Game() {
        observateurs = new ArrayList<Observateur>();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = 0;
            }
        }

        entities.add(pushBlock);
        entities.add(boobyTrap);
        entities.add(driveBlock);
        entities.add(ball);
        entities.add(player);
        entities.add(bird);

        for(Entity entity : entities)
        {
            map[entity.getX()][entity.getY()] = entity.getIdentifier();
        }

        GameConsole gameConsole = new GameConsole(this);
        GameUI gameUI = new GameUI(this);


        //this.attacheObservateur(gameConsole);
        this.attacheObservateur(gameUI);

        gameUI.setKeyListener(keyListener);
    }
    public TimerTask timer = new TimerTask() {
        @Override
        public void run() {
            System.out.println("Fin");
            while(true);
        }
    };

    public TimerTask playerMovement = new TimerTask() {
        @Override
        public void run() {
            player.updatePosition(map, pushBlock);
        }
    };

    public TimerTask refresh = new TimerTask() {
        @Override
        public void run() {
            for(Entity entity : entities)
            {
                if(entity.isMove())
                {
                    map[entity.getLastX()][entity.getLastY()] = 0;
                    map[entity.getX()][entity.getY()] = entity.getIdentifier();
                }
                else
                {
                    if(entity.getIdentifier() == 5 || entity.getIdentifier() == 6 || entity.getIdentifier() == 9)
                    {
                        if(entity.isVisible())
                        {
                            map[entity.getX()][entity.getY()] = entity.getIdentifier();
                        }
                    }
                }
            }
            try {
                checkIntersection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //graphicPanel.actualise();
            notifieObservateurs();
        }
    };

    public void checkIntersection() throws IOException {
        for(Entity entity : entities)
        {
            if(entity.getIdentifier() != 8)
            {
                if(player.getX() == entity.getX() && player.getY() == entity.getY())
                {
                    if(!entity.isCollision())
                    {
                        if(entity.getIdentifier() == 3 || entity.getIdentifier() == 7)
                        {
                            player.kill();
                        }
                        else if(entity.getIdentifier() == 9)
                        {
                            player.bird();
                            map[entity.getX()][entity.getY()] = 0;
                            if(player.win()){
                                currentMap += 1;
                                loadMap(currentMap);
                            }
                        }
                        else if(entity.getIdentifier() == 6)
                        {
                            player.itsDirection = driveBlock.getItsDirection();
                            player.unBlockMovement = false;
                        }
                    }
                    else
                    {
                        player.unBlockMovement = true;
                        if(pushBlock.isPushable())
                        {
                            if(player.itsDirection == Direction.ANY)
                            {
                                pushBlock.push(player.itsLastDirection);
                            }
                            else
                            {
                                pushBlock.push(player.itsDirection);
                            }
                            map[pushBlock.getX()][pushBlock.getY()] = pushBlock.getIdentifier();
                        }
                    }
                }
            }
        }
    }

    public void displayMap()
    {
        for(int i = 0 ; i < 10 ; i++)
        {
            for(int j = 0 ; j < 20 ; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public void save() throws IOException {
        String mapString = "";
        for(int i = 0 ; i < 10 ; i++)
        {
            for(int j = 0 ; j < 20 ; j++)
            {
                mapString += map[i][j];
                mapString += " ";
            }
            mapString += '\n';
        }
        Path filePath = Path.of("saveFile.txt");
        Files.writeString(filePath, mapString);
    }

    public int[][] getMap() {
        return map;
    }

    public void loadMap(int mapInt) throws IOException {
        reader = new BufferedReader(new FileReader(mapInt + ".txt"));
    }

    public static void main(String[] args){
        Game game = new Game();
        game.timerController.schedule(game.timer, 60000);
        game.timerController.schedule(game.refresh, 0, 50);
        game.playerMovementTimer.schedule(game.playerMovement, 0, 100);
    }

    @Override
    public void attacheObservateur(Observateur o) {
        observateurs.add(o);
    }

    @Override
    public void detacheObservateur(Observateur o) {
        observateurs.remove(o);
    }

    @Override
    public void notifieObservateurs() {
        for (Observateur o: observateurs) {
            o.actualise();
        }
    }
}
