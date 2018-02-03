package t53landingPlane.Tower;

public interface IPlanePositionDataListener {
    void positionDataUpdate(double speed, double height, double distance, String id);
}
