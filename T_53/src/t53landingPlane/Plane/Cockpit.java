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
    private IControlUnit controlUnit;

    /**
     * Constructor for the cockpit.
     */
    public Cockpit() {
        this.controlUnit = new ControlUnit();
        this.controlUnit.registerLeftWing(new Wing(this.controlUnit));
        this.controlUnit.registerRightWing(new Wing(this.controlUnit));
    }

    /**
     * Get the control unit.
     * @return The control unit.
     */
    public IControlUnit getControlUnit() {
        return controlUnit;
    }

    /**
     * Get the command.
     * @return The command.
     */
    public ISetAllFlapsCommand getCommand() {
        return command;
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
