package t53landingPlane.Plane;

/**
 * Class representing the cockpit of the plane.
 */
public class Cockpit {
    /**
     * The set all flaps command.
     */
    private ISetAllFlapsCommand command;
    /**
     * The control unit.
     */
    private ControlUnit controlUnit;

    /**
     * Constructor for the cockpit.
     */
    public Cockpit() {
        this.controlUnit = new ControlUnit();
    }

    /**
     * Get the control unit.
     * @return
     */
    public ControlUnit getControlUnit() {
        return controlUnit;
    }

    /**
     * Sets the set all flaps command.
     * @param command The set all flaps command.
     */
    public void setCommand(ISetAllFlapsCommand command) {
        this.command = command;
    }

    /**
     * Initiates the descending procedure.
     */
    public void descend() {
        setCommand(new SetAllFlapsToThreeDegreeCommand(this.controlUnit));
        this.command.setAllFlaps();
    }
}
