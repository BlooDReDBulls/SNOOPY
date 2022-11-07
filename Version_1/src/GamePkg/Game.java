package GamePkg;

import Graphics.*;
import Graphics.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.swing.Timer;


public class Game implements Observable{

    Map map = new Map();
    public Timer timerController;
    public Timer refreshTimer;
    List<Observateur> observateurs;
    private int numberLife;
    private boolean pause;

    GamePanel gamePanel;
    GameUI gameUI;

    public Game() throws IOException {
        map.loadMap(1);
        numberLife = 3;
        pause = false;
        timerController = new Timer(60000, timer);
        refreshTimer = new Timer(50, refresh);
        timerController.setRepeats(false);
        refreshTimer.setRepeats(true);
        timerController.start();
        refreshTimer.start();
        observateurs = new ArrayList<Observateur>();
        GameConsole gameConsole = new GameConsole(this);
//        this.gameUI = new GameUI(this);
        this.gamePanel = new GamePanel(this.getMap());

        this.attacheObservateur(gamePanel);
        //this.attacheObservateur(gameConsole);
//       this.attacheObservateur(gameUI);

        this.gamePanel.setKeyListener(keyListener);
    }

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
            if(e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                if(map.getPlayer().getX() + 1 <= 9 && map.getMap()[map.getPlayer().getX() + 1][map.getPlayer().getY()] == 1)
                {
                    map.getMap()[map.getPlayer().getX() + 1][map.getPlayer().getY()] = 0;
                }
                if(map.getPlayer().getX() - 1 >= 0 && map.getMap()[map.getPlayer().getX() - 1][map.getPlayer().getY()] == 1)
                {
                    map.getMap()[map.getPlayer().getX() - 1][map.getPlayer().getY()] = 0;
                }
                if(map.getPlayer().getY() + 1 <= 19 && map.getMap()[map.getPlayer().getX()][map.getPlayer().getY() + 1] == 1)
                {
                    map.getMap()[map.getPlayer().getX()][map.getPlayer().getY() + 1] = 0;
                }
                if(map.getPlayer().getY() - 1 >= 0 && map.getMap()[map.getPlayer().getX()][map.getPlayer().getY() - 1] == 1)
                {
                    map.getMap()[map.getPlayer().getX()][map.getPlayer().getY() - 1] = 0;
                }
            }
            else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
            {
                pause = !pause;
                if(pause == true)
                {
                    timerController.stop();
                }
                else
                {
                    timerController.start();
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

    public ActionListener timer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Game over");
            while(true);
        }
    };
    public ActionListener refresh = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent a) {
            if(!pause)
            {
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
                            kill();
                            map.loadMap(map.getCurrentMap());
                            timerController.restart();
                            break;
                        }
                        else if(entity.getIdentifier() == 9 && entity.isVisible())
                        {
                            map.getPlayer().bird();
                            entity.setVisible(false);
                            map.getMap()[entity.getX()][entity.getY()] = 0;
                            if(map.getPlayer().win()){
                                map.setCurrentMap(1);
                                map.loadMap(map.getCurrentMap());
                                timerController.restart();
                                break;
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

    public Map getMap() {

        return map;
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
    public void kill() {
        numberLife -= 1;
        if (numberLife == 0) {
            while (true) ;
        }
    }

    public static void main(String[] args) throws IOException {
        Game game = new Game();
    }
}
