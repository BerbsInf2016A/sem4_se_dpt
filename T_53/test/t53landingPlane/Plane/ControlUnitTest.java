package t53landingPlane.Plane;

import org.junit.Assert;
import org.junit.Test;
import t53landingPlane.Configuration;
import t53landingPlane.Tower.IPlanePositionDataListener;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class ControlUnitTest {

    @Test
    public void ControlUnit_CheckingIfTheObserverListenerIsAdded() {
        ControlUnit controlUnit = new ControlUnit();
        MockPlanePositionDataListener planePositionDataListener = new MockPlanePositionDataListener();

        controlUnit.addPlanePositionDataListener(planePositionDataListener);
        controlUnit.updatePositionData();

        final double expectedSpeed = Configuration.instance.planeSpeed;
        final double expectedDistance = Configuration.instance.planeDistance - (expectedSpeed * (Configuration.instance.planePositionUpdateInterval /1000.0));
        final double expectedHeight = Configuration.instance.planeHeight;

        assertEquals("Should have the speed value from the Configuration!", expectedSpeed, planePositionDataListener.getSpeed(), 1);
        assertEquals("Should have the height value from the Configuration!", expectedHeight, planePositionDataListener.getHeight(), 1);
        assertEquals("Should have the distance value from the Configuration with a deficit because of the distance calculation!", expectedDistance, planePositionDataListener.getDistance(), 1);
    }

    @Test
    public void ControlUnit_CheckingIfTheObserverListenerIsRemoved() {
        IControlUnit controlUnit = new ControlUnit();
        MockPlanePositionDataListener planePositionDataListener = new MockPlanePositionDataListener();

        controlUnit.addPlanePositionDataListener(planePositionDataListener);
        controlUnit.removePlanePositionDataListener(planePositionDataListener);

        Assert.assertTrue("Should have not been set because the listener was removed!", planePositionDataListener.getSpeed() == 0);
        Assert.assertTrue("Should have not been set because the listener was removed!", planePositionDataListener.getHeight()== 0);
        Assert.assertTrue("Should have not been set because the listener was removed!", planePositionDataListener.getDistance() == 0);
    }

    @Test
    public void ControlUnit_moveAllFlaps() {
        ControlUnit controlUnit = new ControlUnit();
        final double expectedAngle = 17;

        controlUnit.registerLeftWing(new Wing(controlUnit));
        controlUnit.registerRightWing(new Wing(controlUnit));

        controlUnit.moveAllFlaps(17);

        assertEquals("Left Wing should have moved to the expected Angle!", expectedAngle, controlUnit.getLeftWing().getLeftFlap().getCurrentAngle(), 0.001);
        assertEquals("Left Wing should have moved to the expected Angle!", expectedAngle, controlUnit.getLeftWing().getRightFlap().getCurrentAngle(), 0.001);
        assertEquals("Right Wing should have moved to the expected Angle!", expectedAngle, controlUnit.getRightWing().getLeftFlap().getCurrentAngle(), 0.001);
        assertEquals("Right Wing should have moved to the expected Angle!", expectedAngle, controlUnit.getRightWing().getRightFlap().getCurrentAngle(), 0.001);
    }

    private class MockPlanePositionDataListener implements IPlanePositionDataListener {
        private double distance = 0;
        private double speed = 0;
        private double height = 0;

        public double getDistance() {
            return distance;
        }

        public double getSpeed() {
            return speed;
        }

        public double getHeight() {
            return height;
        }

        public void positionDataUpdate(double speed, double height, double distance, String id) {
            this.speed = speed;
            this.height = height;
            this.distance = distance;
        }
    }
}