package Graphics;

import GamePkg.Game;

public class GameConsole implements Observateur{

    private final Game game;


    public GameConsole(Game game){
        this.game = game;
    }

    @Override
    public void actualise() {

        for(int i = 0 ; i < 10 ; i++)
        {
            for(int j = 0 ; j < 20 ; j++)
            {
                System.out.print(game.getMap().getMap()[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
}
