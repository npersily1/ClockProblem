import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Clock {

    private int totalh, totalm, totals,  currentHours, currentMinutes, currentSeconds, rateOfChange;
    private Rectangle2D r;

    public Clock(int totalm, int totalh,  int currentMinutes, int currentHours) {
        this.totalh = totalh;
        this.totalm = totalm;
        this.totals = 0;
        this.currentHours = currentHours;
        this.currentMinutes = currentMinutes;
        this.currentSeconds = 0;
    }

//    public double getAnswer(int i) {
//        if(i == 0) {
//            return getInbetweenMins()
//        }
//        if(i == 1) {
//            return 0.0;
//        }
    //}//

    public double getInbetweenMins() {

        double baseH = currentHours * 360.0 / totalh;
        double hPos = baseH + (360.0 / totalh / totalm * currentMinutes);
        double mPos = currentMinutes * 360.0 / totalm;

        return Math.abs(hPos - mPos);

    }
    //https://stackoverflow.com/questions/7517688/rotate-a-java-graphics2d-rectangle
    public void draw(Graphics g, ClockProblemViewer c) {

        Graphics2D g2d = (Graphics2D)g;
        g2d.fillRect(700,400,10,100);
        g2d.rotate(Math.toRadians(45));
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
