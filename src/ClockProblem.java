import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClockProblem implements KeyListener, MouseListener {



    public final static Button UNDO = new Button(11, 526, 69,67);
    private ClockProblemViewer window;

    private Screen root;
    private Screen currentScreen;
    public ClockProblem() {
        initScreens();
        currentScreen = root;
        window = new ClockProblemViewer(this);
        window.addMouseListener(this);
        window.addKeyListener(this);

    }

    public Screen getRoot() {
        return root;
    }

    public void setRoot(Screen root) {
        this.root = root;
    }

    public void initScreens() {
        root = new Screen(1,1,new ImageIcon("Resources/title.png").getImage(),null);
        Screen options = new Screen(4,5,new ImageIcon("Resources/options.png").getImage(),root);

        Button b = new Button(279, 442,443,113);
        Button[] bArr = new Button[1];
        bArr[0] = b;
        root.setB(bArr);

        Screen[] s = new Screen[1];
        s[0] = options;
        root.setS(s);

        bArr = new Button[5];

        Screen minutes = new Screen(1,2, new ImageIcon("Resources/input1.png").getImage(),options);
        b = new Button(1, 142,443,113);
        bArr[0] = b;


        Screen seconds = new Screen(1,2, new ImageIcon("Resources/input2.png").getImage(),options);
        b = new Button(544, 145,421,100);
        bArr[1] = b;

        Screen overlap = minutes;
        b = new Button(45, 381,416,78);
        bArr[2] = b;


        Screen warning = new Screen(1,2, new ImageIcon("Resources/warning.png").getImage(),options);
        b = new Button(575, 382,370,85);
        bArr[3] = b;

        s = new Screen[4];
        s[0] = minutes;
        s[1] = seconds;
        s[2] = overlap;
        s[3] = warning;
        options.setS(s);
        options.addButtons(bArr);


        Screen spider = new Screen(1,2, new ImageIcon("Resources/input3.png").getImage(), warning);
        s = new Screen[1];
        s[0] = spider;
        warning.setS(s);
    }

    @Override
    public void keyTyped(KeyEvent e) {

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
        for (int i = 0; i < currentScreen.getB().length; i++) {
            if(currentScreen.getB()[i].isClicked(e.getX(),e.getY())) {
                currentScreen = currentScreen.getS()[i];
                window.repaint();
            }
        }
        if(UNDO.isClicked(e.getX(),e.getY()) && currentScreen != root) {
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
    public static void main(String[] args) {
        ClockProblem c = new ClockProblem();

    }

}
