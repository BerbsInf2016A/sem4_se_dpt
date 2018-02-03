package t52airport.helpers;

public enum Configuration {
    instance;
    public final MersenneTwisterFast randomNumberGenerator = new MersenneTwisterFast(System.currentTimeMillis());
    public final int numberOfPlanesPerRunway = 200;
    public final int simulationStepDurationInMilliSeconds = 1000;
    public final int simulationStartDelayInMilliSeconds = 500;
    public final int fireTruckInterventionDurationInMilliSeconds = 1000;
}
