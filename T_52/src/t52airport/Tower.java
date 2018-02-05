package t52airport;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An aiport tower.
 */
public class Tower implements ITower {

    /**
     * The fire stations under the control of the tower.
     */
    private final List<FireStation> fireStations;

    /**
     * A list of all listeners, which are interested in plane crashes.
     */
    private final List<IPlaneCrashAlarmButtonListener> planeCrashAlarmButtonListeners;

    /**
     * The planes which want to land at the airport.
     */
    private final List<Plane> planes;

    /**
     * The list of available towers.
     */
    private final List<Runway> runways;

    /**
     * True, if no more planes want to land.
     */
    private boolean noMorePlanesWantToLand;

    /**
     * The constructor for the tower.
     */
    public Tower() {
        this.fireStations = new ArrayList<>();
        this.planes = new ArrayList<>();
        this.runways = new ArrayList<>();
        this.planeCrashAlarmButtonListeners = new ArrayList<>();
    }

    /**
     * Flag, which shows, if there are still
     * planes which want to land.
     *
     * @return True, if no more planes want to land,
     * false if there are still planes which want to land.
     */
    public boolean isNoMorePlanesWantToLand() {
        return noMorePlanesWantToLand;
    }

    /**
     * Getter for the fire stations.
     *
     * @return The list of fire station, which are registered at the tower.
     */
    public List<FireStation> getFireStations() {
        return fireStations;
    }

    /**
     * Getter for the planes which want to land at the airport.
     *
     * @return The list of airplanes.
     */
    public List<Plane> getPlanes() {
        return planes;
    }

    /**
     * Getter for the runways.
     *
     * @return The list of runways.
     */
    public List<Runway> getRunways() {
        return runways;
    }

    /**
     * Start the landing process for the planes which are next in the list
     * of planes which want to land.
     */
    public void startLandingProcess() {
        // There should always be two runway in this simulation.
        Plane firstPlane = null;
        Runway firstRunway = this.runways.get(0);
        Plane secondPlane = null;
        Runway secondRunway = this.runways.get(1);

        // Try to get two planes from the list of planes and initiate the landing
        // process. Inform the plane and the runway of each other.
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

        // Handle the situation when two planes tried to land.
        if (firstPlane != null && secondPlane != null) {
            List<Runway> crashes = Stream.of(firstRunway, secondRunway).filter(runway -> runway.getPlane().isCrashed()).collect(Collectors.toList());
            if (crashes.size() > 0) {
                this.triggerPlaneCrashAlarm(crashes);
            }
            if (!firstPlane.isCrashed())
                System.out.printf("Tower: Plane <%s> has landed on runway <%s> %n", firstPlane.getShortPlaneIdentifier(), firstRunway.getId());
            if (!secondPlane.isCrashed())
                System.out.printf("Tower: Plane <%s> has landed on runway <%s> %n", secondPlane.getShortPlaneIdentifier(), secondRunway.getId());
        } else { // Handle the situation if only one plane tried to land.
            handleLandingResultForPlane(firstPlane, firstRunway);
            handleLandingResultForPlane(secondPlane, secondRunway);
        }

        // React to a empty list of planes.
        if (this.planes.size() == 0) {
            if (!this.isNoMorePlanesWantToLand()) {
                this.noMorePlanesWantToLand = true;
                System.out.printf("Tower: No more planes want to land %n");
            }
        }
    }

    /**
     * Register a fire station at the tower and handle all necessary registrations.
     *
     * @param fireStation The fire station which should be registered.
     * @param runway      The runway, which the fire station should protect.
     */
    public void registerFireStation(FireStation fireStation, Runway runway) {
        // The fire station can not already be registered and the fire station can only be registered for a runway,
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

    /**
     * Register a plane at the tower.
     *
     * @param plane The plane which should be registered.
     */
    public void registerLandingPlane(Plane plane) {
        if (!this.planes.contains(plane)) {
            this.planes.add(plane);
        }
    }

    /**
     * Register a runway at the tower.
     *
     * @param runway The runway which should be registerd.
     */
    public void registerRunway(Runway runway) {
        if (!this.runways.contains(runway)) {
            this.runways.add(runway);
            System.out.printf("Tower: Runway <%s> was registered %n", runway.getId());
        }
    }

    /**
     * Add a listener to the plane crash alarm button.
     *
     * @param listener The listener.
     */
    public void addPlaneCrashAlarmButtonListener(IPlaneCrashAlarmButtonListener listener) {
        if (!this.planeCrashAlarmButtonListeners.contains(listener)) {
            this.planeCrashAlarmButtonListeners.add(listener);
        }
    }

    /**
     * Remove a listener from the plane crash alarm button.
     *
     * @param listener The listener to remove.
     */
    public void removePlaneCrashAlarmButtonListener(IPlaneCrashAlarmButtonListener listener) {
        if (this.planeCrashAlarmButtonListeners.contains(listener)) {
            this.planeCrashAlarmButtonListeners.remove(listener);
        }
    }

    /**
     * Call this method if an disaster occurred.
     *
     * @param location The location of the disaster.
     * @param message  Reason for the disaster.
     */
    public void disasterOccurred(String location, String message) {
        System.out.printf("Tower: Disaster occurred at %s! Message: %s %n", location, message);
    }
    /**
     * Handle the result of a plane landing.
     *
     * @param plane The plane which landed, if it landed.
     * @param runway The runway where it should be landed.
     */
    private void handleLandingResultForPlane(Plane plane, Runway runway) {
        if (plane != null) { // Can be null, if no more plane was available to land.
            if (plane.isCrashed()) {
                this.triggerPlaneCrashAlarm(Collections.singletonList(runway));
            } else {
                if (!plane.isCrashed())
                    System.out.printf("Tower: Plane <%s> is landed %n", plane.getShortPlaneIdentifier());
            }
        }
    }

    /**
     * Trigger the plane crash alarm.
     *
     * @param runways The runways with crashed planes.
     */
    private void triggerPlaneCrashAlarm(List<Runway> runways) {
        runways.forEach(runway -> System.out.printf("Tower: Plane <%s> is crashed on runway <%s> %n", runway.getPlane().getShortPlaneIdentifier(), runway.getId()));
        for (IPlaneCrashAlarmButtonListener listener : this.planeCrashAlarmButtonListeners) {
            listener.planesCrashed(runways);
        }
    }
}
