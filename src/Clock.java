public class Clock {

    private int totalh, totalm, totals,  currentHours, currentMinutes, currentSeconds;



    public Clock(int h1, int h2, int m1, int m2) {

    }
    public Clock(int h1, int h2, int m1, int m2, int s1, int s2) {

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
