package Graphics;

import javax.swing.*;
import java.awt.*;

public class GraphicFrame extends JFrame {


    private final GraphicPanel graphicPanel;

    public GraphicFrame(GraphicPanel graphicPanel){
        this.graphicPanel = graphicPanel;
        graphicPanel.initImageIconHashMap();
        initFrame();
        showUI();
    }


    public void initFrame(){
        this.setLayout(new BorderLayout());
        this.setContentPane(graphicPanel);
    }

    private void showUI(){
       this.setName("Snoopy le jeu");
       // set minimum size of the frame
        this.setMinimumSize(new Dimension(100,200));
        this.setResizable(false);
        // Adjuste the frame size with the content of it
        this.pack();
        this.setVisible(true);

       this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       this.setLocationRelativeTo(null);
    }
}
