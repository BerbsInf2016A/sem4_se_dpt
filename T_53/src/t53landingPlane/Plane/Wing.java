package t53landingPlane.Plane;

/**
 * Class representing the wing of a plane.
 */
public class Wing implements IMoveAllWingFlapsCommand {
    /**
     * The left flap of the wing.
     */
    private Flap leftFlap;
    /**
     * The right flap of the wing.
     */
    private Flap rightFlap;

    /**
     * Constructor for the Wing.
     */
    public Wing() {
        this.leftFlap = new Flap();
        this.rightFlap = new Flap();
    }

    /**
     * Get the left flap.
     *
     * @return The left flap.
     */
    public Flap getLeftFlap() {
        return leftFlap;
    }

    /**
     * Get the right flap.
     *
     * @return The right flap.
     */
    public Flap getRightFlap() {
        return rightFlap;
    }

    /**
     * Moves all flaps of the wing to a certain degree.
     *
     * @param degree The degree to move the flaps.
     */
    public void moveAllWingFlaps(double degree) {
        moveFlaps(degree);
    }

    /**
     * Moves the flaps.
     *
     * @param degree The degree to move the flaps.
     */
    private void moveFlaps(double degree) {
        moveLeftFlap(degree);
        moveRightFlap(degree);
    }

    /**
     * Moves the left flap of the wing.
     *
     * @param degree The degree to move the flap.
     */
    public void moveLeftFlap(double degree) {
        leftFlap.moveToDegree(degree);
    }

    /**
     * Moves the right flap of the wing.
     *
     * @param degree The degree to move the flap.
     */
    public void moveRightFlap(double degree) {
        rightFlap.moveToDegree(degree);
    }

}
