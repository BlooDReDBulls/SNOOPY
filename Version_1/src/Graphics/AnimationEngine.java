package Graphics;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimationEngine {

    private Timer timer;
    private final int fps;
    private long cycleStartTime;
    private double cycleProgress;

    private final TimerHandler timerHandler;

    public AnimationEngine(int fps){
        this.fps = fps;
        timerHandler = new TimerHandler();

    }

    protected void clean() {
        cycleProgress = 0;
        cycleStartTime = 0;
    }

    public void stop() {
        if (timer != null) {
            timer.stop();
        }
        clean();
    }

    public void start() {
        stop();
        timer = new Timer(1000 / fps, timerHandler);
        timer.start();
    }

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

    public double getCycleProgress() {
        return  cycleProgress;
    }




}
