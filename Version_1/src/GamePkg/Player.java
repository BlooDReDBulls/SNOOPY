package GamePkg;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {
    int x;
    int y;

    public Player()
    {
        x = 5;
        y = 5;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void left()
    {
        x -= 1;
    }

    public void right()
    {
        x += 1;
    }

    public void up()
    {
        y -= 1;
    }

    public void down()
    {
        y += 1;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left();
            System.out.println("left");
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right();
            System.out.println("right");
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            up();
            System.out.println("up");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            down();
            System.out.println("down");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
