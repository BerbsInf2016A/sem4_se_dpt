package t53landingPlane.Plane;

/**
 * Class for the Set All Flaps To Three Degree command that needs to be send to the Control Unit.
 */
public class SetAllFlapsToThreeDegreeCommand implements ISetAllFlapsCommand {
    /**
     * The Control Unit.
     */
    private IControlUnit controlUnit;

    /**
     * The Set All Flaps To Three Degree command.
     * @param controlUnit The control unit.
     */
    public SetAllFlapsToThreeDegreeCommand(IControlUnit controlUnit) {
        this.controlUnit = controlUnit;
    }

    /**
     * Executes the command.
     */
    public void setAllFlaps() {
        this.controlUnit.moveAllFlaps(3);
    }
}
