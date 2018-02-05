package t52airport;

import t52airport.helpers.Configuration;
import t52airport.helpers.MersenneTwisterFast;

/**
 * A plane class.
 */
public class Plane implements ILandingCommand {

    /**
     * The tower acts as mediator for the plane.
     */
    private final ITower tower;

    /**
     * The type of the plane.
     */
    private final PlaneType type;

    /**
     * The id of the plane.
     */
    private final String id;
    /**
     * The runway to land on.
     */
    private Runway runway;

    /**
     * True, if the plane crashed during the landing process.
     */
    private boolean isCrashed;

    /**
     * Constructor for  plane.
     *
     * @param tower The tower acts as mediator for the plane.
     * @param id The id of the plane.
     * @param type The type of the plane.
     */
    public Plane(ITower tower, String id, PlaneType type) {
        this.tower = tower;
        this.id = id;
        this.type = type;
    }

    /**
     * Getter for the type of the plane.
     *
     * @return The type of the plane.
     */
    public PlaneType getType() {
        return type;
    }

    /**
     * Getter for the id of the plane.
     *
     * @return The id of the plane.
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for crash flag.
     *
     * @return True if plane is crashed, false if not.
     */
    public boolean isCrashed() {
        return isCrashed;
    }

    /**
     * Setter for the crash flag.
     *
     * @param crashed The value to set.
     */
    public void setCrashed(boolean crashed) {
        isCrashed = crashed;
    }

    /**
     * Gets a short identifying string for the plane.
     *
     * @return A identifying string.
     */
    public String getShortPlaneIdentifier() {
        return String.format("%s <%s>", this.getId(), this.getType());
    }

    /**
     * Initiate the landing process on the given runway.
     *
     * @param runway The runway which should be used to land.
     * @param plane The plane which is allowed to land.
     */
    public void initiateLandingPermission(Runway runway, Plane plane) {
        if (plane == this) { // Security check. Do not react to a landing command of a different plane.
            this.runway = runway;
            this.descendAndLandThePlane();
        }
    }

    /**
     * Descend the plane and land. Also calculating the chance for a crash.
     */
    private void descendAndLandThePlane() {
        MersenneTwisterFast random = Configuration.instance.randomNumberGenerator;
        int value = random.nextInt(0, 100);

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
}
