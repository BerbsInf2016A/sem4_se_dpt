package t53landingPlane;

import t53landingPlane.Plane.ControlUnit;

import java.util.TimerTask;

/**
 * The timer task for updating the plane position.
 */
public class PositionUpdateTimerTask extends TimerTask {
    /**
     * The control unit of the plane.
     */
    private final ControlUnit controlUnit;

    /**
     * The Constructor of the Position Update Timer Task.
     * @param controlUnit The Control Unit
     */
    public PositionUpdateTimerTask(ControlUnit controlUnit ){
        this.controlUnit = controlUnit;
    }

    /**
     * The method that the timer is going to repeat.
     */
    @Override
    public void run() {
        this.controlUnit.updatePositionData();
    }
}
