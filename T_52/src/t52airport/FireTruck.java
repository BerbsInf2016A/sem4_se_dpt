package t52airport;

import t52airport.helpers.Configuration;
import t52airport.helpers.ResetFireTruckTimerTask;

import java.util.Timer;

/**
 * A class representing a fire truck.
 */
public class FireTruck {
    /**
     * The id of the fire truck.
     */
    private final String id;
    /**
     * A timer which is used to reset the in-use state of the truck.
     */
    private final Timer timer;
    /**
     * The id of the owning fire station.
     */
    private final String firesStationId;
    /**
     * True if the fire truck is in use. False if not.
     */
    private boolean isInUse;

    /**
     * Constructor for the fire truck.
     *
     * @param id The id of the fire truck.
     * @param stationId The id of the owning fire station.
     */
    public FireTruck(String id, String stationId) {
        this.id = id;
        this.isInUse = false;
        this.timer = new Timer();
        this.firesStationId = stationId;
    }

    /**
     * Get the id of the fire truck.
     *
     * @return The id of the fire truck.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the state of the fire truck.
     *
     * @return True, if the fire truck is in use, false if not.
     */
    public boolean isInUse() {
        return isInUse;
    }

    /**
     * Set the using state of the fire truck.
     *
     * @param inUse The value to set.
     */
    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }

    /**
     * Command the fire truck to move out.
     *
     * @param runway The runway to move out to.
     */
    public void moveOut(Runway runway) {
        System.out.printf("FireTruck <%s> from FireStation <%s>: Moving out to <%s> to help crashed plane <%s> %n",
                this.getId(), this.firesStationId, runway.getId(), runway.getPlane().getShortPlaneIdentifier());
        this.setInUse(true);
        ResetFireTruckTimerTask task = new ResetFireTruckTimerTask(this);
        timer.schedule(task, Configuration.instance.fireTruckInterventionDurationInMilliSeconds);
    }
}
