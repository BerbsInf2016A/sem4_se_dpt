package t52airport;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlaneTest {
    @Test
    public void Plane_LandingCommand() {
        ITower tower = new Tower();
        Plane testee = new Plane(tower, "01", PlaneType.A319);
        Runway runway = new Runway(tower, "TestRunway");

        testee.initiateLandingPermission(runway, testee);
    }
    // (i) A319 und B737: 0,05; (ii) A320 und B747: 0,03; (iii) A330 und B787: 0,02; (iv) A350, A380 und B777: 0,01
    @Test
    public void Plane_CrashSimulation_A350_B777_A380(){
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "VeryFrequentedRunway");

        List<Plane> planes = new ArrayList<>();
        int switchPlaneType = 0;
        for (int i = 0; i < 1000; i++) {
            if( switchPlaneType == 0 ){
                planes.add(new Plane(tower, "Id: " + i, PlaneType.A350));
            }
            if(switchPlaneType == 1) {
                planes.add(new Plane(tower, "Id: " + i, PlaneType.A380));
            }
            if (switchPlaneType == 2) {
                planes.add(new Plane(tower, "Id: " + i, PlaneType.B777));
                switchPlaneType = 0;
                continue;
            }
            switchPlaneType++;
        }

        planes.stream().parallel().forEach(plane -> plane.initiateLandingPermission(runway, plane));

        long crashedPlanes = planes.stream().filter(plane -> plane.isCrashed()).count();


        assertTrue("Around 1 percent of the A350, A380 and B777 planes should be crashed:" +
                " so between 0 and 20 planes in 1000 planes. Crashed: " + crashedPlanes, 0 <= crashedPlanes && crashedPlanes <= 20);
    }
    @Test
    public void Plane_CrashSimulation_A330_B787(){
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "VeryFrequentedRunway");

        List<Plane> planes = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if( i % 2 == 0){
                planes.add(new Plane(tower, "Id: " + i, PlaneType.A330));
            } else {
                planes.add(new Plane(tower, "Id: " + i, PlaneType.B787));
            }

        }

        planes.stream().parallel().forEach(plane -> plane.initiateLandingPermission(runway, plane));

        long crashedPlanes = planes.stream().filter(plane -> plane.isCrashed()).count();


        assertTrue("Around 2 percent of the A330 and B787 planes should be crashed:" +
                " so between 10 and 30 planes in 1000 planes " + crashedPlanes, 10 <= crashedPlanes && crashedPlanes <= 30);
    }

    @Test
    public void Plane_CrashSimulation_A320_B747(){
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "VeryFrequentedRunway");

        List<Plane> planes = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if( i % 2 == 0){
                planes.add(new Plane(tower, "Id: " + i, PlaneType.A320));
            } else {
                planes.add(new Plane(tower, "Id: " + i, PlaneType.B747));
            }

        }

        planes.stream().parallel().forEach(plane -> plane.initiateLandingPermission(runway, plane));

        long crashedPlanes = planes.stream().filter(plane -> plane.isCrashed()).count();


        assertTrue("Around 3 percent of the A320 and B747 planes should be crashed:" +
                " so between 20 and 40 planes in 1000 planes " + crashedPlanes, 20 <= crashedPlanes && crashedPlanes <= 40);
    }

    @Test
    public void Plane_CrashSimulation_A319_B737(){
        ITower tower = new Tower();
        Runway runway = new Runway(tower, "VeryFrequentedRunway");

        List<Plane> planes = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if( i % 2 == 0){
                planes.add(new Plane(tower, "Id: " + i, PlaneType.A319));
            } else {
                planes.add(new Plane(tower, "Id: " + i, PlaneType.B737));
            }

        }

        planes.stream().parallel().forEach(plane -> plane.initiateLandingPermission(runway, plane));

        long crashedPlanes = planes.stream().filter(plane -> plane.isCrashed()).count();


        assertTrue("Around 5 percent of the A319 and B737 planes should be crashed:" +
                " so between 40 and 60 planes in 1000 planes " + crashedPlanes, 40 <= crashedPlanes && crashedPlanes <= 60);
    }

}