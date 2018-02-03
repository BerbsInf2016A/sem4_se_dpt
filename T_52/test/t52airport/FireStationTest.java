package t52airport;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class FireStationTest {

    @Test
    public void planesCrashed_ownRunway_A319() {
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "Runway01");

        FireStation testee = new FireStation(tower, "TestFireStation01");
        testee.setRunway(runway);

        Plane crashedPlane = new Plane(tower, "Plane01", PlaneType.A319);
        runway.initiateLandingPermission(runway, crashedPlane);
        crashedPlane.setCrashed(true);

        testee.planesCrashed(Arrays.asList(runway));
        List<String> usedFireTruckIds =  testee.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());


        Assert.assertEquals("Two trucks should move out in case a A319 is crashed", 2, usedFireTruckIds.size());
        Assert.assertTrue("A319 V01 should move out", usedFireTruckIds.contains("V01"));
        Assert.assertTrue("A319 V02 should move out", usedFireTruckIds.contains("V02"));
    }

    @Test
    public void planesCrashed_ownRunway_A320() {
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "Runway01");

        FireStation testee = new FireStation(tower, "TestFireStation01");
        testee.setRunway(runway);

        Plane crashedPlane = new Plane(tower, "Plane01", PlaneType.A320);
        runway.initiateLandingPermission(runway, crashedPlane);
        crashedPlane.setCrashed(true);

        testee.planesCrashed(Arrays.asList(runway));
        List<String> usedFireTruckIds =  testee.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());


        Assert.assertEquals("Three trucks should move out in case a A320 is crashed", 3, usedFireTruckIds.size());
        Assert.assertTrue("A320 V01 should move out", usedFireTruckIds.contains("V01"));
        Assert.assertTrue("A320 V02 should move out", usedFireTruckIds.contains("V02"));
        Assert.assertTrue("A320 V02 should move out", usedFireTruckIds.contains("V03"));
    }

    @Test
    public void planesCrashed_ownRunway_B737() {
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "Runway01");

        FireStation testee = new FireStation(tower, "TestFireStation01");
        testee.setRunway(runway);

        Plane crashedPlane = new Plane(tower, "Plane01", PlaneType.B737);
        runway.initiateLandingPermission(runway, crashedPlane);
        crashedPlane.setCrashed(true);

        testee.planesCrashed(Arrays.asList(runway));
        List<String> usedFireTruckIds =  testee.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());


        Assert.assertEquals("Three trucks should move out in case a B737 is crashed", 3, usedFireTruckIds.size());
        Assert.assertTrue("B737 V01 should move out", usedFireTruckIds.contains("V01"));
        Assert.assertTrue("B737 V02 should move out", usedFireTruckIds.contains("V02"));
        Assert.assertTrue("B737 V02 should move out", usedFireTruckIds.contains("V03"));
    }

    @Test
    public void planesCrashed_ownRunway_A330() {
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "Runway01");

        FireStation testee = new FireStation(tower, "TestFireStation01");
        testee.setRunway(runway);

        Plane crashedPlane = new Plane(tower, "Plane01", PlaneType.A330);
        runway.initiateLandingPermission(runway, crashedPlane);
        crashedPlane.setCrashed(true);

        testee.planesCrashed(Arrays.asList(runway));
        List<String> usedFireTruckIds =  testee.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());

        Assert.assertEquals("Five trucks should move out in case a A330 is crashed", 5, usedFireTruckIds.size());
        Assert.assertTrue("A330 V01 should move out", usedFireTruckIds.contains("V01"));
        Assert.assertTrue("A330 V02 should move out", usedFireTruckIds.contains("V02"));
        Assert.assertTrue("A330 V02 should move out", usedFireTruckIds.contains("V03"));
        Assert.assertTrue("A330 V02 should move out", usedFireTruckIds.contains("V04"));
        Assert.assertTrue("A330 V02 should move out", usedFireTruckIds.contains("V05"));
    }

    @Test
    public void planesCrashed_ownRunway_A350() {
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "Runway01");
        Runway secondRunway = new Runway(tower, "Runway01");

        FireStation testee = new FireStation(tower, "TestFireStation01");
        testee.setRunway(runway);

        FireStation supportStation = new FireStation(tower, "TestFireStation02");
        supportStation.setRunway(secondRunway);


        Plane crashedPlane = new Plane(tower, "Plane01", PlaneType.A350);
        runway.initiateLandingPermission(runway, crashedPlane);
        crashedPlane.setCrashed(true);

        List<Runway> crashedPlanes =  Arrays.asList(runway);

        testee.planesCrashed(crashedPlanes);
        supportStation.planesCrashed(crashedPlanes);

        List<String> usedFireTruckIds =  testee.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());
        List<String> usedSupportFireTruckIds =  supportStation.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());

        Assert.assertEquals("Four trucks should move out in case a A350 is crashed on the own runway", 4, usedFireTruckIds.size());
        Assert.assertTrue("A350 V01 from own runway should move out", usedFireTruckIds.contains("V01"));
        Assert.assertTrue("A350 V02 from own runway should move out", usedFireTruckIds.contains("V02"));
        Assert.assertTrue("A350 V02 from own runway should move out", usedFireTruckIds.contains("V03"));
        Assert.assertTrue("A350 V06 from own runway should move out", usedFireTruckIds.contains("V06"));

        Assert.assertEquals("One truck should move out in case a A350 is crashed on the other runway", 1, usedSupportFireTruckIds.size());
        Assert.assertTrue("A350 V06 from support fire station runway should move out", usedSupportFireTruckIds.contains("V06"));
    }

    @Test
    public void planesCrashed_ownRunway_B787() {
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "Runway01");
        Runway secondRunway = new Runway(tower, "Runway01");

        FireStation testee = new FireStation(tower, "TestFireStation01");
        testee.setRunway(runway);

        FireStation supportStation = new FireStation(tower, "TestFireStation02");
        supportStation.setRunway(secondRunway);


        Plane crashedPlane = new Plane(tower, "Plane01", PlaneType.B787);
        runway.initiateLandingPermission(runway, crashedPlane);
        crashedPlane.setCrashed(true);

        List<Runway> crashedPlanes =  Arrays.asList(runway);

        testee.planesCrashed(crashedPlanes);
        supportStation.planesCrashed(crashedPlanes);

        List<String> usedFireTruckIds =  testee.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());
        List<String> usedSupportFireTruckIds =  supportStation.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());

        Assert.assertEquals("Four trucks should move out in case a B787 is crashed on the own runway", 4, usedFireTruckIds.size());
        Assert.assertTrue("B787 V01 from own runway should move out", usedFireTruckIds.contains("V01"));
        Assert.assertTrue("B787 V02 from own runway should move out", usedFireTruckIds.contains("V02"));
        Assert.assertTrue("B787 V02 from own runway should move out", usedFireTruckIds.contains("V03"));
        Assert.assertTrue("B787 V06 from own runway should move out", usedFireTruckIds.contains("V06"));

        Assert.assertEquals("One truck should move out in case a B787 is crashed on the other runway", 1, usedSupportFireTruckIds.size());
        Assert.assertTrue("B787 V06 from support fire station runway should move out", usedSupportFireTruckIds.contains("V06"));
    }

    @Test
    public void planesCrashed_ownRunway_A380() {
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "Runway01");
        Runway secondRunway = new Runway(tower, "Runway01");

        FireStation testee = new FireStation(tower, "TestFireStation01");
        testee.setRunway(runway);

        FireStation supportStation = new FireStation(tower, "TestFireStation02");
        supportStation.setRunway(secondRunway);


        Plane crashedPlane = new Plane(tower, "Plane01", PlaneType.A380);
        runway.initiateLandingPermission(runway, crashedPlane);
        crashedPlane.setCrashed(true);

        List<Runway> crashedPlanes =  Arrays.asList(runway);

        testee.planesCrashed(crashedPlanes);
        supportStation.planesCrashed(crashedPlanes);

        List<String> usedFireTruckIds =  testee.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());
        List<String> usedSupportFireTruckIds =  supportStation.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());

        Assert.assertEquals("Six trucks should move out in case a A380 is crashed on the own runway", 6, usedFireTruckIds.size());
        Assert.assertTrue("A380 V01 from own runway should move out", usedFireTruckIds.contains("V01"));
        Assert.assertTrue("A380 V02 from own runway should move out", usedFireTruckIds.contains("V02"));
        Assert.assertTrue("A380 V02 from own runway should move out", usedFireTruckIds.contains("V03"));
        Assert.assertTrue("A380 V06 from own runway should move out", usedFireTruckIds.contains("V06"));
        Assert.assertTrue("A380 V07 from own runway should move out", usedFireTruckIds.contains("V07"));
        Assert.assertTrue("A380 V08 from own runway should move out", usedFireTruckIds.contains("V08"));

        Assert.assertEquals("Two trucks should move out in case a A380 is crashed on the other runway", 2, usedSupportFireTruckIds.size());
        Assert.assertTrue("A380 V06 from support fire station runway should move out", usedSupportFireTruckIds.contains("V06"));
        Assert.assertTrue("A380 V08 from support fire station runway should move out", usedSupportFireTruckIds.contains("V08"));
    }

    @Test
    public void planesCrashed_ownRunway_B747() {
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "Runway01");
        Runway secondRunway = new Runway(tower, "Runway01");

        FireStation testee = new FireStation(tower, "TestFireStation01");
        testee.setRunway(runway);

        FireStation supportStation = new FireStation(tower, "TestFireStation02");
        supportStation.setRunway(secondRunway);


        Plane crashedPlane = new Plane(tower, "Plane01", PlaneType.B747);
        runway.initiateLandingPermission(runway, crashedPlane);
        crashedPlane.setCrashed(true);

        List<Runway> crashedPlanes =  Arrays.asList(runway);

        testee.planesCrashed(crashedPlanes);
        supportStation.planesCrashed(crashedPlanes);

        List<String> usedFireTruckIds =  testee.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());
        List<String> usedSupportFireTruckIds =  supportStation.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());

        Assert.assertEquals("Six trucks should move out in case a B747 is crashed on the own runway", 6, usedFireTruckIds.size());
        Assert.assertTrue("B747 V01 from own runway should move out", usedFireTruckIds.contains("V01"));
        Assert.assertTrue("B747 V02 from own runway should move out", usedFireTruckIds.contains("V02"));
        Assert.assertTrue("B747 V02 from own runway should move out", usedFireTruckIds.contains("V03"));
        Assert.assertTrue("B747 V06 from own runway should move out", usedFireTruckIds.contains("V06"));
        Assert.assertTrue("B747 V07 from own runway should move out", usedFireTruckIds.contains("V07"));
        Assert.assertTrue("B747 V08 from own runway should move out", usedFireTruckIds.contains("V08"));

        Assert.assertEquals("Two trucks should move out in case a B747 is crashed on the other runway", 2, usedSupportFireTruckIds.size());
        Assert.assertTrue("B747 V06 from support fire station runway should move out", usedSupportFireTruckIds.contains("V06"));
        Assert.assertTrue("B747 V08 from support fire station runway should move out", usedSupportFireTruckIds.contains("V08"));
    }

    @Test
    public void planesCrashed_ownRunway_B777() {
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "Runway01");
        Runway secondRunway = new Runway(tower, "Runway01");

        FireStation testee = new FireStation(tower, "TestFireStation01");
        testee.setRunway(runway);

        FireStation supportStation = new FireStation(tower, "TestFireStation02");
        supportStation.setRunway(secondRunway);


        Plane crashedPlane = new Plane(tower, "Plane01", PlaneType.B777);
        runway.initiateLandingPermission(runway, crashedPlane);
        crashedPlane.setCrashed(true);

        List<Runway> crashedPlanes =  Arrays.asList(runway);

        testee.planesCrashed(crashedPlanes);
        supportStation.planesCrashed(crashedPlanes);

        List<String> usedFireTruckIds =  testee.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());
        List<String> usedSupportFireTruckIds =  supportStation.getFireTrucks().stream().filter(truck -> truck.isInUse()).map(truck -> truck.getId()).collect(Collectors.toList());

        Assert.assertEquals("Six trucks should move out in case a B777 is crashed on the own runway", 6, usedFireTruckIds.size());
        Assert.assertTrue("B777 V01 from own runway should move out", usedFireTruckIds.contains("V01"));
        Assert.assertTrue("B777 V02 from own runway should move out", usedFireTruckIds.contains("V02"));
        Assert.assertTrue("B777 V02 from own runway should move out", usedFireTruckIds.contains("V03"));
        Assert.assertTrue("B777 V06 from own runway should move out", usedFireTruckIds.contains("V06"));
        Assert.assertTrue("B777 V07 from own runway should move out", usedFireTruckIds.contains("V07"));
        Assert.assertTrue("B777 V08 from own runway should move out", usedFireTruckIds.contains("V08"));

        Assert.assertEquals("Two trucks should move out in case a B777 is crashed on the other runway", 2, usedSupportFireTruckIds.size());
        Assert.assertTrue("B777 V06 from support fire station runway should move out", usedSupportFireTruckIds.contains("V06"));
        Assert.assertTrue("B777 V08 from support fire station runway should move out", usedSupportFireTruckIds.contains("V08"));
    }

}