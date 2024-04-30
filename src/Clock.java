import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Clock {

    private int totalh, totalm, currentHours, currentMinutes, rate, diff;
    private double degreesPerMinute, degressPerHour, hourPerMinute;

    private boolean isWhite;

    public Clock(int totalm, int totalh, int currentMinutes, int currentHours) {
        this.totalh = totalh;
        this.totalm = totalm;
        this.currentHours = currentHours;
        this.currentMinutes = currentMinutes;
        setRates();

    }
    public Clock(int totalm, int totalh, int currentMinutes, int currentHours, String s) {
        this.totalh = totalh;
        this.totalm = totalm;
        this.currentHours = currentHours;
        this.currentMinutes = currentMinutes;
        diff = Integer.parseInt(s);
        setRates();

    }

    public Clock(int totalm, int totalh, int currentMinutes, int currentHours, int rate) {
        this.totalh = totalh;
        this.totalm = totalm;
        this.currentHours = currentHours;
        this.currentMinutes = currentMinutes;
        this.rate = rate;
        isWhite = true;
        setRates();
    }

    public void setRates() {
        degressPerHour = 360.0 / totalh;
        degreesPerMinute = 360.0 / totalm;
        hourPerMinute = degressPerHour / totalm;
    }

    public double getInbetweenMins() {

        double hPos = currentHours * degressPerHour + hourPerMinute * currentMinutes;
        double mPos = currentMinutes * degreesPerMinute;
        return Math.abs(hPos - mPos);

    }

    private double getInbetweenMinsSpider() {

        double hPos = currentHours * degressPerHour + hourPerMinute * currentMinutes;
        double mPos = currentMinutes * degreesPerMinute;
        return hPos - mPos;

    }

    public double getOverlap() {
        return getInbetweenMins() / (degreesPerMinute - hourPerMinute);
    }


    public double getSpider() {
        double diff = getInbetweenMinsSpider();
        if (diff >= 0) {
            return diff / (degreesPerMinute - (hourPerMinute * rate / 100.0));
        }
        return  (360 + diff) / degreesPerMinute - (hourPerMinute * rate / 100.0);
    }
    public diff() {
        double ans1 = -1 * ((-diff - currentHours * degressPerHour) / (-1 * (currentMinutes * (degreesPerMinute - hourPerMinute)) - currentMinutes);
        double ans2 =
    }

    public void draw(Graphics g, ClockProblemViewer c) {

        Graphics2D g2d = (Graphics2D) g;
        if (isWhite) g.setColor(Color.WHITE);
        else g.setColor(Color.BLACK);

        AffineTransform original = g2d.getTransform();

        Rectangle2D.Double mh = new Rectangle2D.Double(766.5, 150, 10, 150);

        g2d.rotate(Math.toRadians(currentMinutes * degreesPerMinute), 776.5, 300);
        g2d.fill(mh);
        g2d.setTransform(original);

        Rectangle2D.Double hh = new Rectangle2D.Double(766.5, 225, 10, 75);

        g2d.rotate(Math.toRadians(currentHours * degressPerHour + hourPerMinute * currentMinutes), 776.5, 300);
        g2d.fill(hh);
        g2d.setTransform(original);

        Ellipse2D.Double middle = new Ellipse2D.Double(758.5, 285, 30, 30);
        g2d.fill(middle);
    }

    public boolean isWhite() {
        return isWhite;
    }

    public void setWhite(boolean white) {
        isWhite = white;
    }

    public int getTotalh() {
        return totalh;
    }

    public void setTotalh(int totalh) {
        this.totalh = totalh;
    }

    public int getTotalm() {
        return totalm;
    }

    public void setTotalm(int totalm) {
        this.totalm = totalm;
    }

    public int getCurrentHours() {
        return currentHours;
    }

    public void setCurrentHours(int currentHours) {
        this.currentHours = currentHours;
    }

    public int getCurrentMinutes() {
        return currentMinutes;
    }

    public void setCurrentMinutes(int currentMinutes) {
        this.currentMinutes = currentMinutes;
    }

}
