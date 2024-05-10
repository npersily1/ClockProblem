import javax.swing.*;
import java.awt.event.*;

public class ClockProblem implements KeyListener, MouseListener {

    public final static Button UNDO = new Button(11, 526, 69, 67);

    private ClockProblemViewer window;

    private Screen root;
    private Screen currentScreen;
    private String input;

    public ClockProblem() {
        input = "";
        initScreens();
        currentScreen = root;
        window = new ClockProblemViewer(this);
        window.addMouseListener(this);
        window.addKeyListener(this);

    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }

    public void setCurrentScreen(Screen currentScreen) {
        this.currentScreen = currentScreen;
    }

    public Screen getRoot() {
        return root;
    }

    public void setRoot(Screen root) {
        this.root = root;
    }

    // Initializes a the tree of all different game states
    public void initScreens() {
        // Makes title and options
        root = new Screen(1, new ImageIcon("Resources/title.png").getImage(), null, 0);
        Screen options = new Screen(4, new ImageIcon("Resources/options.png").getImage(), root, 0);
        // Adds continue button and options screen to the Root
        Button b = new Button(279, 442, 443, 113);
        Button[] bArr = new Button[1];
        bArr[0] = b;
        root.setB(bArr);

        Screen[] s = new Screen[1];
        s[0] = options;
        root.setS(s);
        // Makes all the different branches off of the the options screen and their respective continue buttons

        // Regular Mode
        Screen minutes = new Screen(1, new ImageIcon("Resources/input1.png").getImage(), options, 4);
        bArr = new Button[1];
        bArr[0] = new Button(351, 519, 261, 74);
        minutes.setB(bArr);
        // Difference mode
        Screen difference = new Screen(1, new ImageIcon("Resources/input2.png").getImage(), options, 5);
        bArr = new Button[1];
        bArr[0] = new Button(369, 519, 262, 75);
        difference.setB(bArr);
        // Clock
        Screen thing = new Screen(0, new ImageIcon("Resources/TrialClock.png").getImage(), difference, 0, true);
        Screen[] things = new Screen[1];
        things[0] = thing;
        difference.setS(things);
        // Overlap mode
        Screen overlap = new Screen(1, new ImageIcon("Resources/input1.png").getImage(), options, 4);
        // Continue
        bArr = new Button[1];
        bArr[0] = new Button(351, 519, 261, 74);
        overlap.setB(bArr);
        // Spider warning
        Screen warning = new Screen(1, new ImageIcon("Resources/warning.png").getImage(), options, 0);
        bArr = new Button[1];
        bArr[0] = new Button(405, 440, 232, 121);
        warning.setB(bArr);

        // Actually adds buttons and screens
        bArr = new Button[4];
        b = new Button(1, 142, 443, 113);
        bArr[0] = b;
        b = new Button(544, 145, 421, 100);
        bArr[1] = b;
        b = new Button(45, 381, 416, 78);
        bArr[2] = b;
        b = new Button(575, 382, 370, 85);
        bArr[3] = b;
        options.setB(bArr);

        s = new Screen[4];
        s[0] = minutes;
        s[1] = difference;
        s[2] = overlap;
        s[3] = warning;
        options.setS(s);
        // Inits spider screen after warning as well as corresponding buttons
        Screen spider = new Screen(1, new ImageIcon("Resources/input3.png").getImage(), warning, 5);
        s = new Screen[1];
        s[0] = spider;
        warning.setS(s);
        bArr = new Button[1];
        bArr[0] = new Button(351, 519, 261, 74);

        spider.setB(bArr);
        // Inits every clock4
        Screen trial = new Screen(0, new ImageIcon("Resources/TrialClock.png").getImage(), minutes, 0, true);
        s = new Screen[1];
        s[0] = trial;
        minutes.setS(s);

        Screen trial2 = new Screen(0, new ImageIcon("Resources/TrialClock.png").getImage(), overlap, 0, true);
        s = new Screen[1];
        s[0] = trial2;
        overlap.setS(s);
        trial.setB(new Button[1]);

        Screen spiderTrial = new Screen(0, new ImageIcon("Resources/SpiderClock.png").getImage(), spider, 0, true);
        s = new Screen[1];
        s[0] = spiderTrial;
        spider.setS(s);

    }

