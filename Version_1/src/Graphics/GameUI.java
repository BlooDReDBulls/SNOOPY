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
    public JLabel jlPass;
    private JLabel jlScore;
    private JPanel pausePanel;
    private JPanel savePanel;
    private JPanel endPanel;
    private JTextField saveText;
    private JButton jbOK;
    private JButton jbQuit;
    private JButton jbEnd = new JButton("Retourner au Menu");


    public GameUI(Game game){
        this.game = game;
        Map map = game.getMap();
        this.frame = new JFrame("Snoopy le Jeu");
        this.setPreferredSize(new Dimension(640,420));
        this.gamePanel = new GamePanel(map);
        this.setVisible(true);

//        this.gamePanel.setFocusable(true);
//        this.gamePanel.setRequestFocusEnabled(true);
        this.gamePanel.grabFocus();

        setupActionPanel();
        setupGamePanel();
        setupPausePanel();
        setupSaveMenu();
        setupFrame();
    }

    private void setupFrame(){
        this.frame.setMinimumSize(new Dimension(100, 100));
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setContentPane(this);
        this.frame.setVisible(true);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);

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
        gbc.ipady = 2;
        gbc.ipadx = 40;

        JLabel jlTempStr = new JLabel("Temps restant : ");
        JLabel jlScoreStr = new JLabel("Score : ");
        JLabel jldir = new JLabel("Directions : ");
        JLabel jldirKey = new JLabel("flèches directionnelles");

        JLabel jlpassword = new JLabel("Mot de passe :");
        JLabel jlechap = new JLabel("Menu : ECHAP");
        JLabel jlBreak = new JLabel("Casser un block : ");
        JLabel jlBreakKey = new JLabel("ESPACE");


        JLabel jlSave = new JLabel("Save : S");

        this.jlPass = new JLabel("test");
        this.jlTemps = new JLabel();
        this.jlScore = new JLabel("");
        this.jlTemps.setText("60");

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.actionPanel.add(jlTempStr,gbc);
        gbc.gridx = 1;
        this.actionPanel.add(jlTemps,gbc);
        gbc.gridx = 2;
        this.actionPanel.add(jlScoreStr,gbc);
        gbc.gridx = 3;
        this.actionPanel.add(jlScore,gbc);
        gbc.gridy = 1;
        gbc.gridx = 0;
        this.actionPanel.add(jlpassword, gbc);
        gbc.gridx = 1;
        this.actionPanel.add(jlPass, gbc);
        gbc.gridx = 2;
        this.actionPanel.add(jldir,gbc);
        gbc.gridx = 3;
        this.actionPanel.add(jldirKey,gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        this.actionPanel.add(jlechap,gbc);
        gbc.gridx = 1;
        this.actionPanel.add(jlSave,gbc);
        gbc.gridx = 2;
        this.actionPanel.add(jlBreak,gbc);
        gbc.gridx = 3;
        this.actionPanel.add(jlBreakKey,gbc);
    }

    private void setupGamePanel(){
        this.setLayout(new BorderLayout());
        gamePanel.setLayout(new BorderLayout());
        this.add(this.gamePanel, BorderLayout.CENTER);
        this.add(this.actionPanel, BorderLayout.SOUTH);
    }

    private void setupSaveMenu(){

        GridBagConstraints saveConstraints = new GridBagConstraints();

        JLabel jLabel = new JLabel("Nom de la sauvegarde");
        JLabel jLAnnuler = new JLabel("Annuler : S");
        jLabel.setForeground(Color.WHITE);
        jLAnnuler.setForeground(Color.WHITE);

        this.savePanel = new JPanel(new GridBagLayout());

        this.savePanel.setPreferredSize(new Dimension(240,120));
        this.savePanel.setBackground(new Color(0,0,0,155));

        this.saveText = new JTextField();
        this.jbOK = new JButton("Sauvegarder");

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
        saveConstraints.gridx = 0;
        this.savePanel.add(jLAnnuler, saveConstraints);
        saveConstraints.gridx = 1;
        this.savePanel.add(this.jbOK, saveConstraints);
    }

    private void setupPausePanel(){
        GridBagConstraints pauseConstraints  = new GridBagConstraints();

        JLabel pauseLabel = new JLabel("Pause");
        pauseLabel.setForeground(Color.WHITE);

        this.pausePanel = new JPanel(new GridBagLayout());


        this.pausePanel.setPreferredSize(new Dimension(240,120));
        this.pausePanel.setBackground(new Color(0,0,0,155));

         this.jbQuit = new JButton("Menu Principal");


        pauseConstraints.fill = GridBagConstraints.BOTH;
        pauseConstraints.ipady = 50;
        pauseConstraints.ipadx = 100;
        pauseConstraints.weightx = 0;
        pauseConstraints.weighty = 0;
        pauseConstraints.gridwidth = 1;
        pauseConstraints.gridheight = 1;

        pauseConstraints.gridx = 0;
        pauseConstraints.gridy = 0;
        this.pausePanel.add(pauseLabel, pauseConstraints);

        pauseConstraints.ipady = 10;
        pauseConstraints.gridy = 1;
        this.pausePanel.add(jbQuit, pauseConstraints);
    }

    private void setupEndPanel(boolean bool){
        this.endPanel = new JPanel(new GridBagLayout());
        JLabel endLabel = new JLabel();
        endLabel.setFont(new Font("Arial", Font.PLAIN, 28));

        this.savePanel.setPreferredSize(new Dimension(240,120));

        if(bool){
             endLabel.setText("Gagné !");
            this.endPanel.setBackground(new Color(0,155,75,155));
        }else {
            this.endPanel.setBackground(new Color(155,0,75,155));
            endLabel.setText("Perdu !");
        }

        GridBagConstraints endConstraints = new GridBagConstraints();
        endConstraints.ipady = 15;
        endConstraints.ipadx = 120;
        endConstraints.weightx = 0;
        endConstraints.weighty = 0;
        endConstraints.gridwidth = 1;
        endConstraints.gridheight = 1;
        endConstraints.gridx = 0;
        this.endPanel.add(endLabel);
        endConstraints.gridy = 1;
        this.endPanel.add(this.jbEnd, endConstraints);
    }

    public void showEndPanel(boolean bool){
        setupEndPanel(bool);
        showPanel(true, endPanel);
    }

    public void showSaveMenu(Boolean bool){
        showPanel(bool, savePanel);
    }

    public void showPauseMenu(Boolean bool){
        showPanel(bool, pausePanel);
    }

    private void showPanel(Boolean bool, JPanel savePanel) {
        if(bool){
            this.remove(gamePanel);
            this.add(savePanel);

        }else{
            this.remove(savePanel);
            this.add(gamePanel);
        }

        this.repaint();
        this.revalidate();
        this.frame.pack();
    }

    public JButton getJbOK() {
        return jbOK;
    }

    public JButton getJbQuit() {
        return jbQuit;
    }

    public JButton getJbEnd() {
        return jbEnd;
    }
    public String getSaveName(){
        return saveText.getText();
    }

    @Override
    public void actualise() {
        this.gamePanel.repaint();
        this.jlScore.setText(Integer.toString(game.getMap().getPlayer().itsScore));

    }

    @Override
    public void actualiseTimer() {
        this.jlTemps.setText(Integer.toString(game.getRemaingSeconds()));

    }
    public void setKeyListener(KeyListener kl){
        this.frame.addKeyListener(kl);
    }

    public void closeALl(){
        this.frame.dispose();
    }

}
