package t52airport;

/**
 * A command to initiate the landing of a plane on a runway.
 */
public interface ILandingCommand {
    /**
     * Initiate the landing process and inform the runway and the plane of each other.
     *
     * @param runway The runway which should be used to land.
     * @param plane The plane which is allowed to land.
     */
    void initiateLandingPermission(Runway runway, Plane plane);
}
