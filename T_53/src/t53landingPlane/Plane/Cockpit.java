package t53landingPlane.Plane;

public class Cockpit {
    private ISetAllFlapsCommand command;
    private ControlUnit controlUnit;

    public Cockpit() {
        this.controlUnit = new ControlUnit();
    }

    public ControlUnit getControlUnit() {
        return controlUnit;
    }

    public void setCommand(ISetAllFlapsCommand command) {
        this.command = command;
    }

    public void descend() {
        this.command = new SetAllFlapsToThreeDegreeCommand(this.controlUnit);
        this.command.setAllFlaps();
    }
}
