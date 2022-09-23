import org.w3c.dom.ls.LSOutput;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    public Timer timer = new Timer();
    public TimerTask task = new TimerTask() {
        @Override
        public void run() {
            System.out.println("test");
        }
    };
    int[][] matrix = new int[10][20];

    public void Game()
    {
        for(int i = 0 ; i < 10 ; i++)
        {
            for(int j = 0 ; j < 20 ; j++)
            {
                matrix[i][j] = 0;
            }
        }
    }
    public static void main(String[] args)
    {
        Game game = new Game();
        game.timer.schedule(game.task, 1000, 500);
    }
}
