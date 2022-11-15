package Graphics;

import GamePkg.Direction;
import GamePkg.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author lucien
 * Classe permetant de créer les animations
 */
public class Animations {

    private final int nDirection;
    private final int nFrame;

    private final String path;

    private HashMap<Integer, ArrayList<BufferedImage>> animHashMap;

    /**
     * Constructeur de la classe Animations
     * @param nDirection indique la direction de l'animation
     * @param nFrame indique le nombre de sprite
     * @param path le chemin vers les différents sprite
     */
    public Animations(int nDirection, int nFrame, String path){
        this.nDirection = nDirection;
        this.nFrame = nFrame;
        this.path = path;
        this.animHashMap = new HashMap<>();
        loadAnim();
    }

    /**
     * Méthode pour charger les animations
     */
    private void loadAnim(){

        for (int i = 0; i < nDirection; i++) {
            ArrayList<BufferedImage> animArray = new ArrayList<>();
            for (int j = 0; j < nFrame; j++) {
                String name = path + i + File.separator + j + ".png";
//                System.out.println("path : " + name);
                BufferedImage bi;
                try {
                    bi = ImageIO.read(new File(name));
                    animArray.add(bi);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            animHashMap.put(i,animArray);
        }
    }

    /**
     * Getter sur une animation
     * @param direction indique la direction de l'animation
     * @param cycleProgress indique le progrès du cycle d'animation
     */
    public BufferedImage getAnimation(int direction, double cycleProgress){
        int index = (int) (cycleProgress * nFrame);
        return animHashMap.get(direction).get(index);
    }
}
