package t53landingPlane.Tower;

/**
 * Interface for the Plane Position Data Listener
 */
public interface IPlanePositionDataListener {
    /**
     * Updates the position data of the plane.
     * @param speed The speed of the plane.
     * @param height The height of the plane.
     * @param distance The distance of the plane.
     * @param id The id of the plane.
     */
    void positionDataUpdate(double speed, double height, double distance, String id);
}
