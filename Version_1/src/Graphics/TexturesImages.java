package Graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author lucien
 * Classe permetant de charger les images pour les animations
 */
public class TexturesImages {

    HashMap<Integer, BufferedImage> bufferedImageHashMap = new HashMap<>();

    /**
     * Constructeur de la classe TexturesImages
     */
    public TexturesImages() {

        for (int i = 0; i < 5; i++) {
            String name = "includes/" + i + ".png";
            try {
                BufferedImage bi = ImageIO.read(new File(name));
                bufferedImageHashMap.put(i, bi);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            BufferedImage bi = ImageIO.read(new File("includes/7.png"));
            bufferedImageHashMap.put(7, bi);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        System.out.println("Images Chargées ...");
    }


    /**
     * Getter sur une image
     * @param num index de l'image dans la hashmap
     */
    public BufferedImage getImageFromMap(int num){

        return this.bufferedImageHashMap.get(num);
    }

    /**
     * Getter sur une image
     * @param num index de l'image dans la hashmap
     */
    public BufferedImage getImageFromTAB(int num){

        return this.bufferedImageHashMap.get(num);
    }

}
