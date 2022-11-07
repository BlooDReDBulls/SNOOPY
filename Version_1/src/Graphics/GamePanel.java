package Graphics;

import GamePkg.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Observateur{

    private Map map;
    private final AnimationEngine animationEngine;
    private final TexturesImages texturesImages;
    private final Animations animationsSnoopy;
    private final Animations animationsBird;
    private final Animations animationsSlider;

    private final ArrayList<Animations> animationsArrayList = new ArrayList();
    ArrayList<Entity> entities;


    private JFrame jFrame;


    public GamePanel(Map map){
        this.map = map;
        this.entities = map.getEntities();
        this.setPreferredSize(new Dimension(640,320));

        this.jFrame = new JFrame("Temporary Frame SNOOPY ");

        this.animationEngine = new AnimationEngine(60);
        this.animationEngine.start();

        this.texturesImages = new TexturesImages();
        this.animationsSnoopy = new Animations(4,4,"includes" + File.separator + "Snoopy" + File.separator);
        this.animationsBird = new Animations(4,3,"includes" + File.separator + "Bird" + File.separator);
        this.animationsSlider = new Animations(4,4,"includes" + File.separator + "Slider" + File.separator);

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

        repaint();
    }

    @Override
    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        super.paint(g);

//        g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);

//        for (Entity entity: entities) {
//            entity.
//        }


        for (int i = 0; i < 10.; i++) {
            for (int j = 0; j < 20; j++) {
                g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);

                switch (map.getMap()[i][j]) {
                    case 3 -> {
                        g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
                        g2.drawImage(texturesImages.getImageFromMap(map.getMap()[i][j]), (32 * j), (32 * i), this);
                    }
                    case 6 -> {
                        g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
                        g2.drawImage(animationsSlider.getAnimation(1, animationEngine.getCycleProgress()), (32 * j), (32 * i), this);
                    }
                    case 7 -> {
                        g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
                        g2.drawImage(texturesImages.getImageFromMap(map.getMap()[i][j]), (32 * j), (32 * i), this);
                    }
                    case 8 -> {
                        g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
                        g2.drawImage(animationsSnoopy.getAnimation(1, animationEngine.getCycleProgress()), (32 * j), (32 * i), this);
                    }
                    case 9 -> {
                        //Faire les directions avec Lucas
                        g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
                        g2.drawImage(animationsBird.getAnimation(1, animationEngine.getCycleProgress()), (32 * j), (32 * i), this);
                    }
                    default ->
                            g2.drawImage(texturesImages.getImageFromMap(map.getMap()[i][j]), (32 * j), (32 * i), this);
                }
            }
        }
    }

    public void setKeyListener(KeyListener kl){
        this.jFrame.addKeyListener(kl);
    }

}


