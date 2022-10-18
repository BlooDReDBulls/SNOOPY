package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import GamePkg.*;

public class GraphicFrame extends JFrame {
    private final GraphicPanel graphicPanel;

    public GraphicFrame(GraphicPanel graphicPanel){
        this.graphicPanel = graphicPanel;
        graphicPanel.initImageIconHashMap();
        initFrame();
        showUI();
    }


    public void initFrame(){
        this.setContentPane(graphicPanel);
    }

    private void showUI(){
       this.setName("Snoopy le jeu");
       this.setSize(400,400);
       this.setVisible(true);
       this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
