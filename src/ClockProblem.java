import javax.swing.*;
import java.awt.event.*;

public class ClockProblem implements KeyListener, MouseListener, ActionListener {

    public final static Button UNDO = new Button(11, 526, 69, 67);

    public final static Button[] OOB = new Button[]{new Button(10000,10000,0,0)};
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

    public void initScreens() {
        root = new Screen(1, new ImageIcon("Resources/title.png").getImage(), null, 0);
        Screen options = new Screen(4, new ImageIcon("Resources/options.png").getImage(), root, 0);

        Button b = new Button(279, 442, 443, 113);
        Button[] bArr = new Button[1];
        bArr[0] = b;
        root.setB(bArr);

        Screen[] s = new Screen[1];
        s[0] = options;
        root.setS(s);

        Screen minutes = new Screen(1, new ImageIcon("Resources/input1.png").getImage(), options, 4);
        // Continue
        bArr = new Button[1];
        bArr[0] = new Button(351, 519, 261, 74);
        minutes.setB(bArr);

        Screen seconds = new Screen(1, new ImageIcon("Resources/input2.png").getImage(), options, 6);
        bArr = new Button[1];
        bArr[0] = new Button(369, 519, 262, 75);
        seconds.setB(bArr);

        Screen overlap = minutes;

        Screen warning = new Screen(1, new ImageIcon("Resources/warning.png").getImage(), options, 0);
        bArr = new Button[1];
        bArr[0] = new Button(405, 440, 232, 121);
        warning.setB(bArr);

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
        s[1] = seconds;
        s[2] = overlap;
        s[3] = warning;
        options.setS(s);

        Screen spider = new Screen(1, new ImageIcon("Resources/input3.png").getImage(), warning, 5);
        s = new Screen[1];
        s[0] = spider;
        warning.setS(s);
        bArr = new Button[1];
        b = new Button(520, 560, 180, 51);
        bArr[0] = b;

        Screen trial = new Screen(0, new ImageIcon("Resources/TrialClock.png").getImage(), minutes, 0, true);
        s = new Screen[1];
        s[0] = trial;
        minutes.setS(s);
        trial.setB(new Button[1]);
    }
    public boolean checkInput() {
        if(input.length() == currentScreen.getInputArgs() * 2) {
            return true;
        }
        return false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (currentScreen.getInputArgs() == 0) {
            System.out.println("wrong screen");
            return;
        }
//        System.out.println(e.getKeyCode());
//        if (e.getKeyCh() == KeyEvent.) {
//            input = input.substring(0, input.length() - 1);
//        }

        if (input.length()  >= currentScreen.getInputArgs() * 2) {
            System.out.println("overboard");
            window.repaint();
            return;

        }

        if (isNumber(e.getKeyChar())) {
            input += e.getKeyChar();
        } else {
            System.out.println("monkey");
        }
        System.out.println(input.length());
        window.repaint();

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("stuff");
        if(currentScreen.getParent() == root.getS()[0] || root.getS()[0].getS()[3] == currentScreen.getParent()) {
            if (currentScreen.getB()[0].isClicked(e.getX(), e.getY()) && checkInput()) {
                currentScreen = currentScreen.getS()[0];
                window.repaint();
            }
        }
        if(currentScreen.isLeaf() && UNDO.isClicked(e.getX(), e.getY())) {
            input = "";
        }

        for (int i = 0; i < currentScreen.getB().length; i++) {
            if (currentScreen.getB()[i].isClicked(e.getX(), e.getY())) {
                currentScreen = currentScreen.getS()[i];
                window.repaint();
                break;
            }
        }
        if (UNDO.isClicked(e.getX(), e.getY()) && currentScreen != root) {
            currentScreen = currentScreen.getParent();
            window.repaint();
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

    public Clock makeClock() {


        return new Clock(Integer.parseInt(input.substring(0,2)), Integer.parseInt(input.substring(2,4)), Integer.parseInt(input.substring(4,6)), Integer.parseInt(input.substring(6,8)));
    }
    // I know there is another pow class, but it was working weird so I just made it
    public int pow(int B, int E) {
        int total = B;
        for (int i = 0; i < E - 1; i++) {
            total *= B;
        }
        return total;

    }

    public static void main(String[] args) {
        ClockProblem c = new ClockProblem();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        window.repaint();
    }
}
