package Graphics;
import GamePkg.Game;

import javax.swing.*;
import java.awt.*;


public class GameUI extends JLayeredPane implements Observateur {



    private final JFrame jFrame;
    private JPanel menuPanel;

    private GamePanel gamePanel;

    public GameUI(Game game){
        this.jFrame = new JFrame("Snoopy le jeu");
        this.gamePanel = new GamePanel(game);
        this.gamePanel.setVisible(true);

        this.gamePanel.actualise();
        setupJFrame();
        setupMenuPanel();
        this.add(gamePanel, 1);
    }

    private void setupJFrame(){
        this.jFrame.setMinimumSize(new Dimension(100,200));
        this.jFrame.setResizable(true);
        this.jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.jFrame.setLocationRelativeTo(null);

        this.jFrame.setContentPane(gamePanel);
        this.jFrame.pack();
        this.jFrame.setVisible(true);
    }

    private GridBagConstraints setGridBagConstriant(GridBagConstraints gbc, int gX, int gY, int gWidth, int gHeight, int weightX, int weightY){
        gbc.gridx = gX;
        gbc.gridy = gY;
        gbc.gridwidth = gWidth;
        gbc.gridheight = gHeight;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
        return gbc;
    }
    private void setupMenuPanel(){
        GridBagLayout gbl = new GridBagLayout();

        this.menuPanel = new JPanel(gbl);
        menuPanel.setSize(640,320);
        menuPanel.setPreferredSize(new Dimension(640,320));

        menuPanel.setBounds(0,0,640,320);
        menuPanel.setVisible(true);
        menuPanel.setBackground(new Color(0,0,0,5));


        JButton startBtn = new JButton("Jeu de base");
        JButton iaBtn = new JButton("Jeu IA");
        JButton loadBtn = new JButton("Charger une partie");
        JButton passBtn = new JButton("Mot de passe");
        JButton scoreBTN = new JButton("Scores");
        JButton leaveBtn = new JButton("Quitter");

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.ipady = 25;
        c.ipadx = 200;


        c = setGridBagConstriant(c,0,0,1,1,0,0);
        menuPanel.add(startBtn, c);

        c = setGridBagConstriant(c,0,1,1,1,0,0);
        menuPanel.add(iaBtn, c);

        c = setGridBagConstriant(c,0,2,1,1,0,0);
        menuPanel.add(loadBtn, c);


        c = setGridBagConstriant(c,0,3,1,1,0,0);
        menuPanel.add(passBtn, c);

        c = setGridBagConstriant(c,0,4,1,1,0,0);
        menuPanel.add(scoreBTN, c);

        c = setGridBagConstriant(c,0,5,1,1,0,0);
        menuPanel.add(leaveBtn, c);
        this.add(menuPanel, 1);
    }


    @Override
    public void actualise() {
        gamePanel.repaint();
    }

}
