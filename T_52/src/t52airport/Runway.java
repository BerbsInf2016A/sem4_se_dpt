package t52airport;

public class Runway implements ILandingCommand {
    private final ITower tower;
    private final String id;
    private Plane plane;

    public Runway(ITower tower, String id) {
        this.tower = tower;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Plane getPlane() {
        return plane;
    }

    public void initiateLandingPermission(Runway runway, Plane plane) {
        if (runway == this) {
            this.plane = plane;
        }
    }
}
