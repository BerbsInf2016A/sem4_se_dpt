package t53landingPlane;

import t53landingPlane.Plane.Plane;
import t53landingPlane.Tower.DescendCommand;
import t53landingPlane.Tower.Tower;

public class Simulation {
    public void run(){
        Tower tower = new Tower();
        Plane plane = new Plane();

        plane.getCockpit().getControlUnit().addPlanePositionDataListener(tower);

        tower.setCommand(new DescendCommand(plane));
    }
}
