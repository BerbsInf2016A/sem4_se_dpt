package t52airport;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class FireStation implements IPlaneCrashAlarmButtonListener {
    private ITower tower;
    private List<ITower> disasterButtonListeners;
    private List<FireTruck> fireTrucks;
    private IFireTruckMoveOutCommand fireTruckMoveOutCommand;
    private Runway runway;

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }


    public FireStation(ITower tower) {
        this.tower = tower;
        this.disasterButtonListeners = new ArrayList<>();
        this.fireTruckMoveOutCommand = new FireTruckMoveOutCommand();
        this.fireTrucks = new ArrayList<>();
    }


    public void planesCrashed(List<Runway> planeCrashes) {
        throw new NotImplementedException();
    }

    public void addDisasterButtonListener(ITower listener) {
        this.disasterButtonListeners.add(listener);
    }
    public void removeDisasterButtonListener(ITower listener) {
        if(this.disasterButtonListeners.contains(listener)) {
            this.disasterButtonListeners.remove(listener);
        }
    }

    public void triggerDisasterAlarm(String location) {
        for (ITower tower : this.disasterButtonListeners ) {
            tower.disasterOccurred(location);
        }
    }
}
