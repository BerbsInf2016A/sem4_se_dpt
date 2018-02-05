package t53landingPlane.Plane;

public interface IControlUnit {
    void moveAllFlaps(double degree);
    void registerLeftWing(Wing wing);
    void registerRightWing(Wing wing);
}
