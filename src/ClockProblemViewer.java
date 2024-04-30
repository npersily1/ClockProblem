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
            if(c.isWhite()) g.setColor(Color.WHITE); else g.setColor(Color.BLACK);
            g.drawString(String.format("%.2f", this.c.getVal(c)), 400, 300 );
            c.draw(g,this);
        }
        if(c.getCurrentScreen().getParent() == c.getRoot().getS()[0] || c.getRoot().getS()[0].getS()[3] == c.getCurrentScreen().getParent()) {
            int y = -10;
            int[] x = {750,760};

            if(c.getCurrentScreen() == c.getRoot().getS()[0].getS()[3].getS()[0]) g.setColor(Color.WHITE); else g.setColor(Color.BLACK);

            for (int i = 0; i < c.getInput().length(); i++) {
                if((i % 2) == 0) y += 100;
                g.drawString(c.getInput().substring(i, i + 1), x[i % 2], y);
            }

        }
    }
}
