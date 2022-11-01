package GamePkg;

import Graphics.*;
import Graphics.Observable;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Game implements Observable{

    Map map = new Map();
    Timer timerController = new Timer();
    List<Observateur> observateurs;

    GamePanel gamePanel;
    GameUI gameUI;

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



    public Game() throws IOException {
        map.loadMap(1);
        observateurs = new ArrayList<Observateur>();
        GameConsole gameConsole = new GameConsole(this);
//        this.gameUI = new GameUI(this);
        this.gamePanel = new GamePanel(this);

        for(Entity entity : map.getEntities())
        {
            map.getMap()[entity.getX()][entity.getY()] = entity.getIdentifier();
        }

        this.attacheObservateur(gamePanel);
//        this.attacheObservateur(gameConsole);
//       this.attacheObservateur(gameUI);



        this.gamePanel.setKeyListener(keyListener);
    }
    public TimerTask timer = new TimerTask() {
        @Override
        public void run() {
            System.out.println("Fin");
            while(true);
        }
    };
    public TimerTask refresh = new TimerTask() {
        @Override
        public void run() {
            for(Entity entity : map.getEntities())
            {
                if(entity.isMove())
                {
                    entity.updatePosition(map.getMap(), map.getEntities());
                    map.getMap()[entity.getLastX()][entity.getLastY()] = 0;
                    map.getMap()[entity.getX()][entity.getY()] = entity.getIdentifier();
                }
                else
                {
                    if(entity.isVisible())
                    {
                        map.getMap()[entity.getX()][entity.getY()] = entity.getIdentifier();
                    }
                }
            }
            try {
                checkIntersection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
                            entity.setVisible(false);
                            map.getMap()[entity.getX()][entity.getY()] = 0;
                            if(map.getPlayer().win()){
                                map.setCurrentMap(1);
                                map.loadMap(map.getCurrentMap());
                            }
                        }
                        else if(entity.getIdentifier() == 6)
                        {
                            map.getPlayer().itsDirection = entity.itsDirection;
                            map.getPlayer().unBlockMovement = false;
                        }
                        else if(entity.getIdentifier() == 5)
                        {
                            if(entity.activated)
                            {
                                entity.activated = false;
                                for(Entity endPoint : map.getEntities())
                                {
                                    if(endPoint.getIdentifier() == 5 && endPoint.getTeleportationIdentifier() == entity.getTeleportationIdentifier() && endPoint.activated)
                                    {
                                        endPoint.activated = false;
                                        map.getPlayer().setLastX(map.getPlayer().getX());
                                        map.getPlayer().setX(endPoint.getX());
                                        map.getPlayer().setLastY(map.getPlayer().getY());
                                        map.getPlayer().setY(endPoint.getY());
                                        break;
                                    }
                                }
                            }
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
                                map.getMap()[entity.getX()][entity.getY()] = entity.getIdentifier();
                            }
                            else
                            {
                                entity.push(map.getPlayer().itsDirection);
                                map.getMap()[entity.getX()][entity.getY()] = entity.getIdentifier();
                            }
                        }
                    }
                }
                else
                {
                    if(entity.getIdentifier() == 5)
                    {
                        entity.activated = true;
                    }
                }
            }
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

    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.timerController.schedule(game.timer, 60000);
        game.timerController.schedule(game.refresh, 0, 50);
    }
}
