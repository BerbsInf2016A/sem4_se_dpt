package t53landingPlane.Tower;

import t53landingPlane.Configuration;
import t53landingPlane.Plane.Plane;

public class Tower implements IPlanePositionDataListener {
    private IDescendCommand command;
    private Plane currentPlane;

    public void setCurrentPlane(Plane currentPlane) {
        this.currentPlane = currentPlane;
    }

    public void setCommand(IDescendCommand command) {
        this.command = command;
    }

    public void positionDataUpdate(double speed, double height, double distance, String id) {
        while(!this.isDescendPointIsReached(height, distance)) {
            try {
                System.out.println("Plane is " + distance + "m away from the airport!");
                distance = distance - (speed * (Configuration.instance.updateIntervalInMilliseconds / 1000));
                Thread.sleep(Configuration.instance.updateIntervalInMilliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.sendDescendCommand();
    }

    private boolean isDescendPointIsReached(double height, double distance) {
        final double degreesNeededToDescend = 3;

        if(height/distance >= Math.sin(Math.toRadians(degreesNeededToDescend))) {
            return true;
        }
        return false;
    }

    public void sendDescendCommand() {
        System.out.println("Descending command send from Tower!");
        this.command.execute();
    }
}
