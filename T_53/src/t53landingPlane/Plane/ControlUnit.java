package t53landingPlane.Plane;

import t53landingPlane.Configuration;
import t53landingPlane.PositionUpdateTimerTask;
import t53landingPlane.Tower.IPlanePositionDataListener;

import java.util.ArrayList;
import java.util.Timer;

public class ControlUnit implements IControlUnit {

    private Timer timer;

    private Wing leftWing;
    private ArrayList<IPlanePositionDataListener> planePositionDataListeners;
    private Wing rightWing;

    private String planeId;
    private int planeSpeed;
    private int planeHeight;
    private int planeDistance;


    private boolean isDescending;

    public ControlUnit() {
        this.planePositionDataListeners = new ArrayList<>();
        this.registerLeftWing(new Wing(this));
        this.registerRightWing(new Wing(this));

        this.planeSpeed = Configuration.instance.planeSpeed;
        this.planeHeight = Configuration.instance.planeHeight;
        this.planeDistance = Configuration.instance.planeDistance;
        this.planeId = Configuration.instance.planeId;
        this.isDescending = false;

        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new PositionUpdateTimerTask(this), 0, Configuration.instance.updateIntervalInMilliseconds);
    }

    public void addPlanePositionDataListener(IPlanePositionDataListener planePositionDataListener) {
        this.planePositionDataListeners.add(planePositionDataListener);
    }

    public void moveAllFlaps(double degree) {
        leftWing.moveAllWingFlaps(degree);
        System.out.println("Moving Left Plane Wing Flaps!");
        rightWing.moveAllWingFlaps(degree);
        System.out.println("Moving Right Plane Wing Flaps!");

        if (degree > 0 ) {
            this.isDescending = true;
        } else {
            this.isDescending = false;
        }
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
        this.calculatePositionData();
        for(IPlanePositionDataListener planePositionDataListener : this.planePositionDataListeners) {
            planePositionDataListener.positionDataUpdate(this.planeSpeed, this.planeHeight, this.planeDistance, this.planeId);
        }
    }

    private void calculatePositionData() {
        this.planeDistance = this.planeDistance - (int)(this.planeSpeed * (Configuration.instance.updateIntervalInMilliseconds / 1000.0));
        if(this.isDescending) {
            this.planeHeight = (int)(this.planeDistance * Math.sin(Math.toRadians(3)));
            System.out.print("Plane is landing ");
        }
        System.out.println("Distance: " + this.planeDistance + " & Height: " + this.planeHeight);

        if(this.planeDistance <= 0) {
            this.timer.cancel();
            System.out.println("Plane successfully landed!");
        }

    }
}
