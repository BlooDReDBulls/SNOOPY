package Graphics;

import GamePkg.Game;

import javax.swing.*;
import java.awt.*;

public class GameUI extends JPanel implements Observateur {

    private final Game game;
    TexturesImages texturesImages;

    JFrame jFrame;

    public GameUI(Game game){

        jFrame = new JFrame("Snoopy le jeu");
        this.setPreferredSize(new Dimension(640,320));
        this.game = game;
        this.texturesImages = new TexturesImages();
        texturesImages.initImageIconHashMap();
        setupJFrame();
    }

    private void setupJFrame(){
        jFrame.setMinimumSize(new Dimension(100,200));
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(this);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    @Override
    public void actualise() {
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
