package t52airport;

import t52airport.helpers.Configuration;
import t52airport.helpers.MersenneTwisterFast;

public class Plane implements ILandingCommand {

    private Runway runway;
    private final ITower tower;

    public PlaneType getType() {
        return type;
    }

    private final PlaneType type;
    private final String id;

    public void setCrashed(boolean crashed) {
        isCrashed = crashed;
    }

    private boolean isCrashed;

    public String getId() {
        return id;
    }

    public String getShortPlaneIdentifier() {
        return String.format("%s <%s>", this.getId(), this.getType());
    }

    public Plane(ITower tower, String id, PlaneType type) {
        this.tower = tower;
        this.id = id;
        this.type = type;
    }


    public void initiateLandingPermission(Runway runway, Plane plane) {
        if (plane == this) {
            this.runway = runway;
            this.descendAndLandThePlane();
        }

    }

    private void descendAndLandThePlane() {
        MersenneTwisterFast random = Configuration.instance.randomNumberGenerator;
        int value = random.nextInt(0, 100);

        // (i) A319 und B737: 0,05; (ii) A320 und B747: 0,03; (iii) A330 und B787: 0,02; (iv) A350, A380 und B777: 0,01

        switch (this.type) {
            case A319:
            case B737:
                if (value <= 5) this.isCrashed = true;
                break;
            case A320:
            case B747:
                if (value <= 3) this.isCrashed = true;
                break;
            case A330:
            case B787:
                if (value <= 2) this.isCrashed = true;
                break;
            case A350:
            case A380:
            case B777:
                if (value <= 1) this.isCrashed = true;
                break;
        }
    }

    public boolean isCrashed() {
        return isCrashed;
    }
}
