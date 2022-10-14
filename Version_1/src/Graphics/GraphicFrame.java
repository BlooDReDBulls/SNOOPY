package Graphics;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

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
