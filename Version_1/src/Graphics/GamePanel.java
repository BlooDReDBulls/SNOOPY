package Graphics;

import GamePkg.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class GamePanel extends JPanel implements Observateur{




    private JFrame jFrame;


    public GamePanel(Map map){
//        this.map = map;
//        this.entities = map.getEntities();
//        this.setPreferredSize(new Dimension(640,320));

        this.jFrame = new JFrame("Temporary Frame SNOOPY ");



        setupJFrame();


    }

    private void setupJFrame(){
        this.jFrame.setMinimumSize(new Dimension(100,200));
        this.jFrame.setResizable(false);
        this.jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.jFrame.setLocationRelativeTo(null);

        this.jFrame.setContentPane(this);
        this.jFrame.pack();
        this.jFrame.setVisible(true);
    }


    @Override
    public void actualise() {
        System.out.println("hello");
    }
}


