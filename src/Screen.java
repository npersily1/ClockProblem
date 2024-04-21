import java.awt.*;

public class Screen {
    Image i;
    Screen[] s;
    Screen parent;

    Button[] b;

    public Screen(int sSize, int bSize, Image i, Screen sc) {
        s = new Screen[sSize];
        this.i = i;
        parent = sc;

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
        g.drawImage(i,0,0, c);
    }

}
