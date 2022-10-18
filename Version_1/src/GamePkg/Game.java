package GamePkg;

import Graphics.GraphicFrame;
import Graphics.GraphicPanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Timer;
import java.util.TimerTask;


public class Game{

    int[][] map = new int[10][20];
    Timer timerController = new Timer();
    Player player = new Player();

    Ball ball = new Ball();

    PushBlock pushBlock = new PushBlock(5, 14);

    KeyAdapter keyListener = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            if(e.getKeyCode() == KeyEvent.VK_LEFT)
            {
                player.left();
            }
            else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            {
                player.right();
            }
            else if(e.getKeyCode() == KeyEvent.VK_UP)
            {
                player.up();
            }
            else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            {
                player.down();
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
            map[ball.getX()][ball.getY()] = 0;
            ball.updatePosition();
            map[player.getX()][player.getY()] = 8;
            map[ball.getX()][ball.getY()] = 7;
            map[pushBlock.getX()][pushBlock.getY()] = 2;
           // displayMap();
            graphicPanel.update();

        }
    };

    public void displayMap()
    {
        for(int i = 0 ; i < 10 ; i++)
        {
            for(int j = 0 ; j < 20 ; j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
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

    public static void main(String[] args){
        Game game = new Game();
        game.timerController.schedule(game.timer, 60000);
        game.timerController.schedule(game.refresh, 0, 1000);
    }

}
