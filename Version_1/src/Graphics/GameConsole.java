package Graphics;

import GamePkg.Game;

/**
 * @author lucien
 * Classe permetant de faire de l'affichage console
 * @see Observateur
 */
public class GameConsole implements Observateur{

    private final Game game;

    /**
     * Constructeur de la classe GameConsole
     * @param game une instance de game
     */
    public GameConsole(Game game){
        this.game = game;
    }

    /**
     * Méthode permettant d'actualiser l'affichage console de la map
     */
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

    /**
     * Méthode permettant d'actualiser le nombre de secondes restantes
     */
    @Override
    public void actualiseTimer() {
        System.out.println(game.getRemaingSeconds());
    }


}
