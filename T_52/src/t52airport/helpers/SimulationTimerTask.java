package t52airport.helpers;

import t52airport.Tower;

import java.util.TimerTask;

/**
 * Task to trigger the intervals in the simulation.
 */
public class SimulationTimerTask extends TimerTask {
    /**
     * The tower to trigger the landing process.
     */
    private final Tower tower;
    /**
     * True, if the task has been disabled.
     */
    private boolean taskEnded;

    /**
     * A task to trigger the landing process of the tower. Moves the simulation forward.
     *
     * @param tower The tower to act on.
     */
    public SimulationTimerTask(Tower tower) {
        this.taskEnded = false;
        this.tower = tower;
    }

    /**
     * Returns true if the task has ended.
     *
     * @return True if the task is ended, false if still running.
     */
    public boolean isTaskEnded() {
        return taskEnded;
    }

    /**
     * Triggers the landing process or ends the simulation process.
     */
    @Override
    public void run() {
        if (tower.isNoMorePlanesWantToLand()) {
            this.cancel();
            this.taskEnded = true;
        } else {
            tower.startLandingProcess();
        }
    }
}
