package t52airport;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tower implements ITower {

    private final List<FireStation> fireStations;
    private final List<IPlaneCrashAlarmButtonListener> planeCrashAlarmButtonListeners;
    private final List<Plane> planes;
    private final List<Runway> runways;
    private boolean noMorePlanesWantToLand;

    public Tower() {
        this.fireStations = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.runways = new ArrayList<>();
        this.planeCrashAlarmButtonListeners = new ArrayList<>();
    }

    public boolean isNoMorePlanesWantToLand() {
        return noMorePlanesWantToLand;
    }

    public List<FireStation> getFireStations() {
        return fireStations;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public List<Runway> getRunways() {
        return runways;
    }

    public void startLandingProcess() {
        Plane firstPlane = null;
        Runway firstRunway = this.runways.get(0);
        Plane secondPlane = null;
        Runway secondRunway = this.runways.get(1);
        if (this.planes.size() > 0) {
            firstPlane = this.planes.remove(0);
            firstPlane.initiateLandingPermission(firstRunway, firstPlane);
            firstRunway.initiateLandingPermission(firstRunway, firstPlane);
        }
        if (this.planes.size() > 0) {
            secondPlane = this.planes.remove(0);
            secondPlane.initiateLandingPermission(secondRunway, secondPlane);
            secondRunway.initiateLandingPermission(secondRunway, secondPlane);
        }

        if (firstPlane != null && secondPlane != null) {
            List<Runway> crashes = Stream.of(firstRunway, secondRunway).filter(runway -> runway.getPlane().isCrashed()).collect(Collectors.toList());
            if (crashes.size() > 0) {
                this.triggerPlaneCrashAlarm(crashes);
            }
            if (!firstPlane.isCrashed())
                System.out.printf("Tower: Plane <%s> has landed on runway <%s> %n", firstPlane.getShortPlaneIdentifier(), firstRunway.getId());
            if (!secondPlane.isCrashed())
                System.out.printf("Tower: Plane <%s> has landed on runway <%s> %n", secondPlane.getShortPlaneIdentifier(), secondRunway.getId());
        } else {
            handleLandingResultForPlane(firstPlane, firstRunway);
            handleLandingResultForPlane(secondPlane, secondRunway);
        }
        if (this.planes.size() == 0) {
            if (!this.isNoMorePlanesWantToLand()) {
                this.noMorePlanesWantToLand = true;
                System.out.printf("Tower: No more planes want to land %n");
            }
        }
    }

    private void handleLandingResultForPlane(Plane plane, Runway runway) {
        if (plane != null) {
            if (plane.isCrashed()) {
                this.triggerPlaneCrashAlarm(Collections.singletonList(runway));
            } else {
                if (!plane.isCrashed())
                    System.out.printf("Tower: Plane <%s> is landed %n", plane.getShortPlaneIdentifier());
            }
        }
    }

    public void registerFireStation(FireStation fireStation, Runway runway) {
        // The firestation can not already be registered and the firestation can only be registered for a runway,
        // which is under the view of the Tower.
        if (!this.fireStations.contains(fireStation) && this.runways.contains(runway)) {
            this.fireStations.add(fireStation);
            // Register the plane crash and disaster button listener.
            this.addPlaneCrashAlarmButtonListener(fireStation);
            fireStation.addDisasterButtonListener(this);
            fireStation.setRunway(runway);
            System.out.printf("Tower: FireStation <%s> was registered for Runway <%s> %n", fireStation.getId(), runway.getId());
        }
    }

    public void registerLandingPlane(Plane plane) {
        if (!this.planes.contains(plane)) {
            this.planes.add(plane);
        }
    }

    public void registerRunway(Runway runway) {
        if (!this.runways.contains(runway)) {
            this.runways.add(runway);
            System.out.printf("Tower: Runway <%s> was registered %n", runway.getId());
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

    public void disasterOccurred(String location, String message) {
        System.out.printf("Tower: Disaster occurred at %s! Message: %s %n", location, message);
    }

    private void triggerPlaneCrashAlarm(List<Runway> runways) {
        runways.forEach(runway -> System.out.printf("Tower: Plane <%s> is crashed on runway <%s> %n", runway.getPlane().getShortPlaneIdentifier(), runway.getId()));
        for (IPlaneCrashAlarmButtonListener listener : this.planeCrashAlarmButtonListeners) {
            listener.planesCrashed(runways);
        }
    }
}
