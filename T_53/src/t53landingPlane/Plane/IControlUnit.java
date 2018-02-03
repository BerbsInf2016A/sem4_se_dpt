package t53landingPlane.Plane;

import t53landingPlane.Plane.Wing;

public interface IControlUnit {
    void moveAllFlaps(double degree);
    void registerLeftWing(Wing wing);
    void registerRightWing(Wing wing);
}