    // Returns true if the Input is long enough
    public boolean checkInput() {
        if (input.length() == currentScreen.getInputArgs() * 2) {
            return true;
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

        // Ignores typing of not on a page that needs typing
        if (currentScreen.getInputArgs() == 0) {
            return;
        }
        // If you have have typed more than neccesary
        if (input.length() >= currentScreen.getInputArgs() * 2) {
            return;
        }
        // If a number is typed add it to the end
        if (isNumber(e.getKeyChar())) {
            input += e.getKeyChar();
        }
        window.repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handles deletion
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE && input.length() > 0 && (currentScreen.getParent() == root.getS()[0] || root.getS()[0].getS()[3] == currentScreen.getParent())) {
            System.out.println("deleted");
            input = input.substring(0, input.length() - 1);
            window.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        // Handles going back a screen
        if (UNDO.isClicked(e.getX(), e.getY()) && currentScreen != root) {
            // If an input screen, reset
            if (currentScreen.getParent() == root.getS()[0] || root.getS()[0].getS()[3] == currentScreen.getParent())
                input = "";
            // Go back a screen
            currentScreen = currentScreen.getParent();
            window.repaint();
            return;
        }
        // If on an input screeb
        if (currentScreen.getParent() == root.getS()[0] || root.getS()[0].getS()[3] == currentScreen.getParent()) {
            // If the button is clicked
            if (currentScreen.getB()[0].isClicked(e.getX(), e.getY()) && checkInput()) {
                // Change screens and repaint
                currentScreen = currentScreen.getS()[0];
                window.repaint();
                return;
            }
        }
        if (currentScreen.isLeaf()) return;
        // For every button check if its clicked and then progress to that screen
        for (int i = 0; i < currentScreen.getB().length; i++) {
            if (currentScreen.getB()[i].isClicked(e.getX(), e.getY())) {
                currentScreen = currentScreen.getS()[i];
                window.repaint();
                break;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    // Checks if a char is a digit
    public boolean isNumber(char c) {
        String s = "" + c;
        for (int i = 0; i < 10; i++) {

            if (s.equals("" + i)) {
                return true;
            }
        }
        return false;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    // Makes the correct clock depening on the problem type which is determined by the screen
    public Clock makeClock() {

        if (currentScreen == root.getS()[0].getS()[3].getS()[0].getS()[0])
            return new Clock(Integer.parseInt(input.substring(0, 2)), Integer.parseInt(input.substring(2, 4)), Integer.parseInt(input.substring(4, 6)), Integer.parseInt(input.substring(6, 8)), Integer.parseInt(input.substring(8, 10)));
        else if (currentScreen == root.getS()[0].getS()[1].getS()[0])
            return new Clock(Integer.parseInt(input.substring(0, 2)), Integer.parseInt(input.substring(2, 4)), Integer.parseInt(input.substring(4, 6)), Integer.parseInt(input.substring(6, 8)), input.substring(8, 10));

        return new Clock(Integer.parseInt(input.substring(0, 2)), Integer.parseInt(input.substring(2, 4)), Integer.parseInt(input.substring(4, 6)), Integer.parseInt(input.substring(6, 8)));

    }
    // Returns the answers to the specified problem
    public double getVal(Clock c) {

        if (currentScreen == root.getS()[0].getS()[0].getS()[0]) {
            c.setDegrees(true);
            return c.getInbetweenMins();
        }
        if (currentScreen == root.getS()[0].getS()[2].getS()[0]) {
            return c.getOverlap();
        }
        if (currentScreen == root.getS()[0].getS()[3].getS()[0].getS()[0]) {
            return c.getSpider();
        }
        if (currentScreen == root.getS()[0].getS()[1].getS()[0]) {
            return c.degrees();
        }
        return 0;
    }

    public static void main(String[] args) {
        ClockProblem c = new ClockProblem();

    }


}
