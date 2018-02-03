package t52airport;

import t52airport.helpers.Configuration;
import t52airport.helpers.MersenneTwisterFast;
import t52airport.helpers.SimulationTimerTask;

import java.util.Arrays;
import java.util.Timer;
import java.util.stream.Collectors;

public class Simulation {
    private static final MersenneTwisterFast random = Configuration.instance.randomNumberGenerator;
    private final Timer timer;
    private SimulationTimerTask timerTask;

    public Simulation() {
        this.timer = new Timer();
    }

    private void initializeAndRun() {
        Tower tower = new Tower();
        Runway firstRunway = new Runway(tower, "08R/26L");
        Runway secondRunway = new Runway(tower, "08L/26R");

        FireStation firstFireStation = new FireStation(tower, "FireStation runway 08R/26L");
        FireStation secondFireStation = new FireStation(tower, "FireStation runway 08L/26R");

        tower.registerRunway(firstRunway);
        tower.registerRunway(secondRunway);

        tower.registerFireStation(firstFireStation, firstRunway);
        tower.registerFireStation(secondFireStation, secondRunway);


        for (int i = 1; i <= Configuration.instance.numberOfPlanesPerRunway; i++) {
            tower.registerLandingPlane(new Plane(tower, "Plane_" + i, this.getRandomPlaneType()));
        }

        this.timerTask = new SimulationTimerTask(tower);
        this.timer.schedule(this.timerTask, Configuration.instance.simulationStartDelayInMilliSeconds, Configuration.instance.simulationStepDurationInMilliSeconds);
    }

    public void run() {
        System.out.println("Start of simulation.");
        this.initializeAndRun();
        this.checkForSimulationEnd();
        System.out.println("End of simulation.");
    }

    private void checkForSimulationEnd() {
        while (true) {
            if (this.timerTask.isTaskEnded()) {
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private PlaneType getRandomPlaneType() {
        int value = random.nextInt(0, 8);
        return Arrays.stream(PlaneType.values()).collect(Collectors.toList()).get(value);
    }


}
