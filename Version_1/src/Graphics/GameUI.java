package Graphics;
import GamePkg.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;



public class GameUI extends JPanel implements Observateur {
    private GamePanel gamePanel;
    private Map map;
    private Game game;
    private JPanel actionPanel;
    private JLabel jlTemps;
    private JFrame frame;

    public GameUI(Game game){
        this.game = game;
        this.map = game.getMap();
        this.frame = new JFrame("Snoopy le Jeu");
        this.setPreferredSize(new Dimension(640,420));
        this.gamePanel = new GamePanel(this.map);

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

    private GridBagConstraints setGridBagConstriant(GridBagConstraints gbc, int gX, int gY, int gWidth, int gHeight, int weightX, int weightY){
        gbc.gridx = gX;
        gbc.gridy = gY;
        gbc.gridwidth = gWidth;
        gbc.gridheight = gHeight;
        gbc.weightx = weightX;
        gbc.weighty = weightY;
        return gbc;
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

    @Override
    public void actualise() {
       gamePanel.repaint();
    }

    @Override
    public void actualiseTimer() {
        this.jlTemps.setText(Integer.toString(game.getRemaingSeconds()));
    }

    public void setKeyListener(KeyListener kl){
        this.frame.addKeyListener(kl);
    }

}
