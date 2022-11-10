package GamePkg;
import Graphics.*;

import java.io.IOException;

public class main {

    private static Game game;

//     this.gameUI.setKeyListener(keyListener);

    public static void main(String[] args) {

        MenuUI menuUI = new MenuUI();
        menuUI.setVisible(true);

        menuUI.getStartBtn().addActionListener(e->{

            try {
                menuUI.dispose();
                game = new Game();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

    }

}
