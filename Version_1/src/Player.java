import java.awt.event.KeyEvent;

public class Player{
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
        y -= 1;
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left();
            System.out.println("left");
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right();
            System.out.println("right");
        }
    }
}
