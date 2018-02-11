package t53landingPlane.Tower;

import org.junit.Assert;
import org.junit.Test;
import t53landingPlane.Plane.Plane;
import t53landingPlane.Plane.SetAllFlapsToThreeDegreeCommand;

public class TowerTest {

    @Test
    public void Tower_SendDescendCommand() {
        Tower tower = new Tower();
        Plane plane = new Plane();

        plane.getCockpit().getControlUnit().addPlanePositionDataListener(tower);
        tower.setCurrentPlane(plane);
        tower.sendDescendCommand();
        
        Assert.assertTrue("Command should be set to move flaps!", plane.getCockpit().getCommand() instanceof SetAllFlapsToThreeDegreeCommand);
    }

    @Test
    public void Tower_DescendingPointCalculation() {
        Tower tower = new Tower();

        Assert.assertTrue("Descending point should be reached!", tower.isDescendPointReached(100, 1800));
        Assert.assertTrue("Descending point should be reached!", tower.isDescendPointReached(200, 3600));
        Assert.assertFalse("Descending point should not be reached!", tower.isDescendPointReached(100, 3000));
    }


}