package t52airport.testhelpers;

import t52airport.Tower;

public class TestTower extends Tower {

    private boolean disasterOccured = false;

    public boolean isDisasterOccured() {
        return disasterOccured;
    }

    @Override
    public void disasterOccurred(String location, String message) {
        super.disasterOccurred(location, message);
        disasterOccured = true;
    }

}
