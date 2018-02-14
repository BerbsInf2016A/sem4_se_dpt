package t52airport;

import org.junit.Assert;
import org.junit.Test;
import t52airport.helpers.Configuration;

import java.util.concurrent.TimeUnit;

public class FireTruckTest {

    /**
     * The fire truck should be blocked for a short duration after it received the move out command.
     */
    @Test
    public void fireTruck_moveOut() {
        FireTruck testee = new FireTruck("TestFireTruck", "FireStation01");

        ITower tower = new Tower();
        Runway runway = new Runway(tower, "Testrunway");
        Plane plane = new Plane(tower, "Plane01", PlaneType.A320);
        runway.initiateLandingPermission(runway, plane);

        testee.moveOut(runway);
        Assert.assertTrue("Firetruck should be in use, after the moveout command", testee.isInUse());
        try {
            TimeUnit.MILLISECONDS.sleep(Configuration.instance.fireTruckInterventionDurationInMilliSeconds * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertFalse("Firetruck should not be in use after the mission.", testee.isInUse());
    }
}