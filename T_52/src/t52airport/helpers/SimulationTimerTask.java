package t52airport.helpers;

import t52airport.Tower;

import java.util.TimerTask;

public class SimulationTimerTask extends TimerTask {
    private final Tower tower;
    private boolean taskEnded;

    public SimulationTimerTask(Tower tower) {
        this.taskEnded = false;
        this.tower = tower;
    }

    public boolean isTaskEnded() {
        return taskEnded;
    }

    @Override
    public void run() {
        if (tower.isNoMorePlanesWantToLand()) {
            this.cancel();
            this.taskEnded = true;
        }
        tower.startLandingProcess();
    }
}
