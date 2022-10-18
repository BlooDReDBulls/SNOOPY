import java.applet.Applet;
import java.awt.*;
import java.awt.desktop.AppEvent;
import java.awt.event.*;

public class Personnage {
    private int positionX;
    private int positionY;

    protected void leftArrow(KeyEvent e)
    {
        if (e.getKeyChar() == KeyEvent.VK_LEFT){
            positionX-=1;
        }
        System.out.println(positionX);
    }
    public Personnage()
    {
        positionX = 5;
        positionY = 5;
        System.out.println(positionX);
    }



    protected void rightArrow(KeyEvent e)
    {
        if (e.getKeyChar() == KeyEvent.VK_RIGHT){
            positionY-=1;
        }
        System.out.println(positionY);
    }
}
