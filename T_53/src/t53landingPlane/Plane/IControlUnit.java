package t53landingPlane.Plane;

import t53landingPlane.Tower.IPlanePositionDataListener;

/**
 * Interface representing the Control Unit of a plane.
 */
public interface IControlUnit {
    /**
     * Moves all flaps of the plane.
     * @param degree The degree to move the flaps.
     */
    void moveAllFlaps(double degree);
    /**
     * Registers the left wing.
     * @param wing The left wing.
     */
    void registerLeftWing(Wing wing);
    /**
     * Registers the right wing.
     * @param wing The right wing.
     */
    void registerRightWing(Wing wing);
    /**
     * Adds a plane position data listener.
     * @param planePositionDataListener The plane position data listener to add.
     */
    void addPlanePositionDataListener(IPlanePositionDataListener planePositionDataListener);
    /**
     * Removes a plane position data listener
     * @param planePositionDataListener The plane position data listener to remove.
     */
    void removePlanePositionDataListener(IPlanePositionDataListener planePositionDataListener);
}
