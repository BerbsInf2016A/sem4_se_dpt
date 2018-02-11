package t53landingPlane.Tower;

import org.junit.Assert;
import org.junit.Test;
import t53landingPlane.Plane.Plane;

public class TowerTest {

    @Test
    public void Tower_SendDescendCommand() {
        Tower tower = new Tower();
        Plane plane = new Plane();

        plane.getCockpit().getControlUnit().addPlanePositionDataListener(tower);
        tower.setCurrentPlane(plane);
        tower.sendDescendCommand();

        final int expectedDescendingAngle = 3;

        Assert.assertEquals("Left Wing Flaps should be at an angle of 3 degrees!", expectedDescendingAngle, (int) plane.getCockpit().getControlUnit().getLeftWing().getLeftFlap().getCurrentAngle());
        Assert.assertEquals("Left Wing Flaps should be at an angle of 3 degrees!", expectedDescendingAngle, (int) plane.getCockpit().getControlUnit().getLeftWing().getRightFlap().getCurrentAngle());
        Assert.assertEquals("Right Wing Flaps should be at an angle of 3 degrees!", expectedDescendingAngle, (int) plane.getCockpit().getControlUnit().getRightWing().getLeftFlap().getCurrentAngle());
        Assert.assertEquals("Right Wing Flaps should be at an angle of 3 degrees!", expectedDescendingAngle, (int) plane.getCockpit().getControlUnit().getRightWing().getRightFlap().getCurrentAngle());

    }

    @Test
    public void Tower_DescendingPointCalculation() {
        Tower tower = new Tower();

        Assert.assertTrue("Descending point should be reached!", tower.isDescendPointReached(100, 1800));
        Assert.assertTrue("Descending point should be reached!", tower.isDescendPointReached(200, 3600));
        Assert.assertFalse("Descending point should not be reached!", tower.isDescendPointReached(100, 3000));
    }


}