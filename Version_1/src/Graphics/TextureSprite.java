package Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TextureSprite {

    ArrayList<BufferedImage> birdSpriteRight = new ArrayList<>();
    ArrayList<BufferedImage> birdSpriteLeft = new ArrayList<>();

    ArrayList<BufferedImage> sliderSpriteUP = new ArrayList<>();
    ArrayList<BufferedImage> sliderSpriteDown = new ArrayList<>();
    ArrayList<BufferedImage> sliderSpriteLeft = new ArrayList<>();
    ArrayList<BufferedImage> sliderSpriteRight = new ArrayList<>();

    ArrayList<BufferedImage> snoopySpriteDown = new ArrayList<>();
    ArrayList<BufferedImage> snoopySpriteUp = new ArrayList<>();
    ArrayList<BufferedImage> snoopySpriteLeft = new ArrayList<>();
    ArrayList<BufferedImage> snoopySpriteRight = new ArrayList<>();


    public TextureSprite(){

        // Loading Bird right sprites
        System.out.println("Buffer Right");
        for (int i = 0; i < 3; i++) {
            String name = "includes/Bird/Right/" + i + ".png";
            try {
                BufferedImage bi = ImageIO.read(new File(name));
                System.out.println(bi);
                birdSpriteRight.add(bi);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // loading Bird left sprites
        System.out.println("Buffer Left");
        for (int i = 0; i < 3; i++) {
            String name = "includes/Bird/Left/" + i + ".png";
            try {
                BufferedImage bi = ImageIO.read(new File(name));
                System.out.println(bi);
                birdSpriteLeft.add(bi);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < 4; i++) {
            String name = "includes/Slider/Up/" + i + ".png";
            try {
                BufferedImage bi = ImageIO.read(new File(name));
                System.out.println(bi);
                sliderSpriteUP.add(bi);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < 4; i++) {
            String name = "includes/Slider/Down/" + i + ".png";
            try {
                BufferedImage bi = ImageIO.read(new File(name));
                System.out.println(bi);
                sliderSpriteDown.add(bi);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < 4; i++) {
            String name = "includes/Slider/Left/" + i + ".png";
            try {
                BufferedImage bi = ImageIO.read(new File(name));
                System.out.println(bi);
                sliderSpriteLeft.add(bi);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        for (int i = 0; i < 4; i++) {
            String name = "includes/Slider/Right/" + i + ".png";
            try {
                BufferedImage bi = ImageIO.read(new File(name));
                System.out.println(bi);
                sliderSpriteRight.add(bi);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        loadSprite("includes/Snoopy/Down/", 4, snoopySpriteDown);
        loadSprite("includes/Snoopy/Right/", 4, snoopySpriteRight);
        loadSprite("includes/Snoopy/Up/", 4, snoopySpriteUp);
        loadSprite("includes/Snoopy/Left/", 4, snoopySpriteLeft);
    }

    private void loadSprite(String path, int numberOfFrame, ArrayList<BufferedImage> arrayList){
        for (int i = 0; i < numberOfFrame; i++) {
            String name = path + i + ".png";
            BufferedImage bi = null;

            try {
                bi = ImageIO.read(new File(name));
                System.out.println(bi);
                arrayList.add(bi);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    Changer la méthode pour s'adapter à l'interface Entity
    public BufferedImage getSprite(int entityNumber, int direction, double frame, int numberOfFrame){

        int number = (int) (numberOfFrame*frame);

        switch (entityNumber){
            case 6:
                switch (direction){
                    case 0:
                        return sliderSpriteUP.get(number);
                    case 1:
                        return sliderSpriteLeft.get(number);
                    case 2:
                        return sliderSpriteDown.get(number);
                    case 3:
                        return sliderSpriteRight.get(number);
                }
            case 8:
                switch (direction){
                    case 0:
                        return snoopySpriteUp.get(number);
                    case 1:
                        return snoopySpriteLeft.get(number);
                    case 2:
                        return snoopySpriteDown.get(number);
                    case 3:
                        return snoopySpriteRight.get(number);
                }
            case 9:
                switch (direction){
                    case 0:
                        return birdSpriteLeft.get(number);
                    case 1:
                        return birdSpriteRight.get(number);
                }
        }


        return birdSpriteRight.get(0);
    }


}
