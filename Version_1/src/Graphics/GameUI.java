package Graphics;
import GamePkg.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;



public class GameUI extends JPanel implements Observateur {
    private final GamePanel gamePanel;
    private final Game game;

    private final JFrame frame;
    private JPanel actionPanel;
    private JLabel jlTemps;

    private JLabel jlScore;

    private JPanel savePanel;
    private JTextField saveText;

    private JButton jbOK;
    private JButton jbAnnuler;


    public GameUI(Game game){
        this.game = game;
        Map map = game.getMap();
        this.frame = new JFrame("Snoopy le Jeu");
        this.setPreferredSize(new Dimension(640,420));
        this.gamePanel = new GamePanel(map);

        setupActionPanel();
        setupPanels();
        this.setVisible(true);
        setupSaveMenu();

        setupFrame();
    }

    private void setupFrame(){

        this.frame.setMinimumSize(new Dimension(100, 100));
        this.frame.setResizable(true);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        this.frame.setContentPane(this);
        this.frame.setVisible(true);

        this.frame.pack();

    }

    private void setupActionPanel(){
        this.actionPanel = new JPanel();
        this.actionPanel.setPreferredSize(new Dimension(640,60));
        this.actionPanel.setLayout(new GridBagLayout());



        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.ipady = 5;
        gbc.ipadx = 10;

        JLabel jlTempStr = new JLabel("Temps restant : ");
        JLabel jlScoreStr = new JLabel("Score : ");

        this.jlTemps = new JLabel();
        this.jlTemps.setText("60");

        // Changer la Police ?
        this.jlTemps.setFont(new Font("Arial", Font.PLAIN, 18));


        gbc.gridx = 0;
        gbc.gridy = 0;
        this.actionPanel.add(jlTempStr,gbc);

        this.jlScore = new JLabel();
        this.jlScore.setText("0");

        gbc.ipadx = 0;
        gbc.gridx = 1;
        this.actionPanel.add(jlTemps,gbc);

        gbc.ipadx = 10;
        gbc.gridx = 2;
        this.actionPanel.add(jlScoreStr,gbc);

        gbc.gridx = 3;
        this.actionPanel.add(jlScore,gbc);


        JLabel jldir = new JLabel("Directions : flèches directionnelles");
        gbc.gridx = 0;
        gbc.gridy = 1;
        this.actionPanel.add(jldir,gbc);


        JLabel jlechap =new JLabel("Menu : ECHAP");
        gbc.gridx = 1;
        this.actionPanel.add(jlechap,gbc);

        JLabel jlSave =new JLabel("Save : S");

        gbc.gridx = 2;
        this.actionPanel.add(jlSave,gbc);

        JLabel jlBreak =new JLabel("Casser un block : ESPACE");

        gbc.gridx = 3;
        this.actionPanel.add(jlBreak,gbc);

    }

    private void setupPanels(){
        this.setLayout(new BorderLayout());
        gamePanel.setLayout(new BorderLayout());
        this.add(this.gamePanel, BorderLayout.CENTER);
        this.add(actionPanel, BorderLayout.SOUTH);
    }

    private void setupSaveMenu(){

        GridBagConstraints saveConstraints = new GridBagConstraints();

        JLabel jLabel = new JLabel("Nom de la sauvegarde");
        jLabel.setForeground(Color.WHITE);

        this.savePanel = new JPanel(new GridBagLayout());

        this.savePanel.setPreferredSize(new Dimension(240,120));
        this.savePanel.setBackground(new Color(0,0,0,155));

        this.saveText = new JTextField();
        this.jbOK = new JButton("Sauvegarder");
        this.jbAnnuler = new JButton("Annuler");



        saveConstraints.ipady = 100;
        saveConstraints.ipadx = 20;
        saveConstraints.weightx = 0;
        saveConstraints.weighty = 0;
        saveConstraints.gridwidth = 1;
        saveConstraints.gridheight = 1;
        saveConstraints.gridx = 0;
        saveConstraints.gridy = 0;
        this.savePanel.add(jLabel, saveConstraints);

        saveConstraints.fill = GridBagConstraints.HORIZONTAL;
        saveConstraints.ipadx = 200;
        saveConstraints.ipady = 10;
        saveConstraints.gridx = 1;
        this.savePanel.add(saveText, saveConstraints);

        saveConstraints.ipadx = 10;
        saveConstraints.fill = GridBagConstraints.NONE;
        saveConstraints.gridy = 1;
        saveConstraints.gridx = 1;
        this.savePanel.add(this.jbOK, saveConstraints);

    }

    public void showSaveMenu(Boolean bool){
        if(bool){
            this.remove(gamePanel);
            this.add(savePanel);
            actualise();
            this.repaint();
            this.revalidate();
            this.frame.pack();

        }else{
            this.remove(savePanel);
            this.add(gamePanel);
            actualise();
            this.repaint();
            this.revalidate();
            this.frame.pack();
        }
    }

    public JButton getJbOK() {
        return jbOK;
    }


    public String getSaveName(){
        return saveText.getText();
    }

    @Override
    public void actualise() {
        this.gamePanel.repaint();
    }

    @Override
    public void actualiseTimer() {
        this.jlTemps.setText(Integer.toString(game.getRemaingSeconds()));
        this.jlScore.setText(Integer.toString(game.getScore()));
    }


    public void setKeyListener(KeyListener kl){
        this.frame.addKeyListener(kl);
    }

}
