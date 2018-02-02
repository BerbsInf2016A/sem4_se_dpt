package t52airport;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TowerTest {

    @Test
    public void Tower_registerFireStation_KnownRunway() {
        Tower testee = new Tower();

        Runway runway = new Runway(testee);
        testee.registerRunway(runway);
        FireStation fireStation = new FireStation(testee);

        testee.registerFireStation(fireStation, runway);


        List<FireStation> fireStationList = testee.getFireStations();
        Assert.assertEquals("One firestation should be added", 1, fireStationList.size());
    }


    @Test
    public void Tower_registerFireStation_UnknownRunway() {
        Tower testee = new Tower();

        Runway runway = new Runway(testee);

        FireStation fireStation = new FireStation(testee);

        testee.registerFireStation(fireStation, runway);

        List<FireStation> fireStationList = testee.getFireStations();
        Assert.assertEquals("Firestation should not be added", 0, fireStationList.size());
    }
    @Test
    public void Tower_registerLandingPlane() {
        Tower testee = new Tower();
        Plane plane = new Plane(testee);

        testee.registerLandingPlane(plane);

        List<Plane> planeList = testee.getPlanes();
        Assert.assertEquals("One plane should be added", 1, planeList.size());
    }

    @Test
    public void Tower_registerRunway() {
    }
}