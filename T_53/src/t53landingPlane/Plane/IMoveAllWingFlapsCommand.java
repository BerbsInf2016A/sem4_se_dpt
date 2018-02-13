package t53landingPlane.Plane;

/**
 * Interface representing move all wing flaps command of a wing.
 */
public interface IMoveAllWingFlapsCommand {
    /**
     * Moves all flaps of the wing to a certain degree.
     * @param degree The degree to move the flaps.
     */
    void moveAllWingFlaps(double degree);
}
