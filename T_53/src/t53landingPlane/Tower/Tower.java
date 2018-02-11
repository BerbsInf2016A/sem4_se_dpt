package t53landingPlane.Tower;

import t53landingPlane.Plane.Plane;

/**
 * The Class representing the Airport Control Tower.
 */
public class Tower implements IPlanePositionDataListener {
    /**
     * The Descend Command.
     */
    private IDescendCommand command;
    /**
     * The current plane that needs to land.
     */
    private Plane currentPlane;

    /**
     * Boolean indicating if the descend command has already been send.
     */
    private boolean descendCommandIsSent = false;

    /**
     * Set the current plane.
     *
     * @param currentPlane The current plane.
     */
    public void setCurrentPlane(Plane currentPlane) {
        this.currentPlane = currentPlane;
        this.command = new DescendCommand(this.currentPlane);
    }

    /**
     * Updates the position data of the plane.
     *
     * @param speed    The speed of the plane.
     * @param height   The height of the plane.
     * @param distance The distance of the plane.
     * @param id       The id of the plane.
     */
    public void positionDataUpdate(double speed, double height, double distance, String id) {
        if (this.isDescendPointReached(height, distance) && !this.descendCommandIsSent) {
            System.out.printf("Plane is descending - Distance: %.1f & Height: %.1f%n", distance, height);
            this.sendDescendCommand();
            this.descendCommandIsSent = true;
        }
    }

    /**
     * Calculates if the point is reached, when the plane needs to descend.
     *
     * @param height   The height of the plane.
     * @param distance The distance of the plane.
     * @return Boolean indicating if the descend point is reached.
     */
    public boolean isDescendPointReached(double height, double distance) {
        final double degreesNeededToDescend = 3;
        if (height / distance >= Math.sin(Math.toRadians(degreesNeededToDescend))) {
            return true;
        }
        return false;
    }

    /**
     * Sends the descend command to the plane.
     */
    public void sendDescendCommand() {
        System.out.println("Sending Descend Command from Tower!");
        this.command.execute();
    }
}
