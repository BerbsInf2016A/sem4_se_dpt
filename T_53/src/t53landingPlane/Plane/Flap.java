package t53landingPlane.Plane;

/**
 * Class representing the flap of a wing.
 */
public class Flap {
    /**
     * The current angle of the flap.
     */
    private double currentAngle;

    /**
     * Moves the flap to a certain angle.
     *
     * @param degree The degree to move the flap.
     */
    public void moveToDegree(double degree) {
        currentAngle = degree;
    }

    /**
     * Get the current angle.
     *
     * @return The current angle.
     */
    public double getCurrentAngle() {
        return currentAngle;
    }
}
