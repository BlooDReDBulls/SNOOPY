package Graphics;

import javax.swing.*;
import java.awt.*;

public class GraphicalUI extends JFrame {

    int[][] map = new int[10][10];

    JPanel mainPanel = new JPanel(new GridLayout(50,50));


    public void initFrame(){
        Dimension min = new Dimension();

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
