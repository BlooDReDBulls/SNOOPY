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
        this.setMinimumSize(new Dimension(20,40));
        this.setResizable(false);
        //
        this.pack();
        this.setVisible(true);

       this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       this.setLocationRelativeTo(null);
    }
}
