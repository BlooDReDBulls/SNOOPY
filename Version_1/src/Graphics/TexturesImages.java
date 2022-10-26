package Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class TexturesImages {

    HashMap<Integer, BufferedImage> bufferedImageHashMap = new HashMap<>();

    public TexturesImages() {

        System.out.println("Chargement des images ...");
        for (int i = 0; i < 6; i++) {

            String name = "includes/" + i + ".png";
            //System.out.println(name);
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

    }



    public BufferedImage getImageFromMap(int num){

        return this.bufferedImageHashMap.get(num);
    }

    public BufferedImage getImageFromTAB(int num){

        return this.bufferedImageHashMap.get(num);
    }

}
