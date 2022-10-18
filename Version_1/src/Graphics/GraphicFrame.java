package Graphics;

import javax.swing.*;

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
       this.setSize(320,640);
       this.setVisible(true);
       this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
