import java.util.Timer;
import java.util.TimerTask;

public class Game {

    int[][] map = new int[10][20];
    public Timer timerController = new Timer();

    public Game() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = 0;
            }
        }
    }
    public TimerTask timer = new TimerTask() {
        @Override
        public void run() {
            System.out.println("Fin");
        }
    };

    public TimerTask refresh = new TimerTask() {
        @Override
        public void run() {
            displayMap();
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

    public static void main(String[] args)
    {
        Game game = new Game();
        game.timerController.schedule(game.timer, 60000);
        game.timerController.schedule(game.refresh, 200, 200);
    }
}
