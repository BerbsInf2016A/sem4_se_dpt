package t52airport;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

public class Tower implements ITower {

    private List<FireStation> fireStations;
    private List<IPlaneCrashAlarmButtonListener> planeCrashAlarmButtonListeners;
    private List<Plane> planes;
    private List<Runway> runways;

    public List<FireStation> getFireStations() {
        return fireStations;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public List<Runway> getRunways() {
        return runways;
    }

    public Tower() {
        this.fireStations = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.runways = new ArrayList<>();
        this.planeCrashAlarmButtonListeners = new ArrayList<>();
    }

    public void registerFireStation(FireStation fireStation, Runway runway) {
        // The firestation can not already be registered and the firestation can only be registered for a runway,
        // which is under the view of the Tower.
        if (!this.fireStations.contains(fireStation) && this.runways.contains(runway)){
            this.fireStations.add(fireStation);
            // Register the plane crash and disaster button listener.
            this.addPlaneCrashAlarmButtonListener(fireStation);
            fireStation.addDisasterButtonListener(this);
        }
    }

    public void registerLandingPlane(Plane plane) {
        if(!this.planes.contains(plane)) {
            this.planes.add(plane);
        }
    }

    public void registerRunway(Runway runway) {
        if(!this.runways.contains(runway)) {
            this.runways.add(runway);
        }
    }

    public void addPlaneCrashAlarmButtonListener(IPlaneCrashAlarmButtonListener listener) {
        if (!this.planeCrashAlarmButtonListeners.contains(listener)) {
            this.planeCrashAlarmButtonListeners.add(listener);
        }
    }

    public void removePlaneCrashAlarmButtonListener(IPlaneCrashAlarmButtonListener listener) {
        if (this.planeCrashAlarmButtonListeners.contains(listener)) {
            this.planeCrashAlarmButtonListeners.remove(listener);
        }
    }

    public void disasterOccurred(String location) {
        throw new NotImplementedException();
    }
    private void triggerPlaneCrashAlarm(List<Runway> runways){
        for (IPlaneCrashAlarmButtonListener listener : this.planeCrashAlarmButtonListeners) {
            listener.planesCrashed(runways);
        }
    }
}
