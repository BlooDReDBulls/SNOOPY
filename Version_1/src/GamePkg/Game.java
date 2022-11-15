package GamePkg;

import Graphics.*;
import Graphics.Observable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.KeyStore;
import java.util.*;
import javax.swing.Timer;


public class Game implements Observable{

    Map map = new Map();
    public Timer timerController;
    public Timer refreshTimer;
    ArrayList<Observateur> observateurs;
    private boolean pause;
    private int remaingSeconds = 60;
    public Timer timerSeconds;

    private int numberOfLife;

    public boolean userEnd = false;

    GameUI gameUI;

    public Game(int AIChoice) throws IOException {
        map.loadMap(1, 0);
        pause = false;
        timerController = new Timer(60000, timer);
        timerSeconds = new Timer(1000, seconds);
        refreshTimer = new Timer(50, refresh);
        timerController.setRepeats(false);
        timerSeconds.setRepeats(true);
        refreshTimer.setRepeats(true);
        if(AIChoice != 0)
        {
            map.getPlayer().initAI(AIChoice, map.getMap(), map.getEntities(), map.getBall());
        }
        timerSeconds.start();
        timerController.start();
        refreshTimer.start();
        observateurs = new ArrayList<>();
        numberOfLife = 3;

        this.gameUI = new GameUI(this);
//        GameConsole gameConsole = new GameConsole(this);
//        this.attacheObservateur(gameConsole);
        this.attacheObservateur(gameUI);
        this.gameUI.setKeyListener(keyListener);

    }

    KeyAdapter keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if(!map.getPlayer().isAiMode())
            {
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

                    for (Entity entity : getMap().getEntities()){
                        if(entity.getIdentifier() == 1){
                            if(map.getPlayer().getX() + 1 <= 9 && map.getPlayer().getX() + 1 == entity.getX() && map.getPlayer().getY() == entity.getY())
                            {
                                map.getMap()[map.getPlayer().getX() + 1][map.getPlayer().getY()] = 0;
                                entity.setVisible(false);
                            }
                            if(map.getPlayer().getX() - 1 >= 0 && map.getPlayer().getX() - 1 == entity.getX() && map.getPlayer().getY() == entity.getY())
                            {
                                map.getMap()[map.getPlayer().getX() - 1][map.getPlayer().getY()] = 0;
                                entity.setVisible(false);
                            }
                            if(map.getPlayer().getY() + 1 <= 19 && map.getPlayer().getX() == entity.getX() && map.getPlayer().getY() + 1 == entity.getY())
                            {
                                map.getMap()[map.getPlayer().getX()][map.getPlayer().getY() + 1] = 0;
                                entity.setVisible(false);
                            }
                            if(map.getPlayer().getY() - 1 >= 0 && map.getPlayer().getX() == entity.getX() && map.getPlayer().getY() - 1 == entity.getY())
                            {
                                map.getMap()[map.getPlayer().getX()][map.getPlayer().getY() - 1] = 0;
                                entity.setVisible(false);
                            }
                        }
                    }
                }
                else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
                {
                    pause = !pause;
                    changePauseState(pause);
                    gameUI.showPauseMenu(pause);
                    gameUI.getJbQuit().addActionListener(q ->{
                        userEnd = true;
                    });
                }
                else if (e.getKeyCode() == KeyEvent.VK_S)
                {
                    pause = !pause;

                    gameUI.showSaveMenu(pause);
                    changePauseState(pause);
                    gameUI.getJbOK().addActionListener(t->{

                        try {
                            save(gameUI.getSaveName());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });

                }
            }
        }
    };

    public ActionListener timer = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Game over");
            kill();
