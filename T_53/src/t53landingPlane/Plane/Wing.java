package t53landingPlane.Plane;

public class Wing implements IMoveAllWingFlapsCommand {
    private IControlUnit controlUnit;
    private Flap leftFlap;
    private Flap rightFlap;

    public Wing(IControlUnit controlUnit) {
        this.controlUnit = controlUnit;
        this.leftFlap = new Flap();
        this.rightFlap = new Flap();
    }

    public void moveAllWingFlaps(double degree) {
        moveFlaps(degree);
    }

    private void moveFlaps(double degree) {
        moveLeftFlap(degree);
        moveRightFlap(degree);
    }

    private void moveLeftFlap(double degree) {
        leftFlap.moveToDegree(degree);
    }

    private void moveRightFlap(double degree) {
        rightFlap.moveToDegree(degree);
    }

}
