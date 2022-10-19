package GamePkg;

import Graphics.GraphicFrame;
import Graphics.GraphicPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Timer;
import java.util.TimerTask;


public class Game{

    int[][] map = new int[10][20];
    Timer timerController = new Timer();
    Player player = new Player();
    DriveBlock driveBlock = new DriveBlock(2, 8, Direction.UP);

    Block boobyTrap = new Block(6, 12);

    Ball ball = new Ball();
    Block bird = new Block(5, 1);
    PushBlock pushBlock = new PushBlock(1, 2);

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
    private BufferedReader reader;


    public Game() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = 0;
            }
        }

        graphicPanel = new GraphicPanel(this);
        graphicFrame = new GraphicFrame(graphicPanel);
        graphicFrame.addKeyListener(keyListener);
        map[bird.getX()][bird.getY()] = 9;


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
            map[player.getX()][player.getY()] = 0;
            player.updatePosition();
            checkIntersection();
            map[ball.getLastX()][ball.getLastY()] = 0;
            map[player.getX()][player.getY()] = 8;
            map[ball.getX()][ball.getY()] = 7;
            map[pushBlock.getX()][pushBlock.getY()] = 2;
            map[driveBlock.getX()][driveBlock.getY()] = 6;
           // displayMap();
            map[boobyTrap.getX()][boobyTrap.getY()] = 3;
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
        else if(player.getX() == bird.getX() && player.getY() == bird.getY()){
            player.bird();
            map[bird.getX()][bird.getY()] = 0;
            player.win();
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

    public void loadMap(int mapInt) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(mapInt + ".txt"));
    }

    public static void main(String[] args){
        Game game = new Game();
        game.timerController.schedule(game.timer, 60000);
        game.timerController.schedule(game.refresh, 0, 50);
    }
}
