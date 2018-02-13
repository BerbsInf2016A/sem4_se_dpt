package t53landingPlane.Tower;

import t53landingPlane.Plane.Plane;

/**
 * Class for the descend command that needs to be send to the plane.
 */
public class DescendCommand implements IDescendCommand {
    /**
     * The plane.
     */
    private Plane plane;

    /**
     * The descend command.
     *
     * @param plane The plane.
     */
    public DescendCommand(Plane plane) {
        this.plane = plane;
    }

    /**
     * Executes the command.
     */
    public void execute() {
        this.plane.descend();
    }
}
