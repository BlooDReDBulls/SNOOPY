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
    Map map = new Map();
    PushBlock pushBlock = new PushBlock(2, 3);
    Timer timerController = new Timer();
    /*DriveBlock driveBlock = new DriveBlock(2, 8, Direction.UP);
    BoobyTrap boobyTrap = new BoobyTrap(6, 12);
    Ball ball = new Ball();
    Bird bird = new Bird(5, 1);
    PushBlock pushBlock = new PushBlock(1, 2);*/
    Timer playerMovementTimer = new Timer();
    ArrayList<Entity> entities = new ArrayList<Entity>();
    List<Observateur> observateurs;

    KeyAdapter keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if(map.getPlayer().unBlockMovement){
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    map.getPlayer().itsDirection = Direction.LEFT;
                }
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    map.getPlayer().itsDirection = Direction.RIGHT;
                }
                else if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    map.getPlayer().itsDirection = Direction.UP;
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    map.getPlayer().itsDirection = Direction.DOWN;
                }
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {
            super.keyPressed(e);
            if(map.getPlayer().unBlockMovement)
            {
                if(e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    map.getPlayer().itsLastDirection = Direction.LEFT;
                    map.getPlayer().itsDirection = Direction.ANY;
                }
                else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    map.getPlayer().itsLastDirection = Direction.RIGHT;
                    map.getPlayer().itsDirection = Direction.ANY;
                }
                else if(e.getKeyCode() == KeyEvent.VK_UP)
                {
                    map.getPlayer().itsLastDirection = Direction.UP;
                    map.getPlayer().itsDirection = Direction.ANY;
                }
                else if(e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    map.getPlayer().itsLastDirection = Direction.DOWN;
                    map.getPlayer().itsDirection = Direction.ANY;
                }
            }
        }
    };


    private BufferedReader reader;

    public Game() {
        observateurs = new ArrayList<Observateur>();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                map.getMap()[i][j] = 0;
            }
        }

        for(Entity entity : map.getEntities())
        {
            map.getMap()[entity.getX()][entity.getY()] = entity.getIdentifier();
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
            map.getPlayer().updatePosition(map.getMap(), pushBlock);
        }
    };

    public TimerTask refresh = new TimerTask() {
        @Override
        public void run() {
            for(Entity entity : entities)
            {
                if(entity.isMove())
                {
                    map.getMap()[entity.getLastX()][entity.getLastY()] = 0;
                    map.getMap()[entity.getX()][entity.getY()] = entity.getIdentifier();
                }
                else
                {
                    if(entity.getIdentifier() == 5 || entity.getIdentifier() == 6 || entity.getIdentifier() == 9)
                    {
                        if(entity.isVisible())
                        {
                            map.getMap()[entity.getX()][entity.getY()] = entity.getIdentifier();
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
        for(Entity entity : map.getEntities())
        {
            if(entity.getIdentifier() != 8)
            {
                if(map.getPlayer().getX() == entity.getX() && map.getPlayer().getY() == entity.getY())
                {
                    if(!entity.isCollision())
                    {
                        if(entity.getIdentifier() == 3 || entity.getIdentifier() == 7)
                        {
                            map.getPlayer().kill();
                        }
                        else if(entity.getIdentifier() == 9)
                        {
                            map.getPlayer().bird();
                            map.getMap()[entity.getX()][entity.getY()] = 0;
                            if(map.getPlayer().win()){
                                map.setCurrentMap(1);
                                map.loadMap(map.getCurrentMap());
                            }
                        }
                        else if(entity.getIdentifier() == 6)
                        {
                            //map.getPlayer().itsDirection = entity.getItsDirection();
                            map.getPlayer().unBlockMovement = false;
                        }
                    }
                    else
                    {
                        map.getPlayer().unBlockMovement = true;
                        if(entity.isPushable())
                        {
                            if(map.getPlayer().itsDirection == Direction.ANY)
                            {
                                entity.push(map.getPlayer().itsLastDirection);
                            }
                            else
                            {
                                entity.push(map.getPlayer().itsDirection);
                            }
                            map.getMap()[entity.getX()][entity.getY()] = entity.getIdentifier();
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
                System.out.print(map.getMap()[i][j] + " ");
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
                mapString += map.getMap()[i][j];
                mapString += " ";
            }
            mapString += '\n';
        }
        Path filePath = Path.of("saveFile.txt");
        Files.writeString(filePath, mapString);
    }

    public int[][] getMap() {
        return map.getMap();
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
