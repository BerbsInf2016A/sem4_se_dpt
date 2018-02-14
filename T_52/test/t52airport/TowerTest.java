package t52airport;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class TowerTest {

    /**
     * Test the registration of a fire station for a already registered runway.
     */
    @Test
    public void Tower_registerFireStation_KnownRunway() {
        Tower testee = new Tower();

        Runway runway = new Runway(testee, "Testrunway");
        testee.registerRunway(runway);
        FireStation fireStation = new FireStation(testee, "TestFireStation");

        testee.registerFireStation(fireStation, runway);


        List<FireStation> fireStationList = testee.getFireStations();
        Assert.assertEquals("One fire station should be added", 1, fireStationList.size());
    }

    /**
     * Test the registration of a fire station for a unknown runway.
     */
    @Test
    public void Tower_registerFireStation_UnknownRunway() {
        Tower testee = new Tower();

        Runway runway = new Runway(testee, "Testrunway");

        FireStation fireStation = new FireStation(testee, "TestFireStation");

        testee.registerFireStation(fireStation, runway);

        List<FireStation> fireStationList = testee.getFireStations();
        Assert.assertEquals("Fire station should not be added", 0, fireStationList.size());
    }

    /**
     * Testing the registration of a plane.
     */
    @Test
    public void Tower_registerLandingPlane() {
        Tower testee = new Tower();
        Plane plane = new Plane(testee, "Testplane", PlaneType.A320);

        testee.registerLandingPlane(plane);

        List<Plane> planeList = testee.getPlanes();
        Assert.assertEquals("One plane should be added", 1, planeList.size());
    }

    /**
     * Testing the registration of a runway.
     */
    @Test
    public void Tower_registerRunway() {
        Tower testee = new Tower();

        Runway runway = new Runway(testee, "Testrunway01");
        testee.registerRunway(runway);

        runway = new Runway(testee, "Testrunway02");
        testee.registerRunway(runway);

        List<Runway> runways = testee.getRunways();
        Assert.assertEquals("Two runways should be added", 2, runways.size());
    }
}
