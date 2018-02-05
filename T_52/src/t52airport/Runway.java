package t52airport;

/**
 * A runway.
 */
public class Runway implements ILandingCommand {
    /**
     * The tower acts as mediator for the runway.
     */
    private final ITower tower;

    /**
     * The id of the runway.
     */
    private final String id;

    /**
     * The plane which wants to land.
     */
    private Plane plane;

    /**
     * the constructor for the runway.
     *
     * @param tower The tower acts as mediator for the runway.
     * @param id The id of the runway.
     */
    public Runway(ITower tower, String id) {
        this.tower = tower;
        this.id = id;
    }

    /**
     * Getter for the id.
     *
     * @return The id of the runway.
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for the landed plane.
     *
     * @return The plane.
     */
    public Plane getPlane() {
        return plane;
    }

    /**
     * Initiate the landing process and inform the runway and the plane of each other.
     *
     * @param runway The runway which should be used to land.
     * @param plane The plane which is allowed to land.
     */
    public void initiateLandingPermission(Runway runway, Plane plane) {
        if (runway == this) {
            this.plane = plane;
        }
    }
}
