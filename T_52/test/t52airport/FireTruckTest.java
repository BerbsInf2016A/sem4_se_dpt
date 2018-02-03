package t52airport;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class FireTruckTest {

    @Test
    public void fireTruck_moveOut() {
        FireTruck testee = new FireTruck("TestFireTruck");

        ITower tower = new Tower();
        Runway runway = new Runway(tower, "Testrunway");

        testee.moveOut(runway);
        Assert.assertTrue("Firetruck should be in use, after the moveout command", testee.isInUse());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertFalse("Firetruck should not be in use after the mission.", testee.isInUse());
    }
}