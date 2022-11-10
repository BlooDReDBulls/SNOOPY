package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;

public class MenuUI extends JFrame {

    private JPanel menuPanel;
    private JButton startBtn;
    private JButton iaBtn;
    private JButton loadBtn;
    private JButton passBtn;
    private JButton scoreBTN;
    private JButton leaveBtn;

    public MenuUI() {
        setupMenuPanel();
        setupJFrame();
    }

    private void setupJFrame(){
        this.setTitle("Snoopy le Jeu");
        this.setMinimumSize(new Dimension(100, 100));
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(menuPanel);
        this.pack();
        this.setVisible(true);
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

        startBtn = new JButton("Jeu de base");
        iaBtn = new JButton("Jeu IA");
        loadBtn = new JButton("Charger une partie");
        passBtn = new JButton("Mot de passe");
        scoreBTN = new JButton("Scores");
        leaveBtn = new JButton("Quitter");

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


    public void setKeyListener(KeyListener kl){
        this.addKeyListener(kl);
    }

    public JButton getStartBtn() {
        return startBtn;
    }

    public JButton getIaBtn() {
        return iaBtn;
    }

    public JButton getLoadBtn() {
        return loadBtn;
    }

    public JButton getPassBtn() {
        return passBtn;
    }

    public JButton getScoreBTN() {
        return scoreBTN;
    }

    public JButton getLeaveBtn() {
        return leaveBtn;
    }
}
