package t52airport.testhelpers;

import t52airport.Tower;

public class TestTower extends Tower {

    public boolean isDisasterOccured() {
        return disasterOccured;
    }

    private boolean disasterOccured = false;

    @Override
    public void disasterOccurred(String location, String message) {
        super.disasterOccurred(location, message);
        disasterOccured = true;
    }

}
