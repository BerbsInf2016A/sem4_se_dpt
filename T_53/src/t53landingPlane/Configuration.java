package t53landingPlane;

public enum Configuration {
    instance;

    public String planeId = "1";
    public double planeHeight = 100;
    public double planeSpeed = 200;
    public double planeDistance = 5000;

    public int updateIntervalInMilliseconds = 1;
    public int updateIntervalFocConsoleOutputInMilliseconds = 1000;

}
