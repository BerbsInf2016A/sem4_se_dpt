package t52airport;

import t52airport.helpers.Configuration;
import t52airport.helpers.MersenneTwisterFast;
import t52airport.helpers.SimulationTimerTask;

import java.util.Arrays;
import java.util.Timer;
import java.util.stream.Collectors;

/**
 * Simulation class for the airport.
 */
public class Simulation {
    // Random generator, used during plane creation.
    private static final MersenneTwisterFast random = Configuration.instance.randomNumberGenerator;
    // A timer which is used to trigger the simulation steps.
    private final Timer timer;
    // A timer task which is used to trigger the simulation steps.
    private SimulationTimerTask timerTask;

    /**
     * Constructor for the simulation class.
     */
    public Simulation() {
        this.timer = new Timer();
    }

    /**
     * Run the simulation.
     */
    public void run() {
        System.out.println("Start of simulation.");
        this.initializeAndRun();
        this.checkForSimulationEnd();
        System.out.println("End of simulation.");
    }

    /**
     * Initialize the parts of the airport and start the simulation.
     */
    private void initializeAndRun() {
        // Create all necessary parts of the airport.
        Tower tower = new Tower();
        Runway firstRunway = new Runway(tower, "08R/26L");
        Runway secondRunway = new Runway(tower, "08L/26R");

        FireStation firstFireStation = new FireStation(tower, "FireStation runway 08R/26L");
        FireStation secondFireStation = new FireStation(tower, "FireStation runway 08L/26R");

        tower.registerRunway(firstRunway);
        tower.registerRunway(secondRunway);

        tower.registerFireStation(firstFireStation, firstRunway);
        tower.registerFireStation(secondFireStation, secondRunway);

        // Generate the planes and register them at the tower.
        for (int i = 1; i <= Configuration.instance.numberOfPlanesPerRunway; i++) {
            tower.registerLandingPlane(new Plane(tower, "Plane_" + i, this.getRandomPlaneType()));
        }

        // Start the simulation timer and task.
        this.timerTask = new SimulationTimerTask(tower);
        this.timer.schedule(this.timerTask, Configuration.instance.simulationStartDelayInMilliSeconds, Configuration.instance.simulationStepDurationInMilliSeconds);
    }

    /**
     * Constantly checking for the end of the simulation.
     */
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

    /**
     * Get a random PlaneType.
     * @return A plane type.
     */
    private PlaneType getRandomPlaneType() {
        int value = random.nextInt(0, 8);
        return Arrays.stream(PlaneType.values()).collect(Collectors.toList()).get(value);
    }
}
