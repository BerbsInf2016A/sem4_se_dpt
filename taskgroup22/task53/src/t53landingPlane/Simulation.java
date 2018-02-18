package t53landingPlane;

import t53landingPlane.Plane.Plane;
import t53landingPlane.Tower.Tower;

/**
 * The Simulation for the plane landing.
 */
public class Simulation {
    public void run() {
        Tower tower = new Tower();
        Plane plane = new Plane();

        plane.getCockpit().getControlUnit().addPlanePositionDataListener(tower);
        tower.setCurrentPlane(plane);
    }
}
