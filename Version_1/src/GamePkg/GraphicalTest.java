package GamePkg;

import Graphics.*;


public class GraphicalTest {
    static int cpt = 10;

    public static void main(String[] args) {

        Game game = new Game();
        game.timerController.schedule(game.timer, 60000);
        game.timerController.schedule(game.refresh, 2000, 2000);

    }

}
