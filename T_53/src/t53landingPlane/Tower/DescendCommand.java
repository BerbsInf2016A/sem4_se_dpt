package t53landingPlane.Tower;

import t53landingPlane.Plane.Plane;

public class DescendCommand implements IDescendCommand {
    private Plane plane;

    public DescendCommand(Plane plane) {
        this.plane = plane;
    }

    public void execute() {
        this.plane.descend();
    }
}
