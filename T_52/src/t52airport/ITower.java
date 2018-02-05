package t52airport;

public interface ITower {
    /**
     * Register a fire station at the tower and handle all necessary registrations.
     *
     * @param fireStation The fire station which should be registered.
     * @param runway      The runway, which the fire station should protect.
     */
    void registerFireStation(FireStation fireStation, Runway runway);

    /**
     * Register a plane at the tower.
     *
     * @param plane The plane which should be registered.
     */
    void registerLandingPlane(Plane plane);

    /**
     * Register a runway at the tower.
     *
     * @param runway The runway which should be registerd.
     */
    void registerRunway(Runway runway);


    /**
     * Call this method if an disaster occurred.
     *
     * @param location The location of the disaster.
     * @param message  Reason for the disaster.
     */
    void disasterOccurred(String location, String message);
}
