package t52airport;

import t52airport.helpers.Configuration;
import t52airport.helpers.ResetFireTruckTimerTask;

import java.util.Timer;

public class FireTruck {
    private final String id;
    private boolean isInUse;
    private final Timer timer;
    private final String firesStationId;

    public FireTruck(String id, String stationId) {
        this.id = id;
        this.isInUse = false;
        this.timer = new Timer();
        this.firesStationId = stationId;
    }

    public String getId() {
        return id;
    }


    public boolean isInUse() {
        return isInUse;
    }

    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }

    public void moveOut(Runway runway) {
        System.out.printf("FireTruck <%s> from FireStation <%s>: Moving out to <%s> to help crashed plane <%s> %n",
                this.getId(), this.firesStationId, runway.getId(), runway.getPlane().getShortPlaneIdentifier());
        this.setInUse(true);
        ResetFireTruckTimerTask task = new ResetFireTruckTimerTask(this);
        timer.schedule(task, Configuration.instance.fireTruckInterventionDurationInMilliSeconds);
    }


}
