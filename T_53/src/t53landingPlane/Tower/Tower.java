package t53landingPlane.Tower;

import t53landingPlane.Plane.Plane;

public class Tower implements IPlanePositionDataListener {
    private IDescendCommand command;
    private Plane currentPlane;

    private boolean descendCommandSent = false;

    public void setCurrentPlane(Plane currentPlane) {
        this.currentPlane = currentPlane;
    }

    public void setCommand(IDescendCommand command) {
        this.command = command;
    }

    public void positionDataUpdate(double speed, double height, double distance, String id) {
        if(this.isDescendPointIsReached(height, distance)) {
            this.sendDescendCommand();
            this.descendCommandSent = true;
        }
    }

    private boolean isDescendPointIsReached(double height, double distance) {
        final double degreesNeededToDescend = 3;
        if(height/distance >= Math.sin(Math.toRadians(degreesNeededToDescend))) {
            return true;
        }
        return false;
    }

    public void sendDescendCommand() {
        if(!this.descendCommandSent) {
            System.out.println("Sending Descending Command from Tower!");
            this.command.execute();
        }
    }
}
