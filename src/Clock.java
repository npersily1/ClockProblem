import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Clock {

    private int totalh, totalm, totals,  currentHours, currentMinutes, currentSeconds, rate;
    private double degreesPerMinute, degressPerHour, hourPerMinute;

    private boolean isWhite;

    public Clock(int totalm, int totalh,  int currentMinutes, int currentHours) {
        this.totalh = totalh;
        this.totalm = totalm;
        this.currentHours = currentHours;
        this.currentMinutes = currentMinutes;
        setRates();


    }

    public Clock(int totalh, int totalm, int currentHours, int currentMinutes, int rate) {
        this.totalh = totalh;
        this.totalm = totalm;
        this.currentHours = currentHours;
        this.currentMinutes = currentMinutes;
        this.rate = rate;
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
    public double getOverlap() {
        return getInbetweenMins() / (degreesPerMinute - hourPerMinute);
    }
    public double getSpider() {
        isWhite = true;
        return getInbetweenMins() / (degreesPerMinute - (hourPerMinute * rate));
    }

    public void draw(Graphics g, ClockProblemViewer c) {

        Graphics2D g2d = (Graphics2D)g;
//        if (isWhite) g.setColor(Color.WHITE);
//        else g.setColor(Color.BLACK);

        AffineTransform original = g2d.getTransform();

        Rectangle2D.Double mh = new Rectangle2D.Double(766.5, 150, 10, 150);

        g2d.rotate(Math.toRadians(currentMinutes * degreesPerMinute), 776.5, 300);
        g2d.fill(mh);
        g2d.setTransform(original);

        Rectangle2D.Double hh = new Rectangle2D.Double(766.5, 225, 10, 75);

        g2d.rotate(Math.toRadians(currentHours * degressPerHour + hourPerMinute * currentMinutes), 776.5, 300);
        g2d.fill(hh);
        g2d.setTransform(original);

        Ellipse2D.Double middle = new Ellipse2D.Double(758.5,285,30,30);
        g2d.fill(middle);
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

    public int getTotals() {
        return totals;
    }

    public void setTotals(int totals) {
        this.totals = totals;
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

    public int getCurrentSeconds() {
        return currentSeconds;
    }

    public void setCurrentSeconds(int currentSeconds) {
        this.currentSeconds = currentSeconds;
    }
}
