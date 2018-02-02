package t52airport;

import java.util.ArrayList;

public class Tower implements ITower {
    public ArrayList<FireStation> getFireStations() {
        return fireStations;
    }

    public ArrayList<Plane> getPlanes() {
        return planes;
    }

    public ArrayList<Runway> getRunways() {
        return runways;
    }

    private ArrayList<FireStation> fireStations;
    // TODO: Implement Obser pattern.
    //private ArrayList<IPlaneCrashAlarmButtonListener> planeCrashAlarmButtonListeners;
    private ArrayList<Plane> planes;
    private ArrayList<Runway> runways;

    public Tower() {
        this.fireStations = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.runways = new ArrayList<>();
    }

    public void registerFireStation(FireStation fireStation, Runway runway) {
        // The firestation can not already be registered and the firestation can only be registered for a runway,
        // which is under the view of the Tower.
        if (!this.fireStations.contains(fireStation) && this.runways.contains(runway)){
            this.fireStations.add(fireStation);
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
}
