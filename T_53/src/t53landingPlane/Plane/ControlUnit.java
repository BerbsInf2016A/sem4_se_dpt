package t53landingPlane.Plane;

import t53landingPlane.Configuration;
import t53landingPlane.ConsoleOutputTimerTask;
import t53landingPlane.PositionUpdateTimerTask;
import t53landingPlane.Tower.IPlanePositionDataListener;

import java.util.ArrayList;
import java.util.Timer;

/**
 * Class representing the Control Unit of the plane.
 */
public class ControlUnit implements IControlUnit {
    /**
     * The timer.
     */
    private Timer timer;
    /**
     * The left wing.
     */
    private Wing leftWing;
    /**
     * The plane position data listeners.
     */
    private ArrayList<IPlanePositionDataListener> planePositionDataListeners;
    /**
     * The right wing.
     */
    private Wing rightWing;

    /**
     * The plane id.
     */
    private String planeId;
    /**
     * The plane speed.
     */
    private double planeSpeed;
    /**
     * The plane height.
     */
    private double planeHeight;
    /**
     * The plane distance.
     */
    private double planeDistance;
    /**
     * Boolean indicating if the plane is descending.
     */
    private boolean isDescending;

    /**
     * Constructor for the control unit.
     */
    public ControlUnit() {
        this.planePositionDataListeners = new ArrayList<>();

        this.planeSpeed = Configuration.instance.planeSpeed;
        this.planeHeight = Configuration.instance.planeHeight;
        this.planeDistance = Configuration.instance.planeDistance;
        this.planeId = Configuration.instance.planeId;
        this.isDescending = false;

        this.timer = new Timer();

        this.timer.scheduleAtFixedRate(new ConsoleOutputTimerTask(this), 0, Configuration.instance.consoleInformationUpdateInterval);
        this.timer.scheduleAtFixedRate(new PositionUpdateTimerTask(this), 0, Configuration.instance.planePositionUpdateInterval);
    }

    /**
     * Get the left wing.
     *
     * @return The left wing.
     */
    public Wing getLeftWing() {
        return leftWing;
    }

    /**
     * Get the right wing.
     *
     * @return The right wing.
     */
    public Wing getRightWing() {
        return rightWing;
    }

    /**
     * Adds a plane position data listener.
     *
     * @param planePositionDataListener The plane position data listener to add.
     */
    public void addPlanePositionDataListener(IPlanePositionDataListener planePositionDataListener) {
        this.planePositionDataListeners.add(planePositionDataListener);
    }

    /**
     * Moves all flaps of the plane.
     *
     * @param degree The degree to move the flaps.
     */
    public void moveAllFlaps(double degree) {
        leftWing.moveAllWingFlaps(degree);
        rightWing.moveAllWingFlaps(degree);
        System.out.println("Moving Wing Flaps!");
        this.isDescending = degree > 0;
    }

    /**
     * Registers the left wing.
     *
     * @param wing The left wing.
     */
    public void registerLeftWing(Wing wing) {
        this.leftWing = wing;
    }

    /**
     * Registers the right wing.
     *
     * @param wing The right wing.
     */
    public void registerRightWing(Wing wing) {
        this.rightWing = wing;
    }

    /**
     * Removes a plane position data listener
     *
     * @param planePositionDataListener The plane position data listener to remove.
     */
    public void removePlanePositionDataListener(IPlanePositionDataListener planePositionDataListener) {
        this.planePositionDataListeners.remove(planePositionDataListener);
    }

    /**
     * Sends an update of the position data to the listeners.
     */
    public void updatePositionData() {
        this.calculatePositionData();
        for (IPlanePositionDataListener planePositionDataListener : this.planePositionDataListeners) {
            planePositionDataListener.positionDataUpdate(this.planeSpeed, this.planeHeight, this.planeDistance, this.planeId);
        }
    }

    /**
     * Calculates the plane position data.
     */
    private void calculatePositionData() {
        this.planeDistance = this.planeDistance - (this.planeSpeed * (Configuration.instance.planePositionUpdateInterval / 1000.0));
        if(this.isDescending) {
            this.planeHeight = this.planeDistance * Math.sin(Math.toRadians(3));
        }

        if (this.planeDistance <= 0) {
            this.timer.cancel();
            System.out.println("Plane successfully landed!");
        }
    }

    /**
     * Outputs the plane data to the console.
     */
    public void outputInfoToConsole() {
        System.out.printf("Plane Distance: %.1f & Height: %.1f%n", this.planeDistance, this.planeHeight);
    }
}
