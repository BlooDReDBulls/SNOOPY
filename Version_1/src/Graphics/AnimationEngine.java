package Graphics;


import GamePkg.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author lucien
 * Classe permetant de gerer les animations
 */
public class AnimationEngine {

    private Timer timer;
    private final int fps;
    private long cycleStartTime;
    private double cycleProgress;

    private final TimerHandler timerHandler;

    /**
     * Constructeur de la classe AnimationEngine
     * @param fps l'inctance du jeu
     * @see Game
     */
    public AnimationEngine(int fps){
        this.fps = fps;
        timerHandler = new TimerHandler();

    }

    /**
     * Méthode pour reset le cycle d'animation
     */
    protected void clean() {
        cycleProgress = 0;
        cycleStartTime = 0;
    }

    /**
     * Méthode pour arreter le timer d'animation
     */
    public void stop() {
        if (timer != null) {
            timer.stop();
        }
        clean();
    }

    /**
     * Méthode pour lancer le timer d'animation
     */
    public void start() {
        stop();
        timer = new Timer(1000 / fps, timerHandler);
        timer.start();
    }

    /**
     * Timer permettant de faire l'animation
     * @see java.util.EventListener
     */
    protected class TimerHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cycleStartTime == 0) {
                cycleStartTime = System.currentTimeMillis();
            }
            long diff = (System.currentTimeMillis() - cycleStartTime) % 1000;
            cycleProgress = diff / 1000.0;
        }
    }

    /**
     * Getter sur le progrès du cycle
     */
    public double getCycleProgress() {
        return  cycleProgress;
    }

    /**
     * Getter sur le cycle statique
     */
    public double getStaticCycle(){
        return 0.00;
    }





}
