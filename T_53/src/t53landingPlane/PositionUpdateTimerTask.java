package t53landingPlane;

import t53landingPlane.Plane.ControlUnit;

import java.util.TimerTask;

public class PositionUpdateTimerTask extends TimerTask {
    private final ControlUnit controlUnit;

    public PositionUpdateTimerTask(ControlUnit controlUnit ){
        this.controlUnit = controlUnit;
    }

    @Override
    public void run() {
        this.controlUnit.updatePositionData();
    }
}
