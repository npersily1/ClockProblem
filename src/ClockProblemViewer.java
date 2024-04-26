import javax.swing.*;
import java.awt.*;

public class ClockProblemViewer extends JFrame {

    public static final int SCREEN_WIDTH = 1000,
    SCREEN_HEIGHT = 600;

    public static final String title = "Clock Problem Solver";

    private ClockProblem c;

    public ClockProblemViewer(ClockProblem c) {
        setTitle(title);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.c = c;
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        c.getCurrentScreen().draw(g,this);

        if(c.getCurrentScreen().isLeaf() ) {
            Clock c = this.c.makeClock();
            c.draw(g,this);
            g.drawString("" + c.getInbetweenMins(), 500, 300 );
        }
        if(c.getCurrentScreen().getParent() == c.getRoot().getS()[0] || c.getRoot().getS()[0].getS()[3] == c.getCurrentScreen().getParent()) {
            int length = c.getInput().length()/2;
            if(c.getInput().length() % 2  == 1) { length++; }


            int counter = 0;
            int y = 100;
            g.setColor(Color.BLACK);
            for (int i = 0; i < length; i++) {
                g.drawString(c.getInput().substring(i,i+2), 700,y);
                y+=100;
            }
        }
    }
}
