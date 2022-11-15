package Graphics;

import GamePkg.Entity;
import GamePkg.Map;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class GamePanel extends JPanel {

    private final AnimationEngine animationEngine;
    private final TexturesImages texturesImages;
    private final Animations animationsSnoopy;
    private final Animations animationsBird;
    private final Animations animationsDriveBlock;
    private final Animations animationsTeleporter;


    private final HashMap<Integer, Animations> animationsHashMap = new HashMap<>();
    ArrayList<Entity> entities;


    public GamePanel(Map map){
        this.texturesImages = new TexturesImages();
        this.animationEngine = new AnimationEngine(20);
        this.animationEngine.start();

        this.setPreferredSize(new Dimension(640,320));
        this.entities = map.getEntities();

        this.animationsSnoopy = new Animations(4,4,"includes" + File.separator + "Snoopy" + File.separator);
        this.animationsBird = new Animations(4,3,"includes" + File.separator + "Bird" + File.separator);
        this.animationsDriveBlock = new Animations(4,4,"includes" + File.separator + "DriveBlock" + File.separator);
        this.animationsTeleporter = new Animations(1,6,"includes" + File.separator + "Teleporter" + File.separator);
        this.animationsHashMap.put(8, animationsSnoopy);
        this.animationsHashMap.put(9, animationsBird);
        this.animationsHashMap.put(6, animationsDriveBlock);
        this.animationsHashMap.put(5, animationsTeleporter);

        this.setVisible(true);


    }



    @Override
    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();
        super.paint(g);

        for (int i = 0; i < 10.; i++) {
            for (int j = 0; j < 20; j++) {
                g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
            }
        }

        for(Entity entity: entities) {
            if (entity.isVisible()) {
                if (entity.isAnimated()) {
                    if (entity.itsDirection.ordinal() == 4 && (entity.itsLastDirection.ordinal() != 4)) {
                        g2.drawImage(animationsHashMap.get(entity.getIdentifier()).getAnimation(entity.itsLastDirection.ordinal(), animationEngine.getStaticCycle()), entity.getY() * 32, entity.getX() * 32, this);
                    }else if(entity.itsDirection.ordinal() == 4 && (entity.itsLastDirection.ordinal() == 4)) {
                        g2.drawImage(texturesImages.getImageFromTAB(entity.getIdentifier()), entity.getX() * 32, entity.getY() * 32, this);
                    }else{
                        g2.drawImage(animationsHashMap.get(entity.getIdentifier()).getAnimation(entity.itsDirection.ordinal(), animationEngine.getCycleProgress()), entity.getY() * 32, entity.getX() * 32, this);
                    }
                }else{
                    g2.drawImage(texturesImages.getImageFromTAB(entity.getIdentifier()), entity.getY() * 32, entity.getX() * 32, this);
                }
            }
        }
    }
}
