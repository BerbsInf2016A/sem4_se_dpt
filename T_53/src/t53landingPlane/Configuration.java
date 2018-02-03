package t53landingPlane;

public enum Configuration {
    instance;

    public String planeId = "1";
    public int planeHeight = 100;
    public int planeSpeed = 200;
    public int planeDistance = 5000;

    public int updateIntervalInMilliseconds = 1000;
}
