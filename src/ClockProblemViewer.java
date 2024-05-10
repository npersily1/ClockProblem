import javax.swing.*;
import java.awt.*;

public class ClockProblemViewer extends JFrame {

    public static final int SCREEN_WIDTH = 1000,
            SCREEN_HEIGHT = 600;

    public static final String title = "Clock Problem Solver";

    private ClockProblem c;
    // Basic JFrame constructor
    public ClockProblemViewer(ClockProblem c) {
        setTitle(title);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.c = c;
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        // Draw background screen
        c.getCurrentScreen().draw(g, this);
        // If a clock screen
        if (c.getCurrentScreen().isLeaf()) {
            // Get the clock
            Clock c = this.c.makeClock();
            // Set correct color
            if (c.isWhite()) g.setColor(Color.WHITE);
            else g.setColor(Color.BLACK);

            // Draw the answer, clock, and rates
            g.drawString(String.format("%.2f", this.c.getVal(c)), 300, 440);
            c.draw(g);
            c.printRate(g);
            // Decides the unit
            if (c.isDegrees()) {
                g.drawString("degrees", 350, 440);
            } else {
                g.drawString("minutes", 350, 440);
            }
        }

        // If an input screen
        if (c.getCurrentScreen().getParent() == c.getRoot().getS()[0] || c.getRoot().getS()[0].getS()[3] == c.getCurrentScreen().getParent()) {
            int y = -10;
            int[] x = {750, 760};
            // Set color
            if (c.getCurrentScreen() == c.getRoot().getS()[0].getS()[3].getS()[0]) g.setColor(Color.WHITE);
            else g.setColor(Color.BLACK);
            // Draw the input
            for (int i = 0; i < c.getInput().length(); i++) {
                if ((i % 2) == 0) y += 100;
                g.drawString(c.getInput().substring(i, i + 1), x[i % 2], y);
            }

        }
    }
}
