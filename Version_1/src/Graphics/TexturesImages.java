package Graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TexturesImages {

    HashMap<Integer, ImageIcon> imageIconHashMap = new HashMap<>();

    HashMap<Integer, ArrayList<ImageIcon>> birdSpritesImageIconHashMap = new HashMap<>();

    ArrayList<ImageIcon> arrayListTemp = new ArrayList<>();


    public void initImageIconHashMap(){


        System.out.println("Chargement des images ...");
        for (int i = 0; i < 9; i++) {
            String name = "includes/" + i + ".png";
            //System.out.println(name);
            imageIconHashMap.put(i, new ImageIcon(name));
        }

//        Chargement des sprites de bird left
        System.out.println("Chargement des sprites ...");
        for (int i = 0; i < 2; i++){

            for (int j = 0; j < 3; j++) {
                String nameSprite = "includes/9." + i + "." + j + ".png";
                //System.out.println(nameSprite);
                arrayListTemp.add(new ImageIcon(nameSprite));
                System.out.println(arrayListTemp.get(j).getImage());
            }
            birdSpritesImageIconHashMap.put(i,arrayListTemp);
        }

//        System.out.println("Affichage des sprites...");
//        for (int i = 0; i < 2; i++) {
//            for (int j = 0; j < 3; j++) {
//                System.out.println(birdSpritesImageIconHashMap.get(i).get(j).getImage());
//            }
//        }

    }

    public Image getImageFromMap(int num){

        if(num == 9){
            return birdSpritesImageIconHashMap.get(1).get(1).getImage();
        }
        return this.imageIconHashMap.get(num).getImage();
    }

    public Image getImageFromTAB(int num){
        return this.imageIconHashMap.get(num).getImage();
    }

}
