package Graphics;
import GamePkg.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class GameUI extends JPanel implements Observateur {
    private final JFrame jFrame;
    private JPanel menuPanel;
    private JPanel actionPanel;
    private JLabel jlTemps;
    private GamePanel gamePanel;
    private Map map;
    private Game game;



    public GameUI(Game game){
        this.game = game;
        this.map = game.getMap();
        this.jFrame = new JFrame("Snoopy le jeu");
        this.gamePanel = new GamePanel(this.map);
        this.setPreferredSize(new Dimension(640,420));

        setupMenuPanel();
        setupActionPanel();
        setupPanels();
        this.setVisible(true);

        setupJFrame();

    }

    private void setupJFrame(){
        this.jFrame.setMinimumSize(new Dimension(100,100));
        this.jFrame.setResizable(false);
        this.jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.jFrame.setLocationRelativeTo(null);
        this.jFrame.setContentPane(this);
        this.jFrame.setVisible(true);
        this.jFrame.pack();
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
    }

    private void setupActionPanel(){
        this.actionPanel = new JPanel();
        this.actionPanel.setPreferredSize(new Dimension(640,60));
        this.actionPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 0;
        gbc.ipadx = 0;




        this.jlTemps = new JLabel();
        this.jlTemps.setText("60");

        gbc = setGridBagConstriant(gbc,1,0,1,1,1,1);
        this.actionPanel.add(jlTemps,gbc);

        JLabel jldir = new JLabel("Directions : flèches directionnelles");
//        jldir.setBounds(0,0,70,70);
        gbc = setGridBagConstriant(gbc,0,1,1,1,1,1);
        this.actionPanel.add(jldir,gbc);


        JLabel jlechap =new JLabel("Menu : ECHAP");
        gbc = setGridBagConstriant(gbc,1,1,1,1,1,1);
        this.actionPanel.add(jlechap,gbc);

        JLabel jlSave =new JLabel("Save : S");
        gbc = setGridBagConstriant(gbc,2,1,1,1,1,1);
        this.actionPanel.add(jlSave,gbc);

        JLabel jlBreak =new JLabel("Casser un block : ESPACE");
        gbc = setGridBagConstriant(gbc,4,1,1,1,1,1);
        this.actionPanel.add(jlBreak,gbc);



    }

    private void setupPanels(){

        this.setLayout(new BorderLayout());
        gamePanel.setLayout(new BorderLayout());
        this.add(this.gamePanel, BorderLayout.CENTER);
        this.add(actionPanel, BorderLayout.SOUTH);
    }

    private void setupKeyListeners(){
//        jFrame.addKeyListener();
    }


    @Override
    public void actualise() {
       gamePanel.repaint();
    }

    @Override
    public void actualiseTimer() {
        this.jlTemps.setText(Integer.toString(game.getRemaingSeconds()));
    }

    public void setKeyListener(KeyListener kl){
        this.jFrame.addKeyListener(kl);
    }

}
