package Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Animations {

    private final int nDirection;
    private final int nFrame;

    private final String path;

    private HashMap<Integer, ArrayList<BufferedImage>> animHashMap;

    public Animations(int nDirection, int nFrame, String path){
        this.nDirection = nDirection;
        this.nFrame = nFrame;
        this.path = path;
        this.animHashMap = new HashMap<>();
        loadAnim();
    }

    private void loadAnim(){

        for (int i = 0; i < nDirection; i++) {
            ArrayList<BufferedImage> animArray = new ArrayList<>();
            for (int j = 0; j < nFrame; j++) {
                String name = path + i + "/" + j + ".png";
                System.out.println("path : " + name);
                BufferedImage bi;
                try {
                    bi = ImageIO.read(new File(name));
//                    System.out.println(bi);
                    animArray.add(bi);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            animHashMap.put(i,animArray);
        }

    }

    public BufferedImage getAnimation(int direction, double cycleProgress){

        int index = (int) (cycleProgress * nFrame);
        return animHashMap.get(direction).get(index);
    }
}
