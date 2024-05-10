import java.awt.*;

public class Screen {
    private Image i;
    private Screen[] s;
    private Screen parent;
    private Button[] b;
    private int inputArgs;
    private boolean isLeaf;

    public Screen(int sSize, Image i, Screen sc, int args) {
        s = new Screen[sSize];
        this.i = i;
        parent = sc;
        inputArgs = args;
        isLeaf = false;

    }

    public Screen(int sSize, Image i, Screen sc, int args, boolean b) {
        s = new Screen[sSize];
        this.i = i;
        parent = sc;
        inputArgs = args;
        isLeaf = b;

    }
    // Getters and setters

    public boolean isLeaf() { return isLeaf; }

    public void setLeaf(boolean leaf) { isLeaf = leaf; }

    public int getInputArgs() {
        return inputArgs;
    }

    public void setInputArgs(int inputArgs) {
        this.inputArgs = inputArgs;
    }

    public void addButtons(Button[] b) {
        this.b = b;
    }

    public Image getI() {
        return i;
    }

    public void setI(Image i) {
        this.i = i;
    }

    public Screen[] getS() {
        return s;
    }

    public void setS(Screen[] s) {
        this.s = s;
    }

    public Screen getParent() {
        return parent;
    }

    public void setParent(Screen parent) {
        this.parent = parent;
    }

    public Button[] getB() {
        return b;
    }

    public void setB(Button[] b) {
        this.b = b;
    }

    public void draw(Graphics g, ClockProblemViewer c) {
        g.drawImage(i, 0, 0, c);
    }

}
