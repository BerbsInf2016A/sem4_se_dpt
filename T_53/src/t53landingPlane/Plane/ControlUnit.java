package t53landingPlane.Plane;

import t53landingPlane.Configuration;
import t53landingPlane.Tower.IPlanePositionDataListener;

import java.util.ArrayList;

public class ControlUnit implements IControlUnit {

    private Wing leftWing;
    private ArrayList<IPlanePositionDataListener> planePositionDataListeners;
    private Wing rightWing;

    public ControlUnit() {
        this.planePositionDataListeners = new ArrayList<>();
        this.registerLeftWing(new Wing(this));
        this.registerRightWing(new Wing(this));
    }

    public void addPlanePositionDataListener(IPlanePositionDataListener planePositionDataListener) {
        this.planePositionDataListeners.add(planePositionDataListener);
    }

    public void moveAllFlaps(double degree) {
        leftWing.moveAllWingFlaps(degree);
        System.out.println("Moving left Wing Flaps!");
        rightWing.moveAllWingFlaps(degree);
        System.out.println("Moving right Wing Flaps!");
    }

    public void registerLeftWing(Wing wing) {
        this.leftWing = wing;
    }

    public void registerRightWing(Wing wing) {
        this.rightWing = wing;
    }

    public void removePlanePositionDataListener(IPlanePositionDataListener planePositionDataListener) {
        this.planePositionDataListeners.remove(planePositionDataListener);
    }

    public void updatePositionData() {
        System.out.println("Plane send position data!");
        for(IPlanePositionDataListener planePositionDataListener : planePositionDataListeners) {
            planePositionDataListener.positionDataUpdate(Configuration.instance.planeSpeed, Configuration.instance.planeHeight, Configuration.instance.planeDistance, Configuration.instance.planeId);
        }
    }
}
