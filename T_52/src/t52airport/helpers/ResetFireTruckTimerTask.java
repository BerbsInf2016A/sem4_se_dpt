package t52airport.helpers;

import t52airport.FireTruck;

import java.util.TimerTask;

/**
 * A timer task, which resets the fire truck in use state, after the configured time.
 */
public class ResetFireTruckTimerTask extends TimerTask {

    /**
     * The firetruck to work on.
     */
    private final FireTruck truck;

    /**
     * Constructor for the timer task.
     * @param truck The fire truck to work on.
     */
    public ResetFireTruckTimerTask(FireTruck truck) {
        super();
        this.truck = truck;
    }

    /**
     * Resets the fire truck in use state.
     */
    @Override
    public void run() {
        truck.setInUse(false);
    }
}