//            map.loadMap(map.getCurrentMap(), map.getPlayer().itsScore);
//            timerController.restart();
//            timerSeconds.restart();
            remaingSeconds = 0;
        }
    };
    public ActionListener seconds = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            remaingSeconds -= 1;

            if(remaingSeconds == 0){
                remaingSeconds = 0;
            }
            //System.out.println(remaingSeconds);
            notifieTimers();
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
                        if(map.getPlayer().isAiMode() && map.getPlayer().itsDirection == Direction.ANY)
                        {
                            for (Entity block : getMap().getEntities()){
                                if(block.getIdentifier() == 1){
                                    if(map.getPlayer().getX() + 1 <= 9 && map.getPlayer().getX() + 1 == block.getX() && map.getPlayer().getY() == block.getY())
                                    {
                                        map.getMap()[map.getPlayer().getX() + 1][map.getPlayer().getY()] = 0;
                                        block.setVisible(false);
                                    }
                                    if(map.getPlayer().getX() - 1 >= 0 && map.getPlayer().getX() - 1 == block.getX() && map.getPlayer().getY() == block.getY())
                                    {
                                        map.getMap()[map.getPlayer().getX() - 1][map.getPlayer().getY()] = 0;
                                        block.setVisible(false);
                                    }
                                    if(map.getPlayer().getY() + 1 <= 19 && map.getPlayer().getX() == block.getX() && map.getPlayer().getY() + 1 == block.getY())
                                    {
                                        map.getMap()[map.getPlayer().getX()][map.getPlayer().getY() + 1] = 0;
                                        block.setVisible(false);
                                    }
                                    if(map.getPlayer().getY() - 1 >= 0 && map.getPlayer().getX() == block.getX() && map.getPlayer().getY() - 1 == block.getY())
                                    {
                                        map.getMap()[map.getPlayer().getX()][map.getPlayer().getY() - 1] = 0;
                                        block.setVisible(false);
                                    }
                                }
                            }
                        }
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
                            map.loadMap(map.getCurrentMap(), map.getPlayer().itsScore);
                            timerController.restart();
                            timerSeconds.restart();
                            remaingSeconds = 60;
                            break;
                        }
                        else if(entity.getIdentifier() == 9 && entity.isVisible())
                        {
                            map.getPlayer().bird();
                            entity.setVisible(false);
                            map.getMap()[entity.getX()][entity.getY()] = 0;
                            if(map.getPlayer().win()){
                                map.getPlayer().addScore(remaingSeconds);
                                notifieTimers();
                                if(map.getCurrentMap() == 3){
                                    pause = !pause;
                                    changePauseState(pause);
                                    gameUI.showEndPanel(true);

                                }
                                else
                                {
                                    map.setCurrentMap(1);
                                    map.loadMap(map.getCurrentMap(), map.getPlayer().itsScore);
                                    map.getPlayer().initAI(1, map.getMap(), map.getEntities(), map.getBall());
                                    timerController.restart();
                                    timerSeconds.restart();
                                    remaingSeconds = 60;
                                }
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

    private void changePauseState(Boolean bool){

        if(bool){
            timerController.stop();
            timerSeconds.stop();
        }else{
            timerController.start();
            timerSeconds.start();
        }
    }


    public void save(String name) throws IOException {
        String mapString = "";
        for(int i = 0 ; i < 10 ; i++)
        {
            for(int j = 0 ; j < 20 ; j++)
            {
                if(map.getMap()[i][j] == 5)
                {
                    for(Entity entity : map.getEntities())
                    {
                        if(entity.getX() == i && entity.getY() == j)
                        {
                            mapString += map.getMap()[i][j] + ":" + entity.getTeleportationIdentifier();
                        }
                    }
                }
                else if(map.getMap()[i][j] == 6 || map.getMap()[i][j] == 9)
                {
                    for(Entity entity : map.getEntities())
                    {
                        if(entity.getX() == i && entity.getY() == j)
                        {
                            mapString += map.getMap()[i][j] + ":" + entity.itsDirection.ordinal();
                        }
                    }
                }
                else
                {
                    mapString += map.getMap()[i][j];
                }
                mapString += " ";
            }
            mapString += '\n';
        }
        mapString += "Score:" + getScore() + " Vie:" + numberOfLife + "\n";
        File file = new File("save/" + name);
        Path filePath = Path.of("save/" + file.getName());
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
    @Override
    public void notifieTimers() {
        for (Observateur o: observateurs) {
            o.actualiseTimer();
        }
    }

    public int getRemaingSeconds() {
        return remaingSeconds;
    }

    public int getScore(){
        return getMap().getPlayer().itsScore;
    }

    public void kill() {
        numberOfLife -= 1;
        if (numberOfLife == 0) {
            pause = true;
            changePauseState(false);
            gameUI.showEndPanel(false);
        }
    }
}
