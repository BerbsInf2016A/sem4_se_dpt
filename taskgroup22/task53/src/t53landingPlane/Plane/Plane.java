package t53landingPlane.Plane;

/**
 * Class representing the plane.
 */
public class Plane {
    /**
     * The cockpit.
     */
    private Cockpit cockpit;

    /**
     * Constructor for the plane.
     */
    public Plane() {
        this.cockpit = new Cockpit();
    }

    /**
     * The descend command.
     */
    public void descend() {
        this.cockpit.descend();
    }

    /**
     * Get the cockpit.
     *
     * @return The cockpit.
     */
    public Cockpit getCockpit() {
        return cockpit;
    }
}
