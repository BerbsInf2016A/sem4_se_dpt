package t52airport.helpers;

public enum Configuration {
    instance;
    public MersenneTwisterFast randomNumberGenerator = new MersenneTwisterFast(System.currentTimeMillis());
}
