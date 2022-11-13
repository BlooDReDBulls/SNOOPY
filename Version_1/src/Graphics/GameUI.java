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


    public GameUI(Game game){
        this.game = game;
        Map map = game.getMap();
        this.frame = new JFrame("Snoopy le Jeu");
        this.setPreferredSize(new Dimension(640,420));
        this.gamePanel = new GamePanel(map);

        setupActionPanel();
        setupPanels();
        this.setVisible(true);

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
