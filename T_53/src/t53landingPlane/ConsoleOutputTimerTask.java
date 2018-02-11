package t53landingPlane;

import t53landingPlane.Plane.ControlUnit;

import java.util.TimerTask;

/**
 * The timer task for updating the console output.
 */
public class ConsoleOutputTimerTask extends TimerTask {
    private ControlUnit controlUnit;

    /**
     * The Constructor of the Console Output Timer Task.
     *
     * @param controlUnit The Control Unit
     */
    public ConsoleOutputTimerTask(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
    }

    /**
     * The method that the timer is going to repeat.
     */
    @Override
    public void run() {
        this.controlUnit.outputInfoToConsole();
    }
}
