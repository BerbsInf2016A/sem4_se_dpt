package t52airport;

public interface ITower {
    /**
     * Register a fireStation for a runway at a tower.
     *
     * @param fireStation The firestation which should be registered.
     * @param runway The runway, which the firestation should protect.
     */
    public void registerFireStation(FireStation fireStation, Runway runway);

    /**
     * Register a plane at a Tower.
     *
     * @param plane The plane which should be registered.
     */
    public void registerLandingPlane(Plane plane);

    /**
     * Register a runway at a tower.
     *
     * @param runway The runway which should be registerd.
     */
    public void registerRunway(Runway runway);
}
