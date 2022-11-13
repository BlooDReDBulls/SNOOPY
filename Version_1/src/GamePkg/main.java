package GamePkg;
import Graphics.*;

import javax.swing.*;
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

        menuUI.getLoadBtn().addActionListener(e->{
            menuUI.loadPanel();

            menuUI.getJbOK().addActionListener(l->{

                try {
                    game = new Game();
                    String name  = menuUI.getLoadText().getText();
                    menuUI.dispose();
                    game.getMap().loadSave(name);


                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            });

            menuUI.getJbAnnuler().addActionListener(c -> {
                menuUI.goMenu();
                menuUI.pack();

            });

        });

        menuUI.getLeaveBtn().addActionListener(q -> {
            menuUI.dispose();
            System.exit(0);
        });



    }

}
