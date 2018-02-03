package t53landingPlane.Plane;

public class SetAllFlapsToThreeDegreeCommand implements ISetAllFlapsCommand {
    private ControlUnit controlUnit;

    public SetAllFlapsToThreeDegreeCommand(ControlUnit controlUnit) {
        this.controlUnit = controlUnit;
    }

    public void setAllFlaps() {
        this.controlUnit.moveAllFlaps(3);
    }
}
