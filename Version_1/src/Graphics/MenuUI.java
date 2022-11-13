package Graphics;

import javax.swing.*;
import java.awt.*;

public class MenuUI extends JFrame {

    private JPanel menuPanel;

    private JPanel loadPanel;
    public JPanel UIPanel;
    private JButton startBtn;
    private JButton iaBtn;
    private JButton loadBtn;
    private JButton passBtn;
    private JButton scoreBTN;
    private JButton leaveBtn;

    private final JTextField loadText = new JTextField();

    private final JButton jbOK = new JButton("Charger");
    private final JButton jbAnnuler = new JButton("Annuler");


    public MenuUI() {
        this.UIPanel = new JPanel(new CardLayout());
        UIPanel.setPreferredSize(new Dimension(640, 320));

        setupMenuPanel();
        setupLoadMenu();
        System.out.println("LoadPanel is ok in ram");
        setupJFrame();
    }

    private void setupJFrame(){
        this.setTitle("Snoopy le Jeu");
        this.setMinimumSize(new Dimension(100, 100));
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(UIPanel);
        this.setBackground(Color.WHITE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    public void setupLoadMenu(){

        GridBagConstraints loadConstraints = new GridBagConstraints();

        JLabel jLabel = new JLabel("Nom de la sauvegarde");

        this.loadPanel = new JPanel(new GridBagLayout());
        this.loadPanel.setPreferredSize(new Dimension(240,120));
        this.loadPanel.setBackground(Color.WHITE);



        loadConstraints.ipady = 100;
        loadConstraints.ipadx = 20;
        loadConstraints.weightx = 0;
        loadConstraints.weighty = 0;
        loadConstraints.gridwidth = 1;
        loadConstraints.gridheight = 1;
        loadConstraints.gridx = 0;
        loadConstraints.gridy = 0;
        this.loadPanel.add(jLabel, loadConstraints);

        loadConstraints.fill = GridBagConstraints.HORIZONTAL;
        loadConstraints.ipadx = 200;
        loadConstraints.ipady = 10;
        loadConstraints.gridx = 1;
        this.loadPanel.add(loadText, loadConstraints);

        loadConstraints.ipadx = 10;
        loadConstraints.fill = GridBagConstraints.NONE;
        loadConstraints.gridx = 0;
        loadConstraints.gridy = 1;
        this.loadPanel.add(this.jbAnnuler, loadConstraints);

        loadConstraints.gridx = 1;
        this.loadPanel.add(this.jbOK, loadConstraints);

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
        c.weighty = 0;
        c.weightx = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 0;


        c.gridy = 0;
        menuPanel.add(startBtn, c);

        c.gridy = 1;
        menuPanel.add(iaBtn, c);

        c.gridy = 2;
        menuPanel.add(loadBtn, c);

        c.gridy = 3;
        menuPanel.add(passBtn, c);

        c.gridy = 4;
        menuPanel.add(scoreBTN, c);

        c.gridy = 5;
        menuPanel.add(leaveBtn, c);

//        Changer apparence du fond et des BTN
        this.menuPanel.setBackground(Color.WHITE);

        this.UIPanel.add(menuPanel);

    }

    public void loadPanel(){
        this.UIPanel.remove(menuPanel);
        this.UIPanel.add(loadPanel);
        this.pack();
    }

    public void goMenu(){
        this.loadText.setText("");
        this.UIPanel.remove(loadPanel);
        this.UIPanel.add(menuPanel);
        this.pack();
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

    public JButton getJbOK(){
        return jbOK;
    }

    public JButton getJbAnnuler(){
        return jbAnnuler;
    }

    public JTextField getLoadText(){
        return loadText;
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
