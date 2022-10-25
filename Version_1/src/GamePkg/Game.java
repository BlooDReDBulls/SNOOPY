package GamePkg;

import Graphics.GraphicFrame;
import Graphics.GraphicPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Game{

    int[][] map = new int[10][20];
    Timer timerController = new Timer();
    Player player = new Player();
    DriveBlock driveBlock = new DriveBlock(2, 8, Direction.UP);

    BoobyTrap boobyTrap = new BoobyTrap(6, 12);

    Ball ball = new Ball();
    //Block bird = new Block(5, 1);
    PushBlock pushBlock = new PushBlock(1, 2);

    ArrayList<Entity> entities = new ArrayList<Entity>();

    KeyAdapter keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if(player.bl){
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
            if(player.bl)
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


    private GraphicFrame graphicFrame;
    private GraphicPanel graphicPanel;



    public Game() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = 0;
            }
        }

        graphicPanel = new GraphicPanel(this);
        graphicFrame = new GraphicFrame(graphicPanel);
        graphicFrame.addKeyListener(keyListener);

        entities.add(pushBlock);
        entities.add(ball);
        entities.add(boobyTrap);
        entities.add(driveBlock);
        entities.add(player);

        for(Entity entity : entities)
        {
            map[entity.getX()][entity.getY()] = entity.getIdentifier();
        }

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
            for(Entity entity : entities)
            {
                if(entity.isMove())
                {
                    if(entity.getIdentifier() != 7)
                    {
                        entity.updatePosition();
                    }
                    map[entity.getLastX()][entity.getLastY()] = 0;
                    map[entity.getX()][entity.getY()] = entity.getIdentifier();
                }
            }
            checkIntersection();
            graphicPanel.update();
        }
    };

    public void checkIntersection()
    {
        if(player.getX() == pushBlock.getX() && player.getY() == pushBlock.getY())
        {
            player.bl = true;
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
            }
            else
            {
                if(player.itsDirection == Direction.ANY)
                {
                    if(player.itsLastDirection == Direction.DOWN)
                    {
                        player.itsDirection = Direction.UP;
                        player.updatePosition();
                        player.itsDirection = Direction.ANY;
                    }
                    else if(player.itsLastDirection == Direction.UP)
                    {
                        player.itsDirection = Direction.DOWN;
                        player.updatePosition();
                        player.itsDirection = Direction.ANY;
                    }
                    else if(player.itsLastDirection == Direction.RIGHT)
                    {
                        player.itsDirection = Direction.LEFT;
                        player.updatePosition();
                        player.itsDirection = Direction.ANY;
                    }
                    else if(player.itsLastDirection == Direction.LEFT)
                    {
                        player.itsDirection = Direction.RIGHT;
                        player.updatePosition();
                        player.itsDirection = Direction.ANY;
                    }
                }
                else
                {
                    if(player.itsDirection == Direction.DOWN)
                    {
                        player.itsDirection = Direction.UP;
                        player.updatePosition();
                        player.itsDirection = Direction.ANY;
                    }
                    else if(player.itsDirection == Direction.UP)
                    {
                        player.itsDirection = Direction.DOWN;
                        player.updatePosition();
                        player.itsDirection = Direction.ANY;
                    }
                    else if(player.itsDirection == Direction.RIGHT)
                    {
                        player.itsDirection = Direction.LEFT;
                        player.updatePosition();
                        player.itsDirection = Direction.ANY;
                    }
                    else if(player.itsDirection == Direction.LEFT)
                    {
                        player.itsDirection = Direction.RIGHT;
                        player.updatePosition();
                        player.itsDirection = Direction.ANY;
                    }
                }
            }
        }
        else if(player.getX() == driveBlock.getX() && player.getY() == driveBlock.getY()){
            player.itsDirection = driveBlock.getItsDirection();
            player.bl = false;
        }
        else if(player.getX() == boobyTrap.getX() && player.getY() == boobyTrap.getY()){
            player.kill();
            if(player.getNumberLife() > 0)
            {
                //fonction load

            }
        }
        else if (player.getX() == ball.getX() && player.getY() == ball.getY()) {
            player.kill();
        }
        /*else if(player.getX() == bird.getX() && player.getY() == bird.getY()){
            player.bird();
            map[bird.getX()][bird.getY()] = 0;
        }*/
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

    public void loadMap(int mapInt)
    {

    }

    public static void main(String[] args){
        Game game = new Game();
        game.timerController.schedule(game.timer, 60000);
        game.timerController.schedule(game.refresh, 0, 50);
    }
}
