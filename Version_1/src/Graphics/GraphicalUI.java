package Graphics;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

public class GraphicalUI extends JFrame {

    //int[][] map = new int[10][10];
    // ImageIcon player = new ImageIcon("includes/Sprite_frontview_64.png");
    
    HashMap<Integer, ImageIcon> imageIconHashMap = new HashMap<>();
    
    public void initImageIconHashMap(){
        for (int i = 0; i < 9; i++) {
            String name = "includes/" + i + ".png";
            imageIconHashMap.put(i, new ImageIcon(name));
        }
    }


    public void initFrame(){

        GridLayout gl = new GridLayout(10, 20);
        JPanel mainPanel = new JPanel(gl);

        initImageIconHashMap();

        gl.setHgap(0);
        gl.setVgap(0);

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                JPanel jp = new JPanel(new GridLayout());
                jp.add(new JLabel(imageIconHashMap.get(random.nextInt(10))));
                mainPanel.add(jp);
            }
        }


        this.setContentPane(mainPanel);
    }







    public void showUI(){
       this.setName("Snoopy le jeu");
       this.setSize(600,600);
       initFrame();
       this.setVisible(true);
       this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
