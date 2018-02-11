package t53landingPlane;

/**
 * Configuration for the Plane Landing Simulation.
 */
public enum Configuration {
    instance;

    /**
     * The id of the Plane.
     */
    public String planeId = "1";
    /**
     * The height that the Plane is flying.
     */
    public double planeHeight = 100;
    /**
     * The speed that the Plane is traveling.
     */
    public double planeSpeed = 200;
    /**
     * The distance from the plane to the airport runway.
     */
    public double planeDistance = 5000;

    /**
     * The update interval for the plane position in milliseconds.
     */
    public int planePositionUpdateInterval = 1;
    /**
     * The update interval for the console output in milliseconds.
     */
    public int consoleInformationUpdateInterval = 1000;

}
