package t52airport.helpers;

import t52airport.FireTruck;

import java.util.TimerTask;

public class ResetFireTruckTimerTask extends TimerTask {

    private final FireTruck truck;

    public ResetFireTruckTimerTask(FireTruck truck ){
        this.truck = truck;
    }

    @Override
    public void run() {
        truck.setInUse(false);
    }
}
