package Graphics;

import GamePkg.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class GameUI extends JPanel implements Observateur {

    private final Game game;

    private final Animations animations;
    private final TexturesImages texturesImages;
    private final TextureSprite textureSprite;

    private final JFrame jFrame;

    public GameUI(Game game){
        animations = new Animations(60);
        animations.start();

        this.textureSprite = new TextureSprite();
        this.texturesImages = new TexturesImages();

        jFrame = new JFrame("Snoopy le jeu");
        this.setPreferredSize(new Dimension(640,320));
        this.game = game;
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

        Graphics2D g2 = (Graphics2D) g.create();
        super.paint(g);

        for (int i = 0; i < 10.; i++) {
            for (int j = 0; j < 20; j++) {

                switch (game.getMap()[i][j]) {
                    case 3 -> {
                        g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
                        g2.drawImage(texturesImages.getImageFromMap(game.getMap()[i][j]), (32 * j), (32 * i), this);
                    }
                    case 6 -> {
                        g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
                        g2.drawImage(textureSprite.getSprite(6, 0, animations.getCycleProgress(), 4), (32 * j), (32 * i), this);
                    }
                    case 7 -> {
                        g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
                        g2.drawImage(texturesImages.getImageFromMap(game.getMap()[i][j]), (32 * j), (32 * i), this);
                    }
                    case 8 -> {
                        g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
                        g2.drawImage(textureSprite.getSprite(8, 3, animations.getCycleProgress(), 4), (32 * j), (32 * i), this);
                    }
                    case 9 -> {
                        //Faire les directions avec Lucas
                        g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
                        g2.drawImage(textureSprite.getSprite(9, 1, animations.getCycleProgress(), 3), (32 * j), (32 * i), this);
                    }
                    default ->
                            g2.drawImage(texturesImages.getImageFromMap(game.getMap()[i][j]), (32 * j), (32 * i), this);
                }

//                if(game.getMap()[i][j] == 3 || game.getMap()[i][j] == 6 || game.getMap()[i][j] == 7 || game.getMap()[i][j] == 8 || game.getMap()[i][j] == 9){
//                    g2.drawImage(texturesImages.getImageFromTAB(0), (32 * j), (32 * i), this);
//
//                    g2.drawImage(textureSprite.getSprite(0,animations.getCycleProgress()), (32 * j), (32 * i), this);
//                }
//                g2.drawImage(texturesImages.getImageFromMap(game.getMap()[i][j]), (32 * j), (32 * i), this);
            }
        }
    }

    public void setKeyListener(KeyListener kl){
        jFrame.addKeyListener(kl);
    }


}
