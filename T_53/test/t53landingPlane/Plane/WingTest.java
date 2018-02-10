package t53landingPlane.Plane;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WingTest {

    @Test
    public void Wing_MoveLeftFlap() {
        Wing wing = new Wing();
        final double wingFlapAngle = 21;

        wing.moveLeftFlap(wingFlapAngle);

        Assert.assertEquals("Wing Flap Angle should be set!", wingFlapAngle, wing.getLeftFlap().getCurrentAngle(), 0.001);
    }

    @Test
    public void Wing_MoveRightFlap() {
        Wing wing = new Wing();
        final double wingFlapAngle = 21;

        wing.moveRightFlap(wingFlapAngle);

        Assert.assertEquals("Wing Flap Angle should be set!", wingFlapAngle, wing.getRightFlap().getCurrentAngle(),0.001);
    }

    @Test
    public void Wing_MoveAllWingFlaps() {
        Wing wing = new Wing();
        final double wingFlapAngle = 21;

        wing.moveAllWingFlaps(wingFlapAngle);

        Assert.assertEquals("Wing Flap Angle should be set!", wingFlapAngle, wing.getLeftFlap().getCurrentAngle(), 0.001);
        Assert.assertEquals("Wing Flap Angle should be set!", wingFlapAngle, wing.getRightFlap().getCurrentAngle(),0.001);
    }
}