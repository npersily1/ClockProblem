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
    }
}
