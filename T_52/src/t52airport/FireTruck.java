package t52airport;

import t52airport.helpers.ResetFireTruckTimerTask;

import java.util.Timer;

public class FireTruck {
    private String id;
    private boolean isInUse;
    private ResetFireTruckTimerTask resetFireTruckTimerTask;
    private Timer timer;

    public FireTruck(String id) {
        this.id = id;
        this.isInUse = false;
        this.timer = new Timer();
        this.resetFireTruckTimerTask = new ResetFireTruckTimerTask(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isInUse() {
        return isInUse;
    }

    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }

    public void moveOut(Runway runway) {
        this.setInUse(true);
       timer.schedule(this.resetFireTruckTimerTask, 1000);
    }


}
