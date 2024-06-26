import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Clock {

    private int totalh, totalm, currentHours, currentMinutes, rate, diff;
    private double degreesPerMinute, degressPerHour, hourPerMinute;
    private double[] rates;

    private boolean isWhite, isDegrees;

    // Regular and Overlap constructor
    public Clock(int totalh, int totalm, int currentHours, int currentMinutes) {
        this.totalh = totalh;
        this.totalm = totalm;
        this.currentHours = currentHours;
        this.currentMinutes = currentMinutes;
        rates = new double[3];
        setRates();
        isDegrees = false;

    }
    // Difference mode constructor
    public Clock(int totalh, int totalm, int currentHours, int currentMinutes, String s) {
        this.totalh = totalh;
        this.totalm = totalm;
        this.currentHours = currentHours;
        this.currentMinutes = currentMinutes;
        diff = Integer.parseInt(s);
        rates = new double[3];
        setRates();

    }
    // Spider constructor
    public Clock(int totalh, int totalm, int currentHours, int currentMinutes, int rate) {
        this.totalh = totalh;
        this.totalm = totalm;
        this.currentHours = currentHours;
        this.currentMinutes = currentMinutes;
        this.rate = rate;
        rates = new double[3];
        isWhite = true;
        setRates();
    }
    // Sets the rates to be printed
    public void setRates() {
        degressPerHour = 360.0 / totalh;

        degreesPerMinute = 360.0 / totalm;
        hourPerMinute = degressPerHour / totalm;

        rates[0] = degreesPerMinute;
        rates[1] = degressPerHour;
        rates[2] = hourPerMinute;
        if (isWhite) {
            rates[2] = hourPerMinute * rate / 100.0;
        }
    }
    // Returns correct answer to difference mode
    public double degrees() {
        if (getInbetweenMinsSpider() < 0 && getInbetweenMinsSpider() > -90) {

            double total = getOverlap();
            total += diff / (degreesPerMinute - hourPerMinute);
            return total;
        } else if (getInbetweenMinsSpider() > 0 & getInbetweenMinsSpider() < 90) {
            double total = getOverlap();
            total += (diff - total) / (degreesPerMinute - hourPerMinute);
            return total;
        }

        return (diff - getInbetweenMins()) / (degreesPerMinute - hourPerMinute);
    }
    // Gets angle between minuts
    public double getInbetweenMins() {

        double hPos = currentHours * degressPerHour + hourPerMinute * currentMinutes;
        double mPos = currentMinutes * degreesPerMinute;
        return Math.abs(hPos - mPos);

    }
    // Gets angle between minutes but you are able to tell if the hour hand is before or after minute hand
    // only to be used as a helper method to degrees and spider
    private double getInbetweenMinsSpider() {

        double hPos = currentHours * degressPerHour + hourPerMinute * currentMinutes;
        double mPos = currentMinutes * degreesPerMinute;
        return hPos - mPos;

    }
    // Returns answer to overlap problem
    public double getOverlap() {

        return getInbetweenMins() / (degreesPerMinute - hourPerMinute);

    }
    // Returns an answer to a spider problem
    public double getSpider() {
        double diff = getInbetweenMinsSpider();
        if (diff >= 0) {
            return diff / (degreesPerMinute - (hourPerMinute * rate / 100.0));
        }
        return (360 + diff) / degreesPerMinute - (hourPerMinute * rate / 100.0);
    }
    // Draw a clock
    //https://www.youtube.com/watch?v=zCiMlbu1-aQ&t=551s source for rotation
    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        // Set the color
        if (isWhite) g.setColor(Color.WHITE);
        else g.setColor(Color.BLACK);
        // Draws both hands
        AffineTransform original = g2d.getTransform();

        Rectangle2D.Double mh = new Rectangle2D.Double(766.5, 150, 10, 150);

        g2d.rotate(Math.toRadians(currentMinutes * degreesPerMinute), 776.5, 300);
        g2d.fill(mh);
        g2d.setTransform(original);

        Rectangle2D.Double hh = new Rectangle2D.Double(766.5, 225, 10, 75);

        g2d.rotate(Math.toRadians(currentHours * degressPerHour + hourPerMinute * currentMinutes), 776.5, 300);
        g2d.fill(hh);
        g2d.setTransform(original);

        // Draws center
        Ellipse2D.Double middle = new Ellipse2D.Double(766.5, 285, 30, 30);
        g2d.fill(middle);

    }

    public void printRate(Graphics g) {
        // Draws the rates
        int y = 175;
        int x = 400;
        for (int i = 0; i < 3; i++) g.drawString(String.format("%.2f", rates[i]), x, y + i * 90);

    }
    // Getters and setters

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

    public boolean isDegrees() {
        return isDegrees;
    }

    public void setDegrees(boolean degrees) {
        isDegrees = degrees;
    }
}
