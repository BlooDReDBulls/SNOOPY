package Graphics;

import javax.swing.*;
import java.awt.*;

/**
 * @author lucien
 * Classe permetant de generer des menus
 * @see JFrame
 */
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

    private final JLabel jLabel = new JLabel();

    private final JTextField loadText = new JTextField();

    private JButton jbOK;
    private JButton jbAnnuler;

    /**
     * Constructeur de la classe MenuUI
     */
    public MenuUI() {
        this.UIPanel = new JPanel(new CardLayout());
        UIPanel.setPreferredSize(new Dimension(640, 320));
        setupMenuPanel();
//        setupLoadMenu();
        setupJFrame();
    }

    /**
     * Méthode permettant de créer la fenêtre du jeu
     */
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

    /**
     * Méthode permettant de créer la fenêtre de menu de chargement d'une map
     * @param mdpPanel définit si le joueur peut insérer un mot de passe pour accèder à une map
     */
    public void setupLoadMenu(Boolean mdpPanel){

        GridBagConstraints loadConstraints = new GridBagConstraints();

        if(mdpPanel){
            this.jLabel.setText("Mot de Passe ");
        }else{
            this.jLabel.setText("Nom de la sauvegarde ");
        }

        this.jbOK = new JButton("Charger");
        this.jbAnnuler = new JButton("Annuler");
        this.loadPanel = new JPanel(new GridBagLayout());
        this.loadPanel.setPreferredSize(new Dimension(240,120));
        this.loadPanel.setBackground(Color.WHITE);


        loadConstraints.ipady = 10;
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

    /**
     * Méthode permettant de créer la fenêtre de menu du jeu
     */
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

    /**
     * Méthode permettant de passer du menu du jeu au menu de chargement d'une map avec le mot de passe
     */
    public void initPassPanel(){
        setupLoadMenu(true);
        this.UIPanel.remove(menuPanel);
        this.UIPanel.add(loadPanel);
        this.revalidate();
        this.pack();
    }

    /**
     * Méthode permettant de passer du menu du jeu au menu de chargement d'une map sans le mot de passe
     */
    public void loadPanel(){
        setupLoadMenu(false);
        this.UIPanel.remove(menuPanel);
        this.UIPanel.add(loadPanel);
        this.revalidate();
        this.pack();
    }

    /**
     * Méthode permettant de passer du menu de chargement d'une map au menu du jeu
     */
    public void goMenu(){
        this.loadText.setText("");
        this.UIPanel.remove(loadPanel);
        this.UIPanel.add(menuPanel);
        this.pack();
    }

    /**
     * Getter sur startBtn
     */
    public JButton getStartBtn() {
        return startBtn;
    }

    /**
     * Getter sur iaBtn
     */
    public JButton getIaBtn() {
        return iaBtn;
    }

    /**
     * Getter sur laodBtn
     */
    public JButton getLoadBtn() {
        return loadBtn;
    }

    /**
     * Getter sur jbOK
     */
    public JButton getJbOK(){
        return jbOK;
    }

    /**
     * Getter sur jbAnnuler
     */
    public JButton getJbAnnuler(){
        return jbAnnuler;
    }

    /**
     * Getter sur loadText
     */
    public JTextField getLoadText(){
        return loadText;
    }

    /**
     * Getter sur passBtn
     */
    public JButton getPassBtn() {
        return passBtn;
    }

    /**
     * Getter sur scoreBTN
     */
    public JButton getScoreBTN() {
        return scoreBTN;
    }

    /**
     * Getter sur leaveBtn
     */
    public JButton getLeaveBtn() {
        return leaveBtn;
    }
}
