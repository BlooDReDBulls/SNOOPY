package Graphics;

import GamePkg.*;
import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel{



    private final Game game;
    private final TexturesImages texturesImages;


    public GraphicPanel(Game game){
        this.game = game;
        this.setPreferredSize(new Dimension(640,320));
        this.texturesImages = new TexturesImages();
        texturesImages.initImageIconHashMap();
    }



    public void update(){
        repaint();
    }


    @Override
    public void paint(Graphics g) {

        super.paint(g);
        for (int i = 0; i < 10.; i++) {
            for (int j = 0; j < 20; j++) {

                if(game.getMap()[i][j] == 6 || game.getMap()[i][j] == 7 || game.getMap()[i][j] == 8 || game.getMap()[i][j] == 9){
//                    g.drawImage(imageIconHashMap.get(0).getImage(), (32 * j), (32 * i), this);
                    g.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
                }
                g.drawImage(texturesImages.getImageFromMap(game.getMap()[i][j]), (32 * j), (32 * i), this);
            }
        }
    }
}
