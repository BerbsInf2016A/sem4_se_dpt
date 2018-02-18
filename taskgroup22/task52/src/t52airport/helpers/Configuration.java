package t52airport.helpers;

public enum Configuration {
    instance;
    // Random generator for the probabilities of plane crashes.
    public final MersenneTwisterFast randomNumberGenerator = new MersenneTwisterFast(System.currentTimeMillis());
    // The number of planes which want to land per runway.
    public final int numberOfPlanesPerRunway = 200;
    // Speed of the simulation.
    public final int simulationStepDurationInMilliSeconds = 1000;
    // Delay the start of the simulation.
    public final int simulationStartDelayInMilliSeconds = 500;
    // Define how long a fire truck is blocked, if it moved out.
    public final int fireTruckInterventionDurationInMilliSeconds = 1000;
}
