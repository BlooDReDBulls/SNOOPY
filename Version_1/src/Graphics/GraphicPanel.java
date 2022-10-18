package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import GamePkg.*;



public class GraphicPanel extends JPanel{

    HashMap<Integer, ImageIcon> imageIconHashMap = new HashMap<>();

    private final Game game;


    public GraphicPanel(Game game){
        this.game = game;
    }

    public void initImageIconHashMap(){
        for (int i = 0; i < 9; i++) {
            String name = "includes/" + i + ".png";
            imageIconHashMap.put(i, new ImageIcon(name));
        }
    }

    public void update(){
        repaint();
    }


    @Override
    public void paint(Graphics g) {

        game.displayMap();

        super.paint(g);

        for (int i = 0; i < 10.; i++) {
            for (int j = 0; j < 20; j++) {
                /*JPanel jp = new JPanel(new GridLayout());
                jp.add(new JLabel(imageIconHashMap.get(game.getMap()[i][j])));
                this.add(jp);*/

                g.drawImage(imageIconHashMap.get(game.getMap()[i][j]).getImage(), (32 * i), (32 * j), this);
            }
        }

    }
}
