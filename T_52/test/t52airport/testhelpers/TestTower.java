package t52airport.testhelpers;

import t52airport.Tower;

/**
 * A tower for tests, which sets a disaster flag.
 */
public class TestTower extends Tower {

    /**
     * True if a disaster occurred.
     */
    private boolean disasterOccurred = false;

    /**
     * Getter for the disaster flag.
     *
     * @return True if a disaster occurred.
     */
    public boolean isDisasterOccurred() {
        return disasterOccurred;
    }

    /**
     * Overrides the base implementation to set a flag.
     *
     * @param location Location of the disaster.
     * @param message The reason for the disaster.
     */
    @Override
    public void disasterOccurred(String location, String message) {
        super.disasterOccurred(location, message);
        disasterOccurred = true;
    }

}
