package GamePkg;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
/**
 * @author lucas
 * @author thibaud
 * Classe permettant de créer une balle
 * @see Entity
 */
public class Ball extends Entity{
    int xspeed;
    int yspeed;

    boolean unableMovement;
    boolean start;
    Timer speed = new Timer();

    /**
     * Constructeur de la classe Ball
     */
    public Ball(){
        animation = false;
        collision = false;
        identifier = 7;
        move = true;
        x = 0;
        y = 0;
        xspeed = 1;
        yspeed = 1;
        unableMovement = true;
        start = false;
    }

    /**
     * Getter sur collision
     */
    @Override
    public boolean isCollision()
    {
        return collision;
    }
    /**
     * Setter sur pushable
     */
    @Override
    void setPushable(boolean pushable) {

    }

    /**
     * Getter sur pushable
     */
    @Override
    boolean isPushable() {
        return false;
    }

    /**
     * Getter sur TeleportationIdentifier
     */
    @Override
    int getTeleportationIdentifier() {
        return 0;
    }

    /**
     * Méthode permettant de faire faire un déplacement à la balle
     * @param entities la liste des entités du jeu
     * @param map la map
     */
    @Override
    void updatePosition(int[][] map, ArrayList<Entity> entities) {
        if(unableMovement && start)
        {
            lastX = x;
            lastY = y;
            x+=xspeed;
            y+=yspeed;
            if (x + xspeed > 9 || x + xspeed < 0){
                xspeed=-xspeed;
            }
            if(y + yspeed > 19 || y + yspeed < 0){
                yspeed=-yspeed;
            }
            if(map[x + xspeed][y] == 1 || map[x + xspeed][y] == 2 || map[x + xspeed][y] == 3 || map[x + xspeed][y] == 4)
            {
                xspeed=-xspeed;
            }
            if(map[x][y + yspeed] == 1 || map[x][y + yspeed] == 2 || map[x][y + yspeed] == 3 || map[x][y + yspeed] == 4)
            {
                yspeed=-yspeed;
            }
            if(map[x + xspeed][y + yspeed] == 1 || map[x + xspeed][y + yspeed] == 2 || map[x + xspeed][y + yspeed] == 3 || map[x + xspeed][y + yspeed] == 4)
            {
                xspeed=-xspeed;
                yspeed=-yspeed;
            }
            unableMovement = false;
            speed.schedule(new TimerTask() {
                @Override
                public void run() {
                    unableMovement = true;
                }
            }, 250);
        }
    }
    /**
     * Méthode permettant de faire faire un déplacement à la balle pour l'algorithme d'IA
     * @param map la map
     */
    void updatePositionIA(int[][] map) {
        lastX = x;
        lastY = y;
        x+=xspeed;
        y+=yspeed;
        if (x + xspeed > 9 || x + xspeed < 0){
            xspeed=-xspeed;
        }
        if(y + yspeed > 19 || y + yspeed < 0){
            yspeed=-yspeed;
        }
        if(map[x + xspeed][y] == 1 || map[x + xspeed][y] == 2 || map[x + xspeed][y] == 3 || map[x + xspeed][y] == 4)
        {
            xspeed=-xspeed;
        }
        if(map[x][y + yspeed] == 1 || map[x][y + yspeed] == 2 || map[x][y + yspeed] == 3 || map[x][y + yspeed] == 4)
        {
            yspeed=-yspeed;
        }
        if(map[x + xspeed][y + yspeed] == 1 || map[x + xspeed][y + yspeed] == 2 || map[x + xspeed][y + yspeed] == 3 || map[x + xspeed][y + yspeed] == 4)
        {
            xspeed=-xspeed;
            yspeed=-yspeed;
        }
    }

    /**
     * Méthode permettant de faire démarrer le déplacement de la balle
     */
    public void start()
    {
        start = true;
    }
}
