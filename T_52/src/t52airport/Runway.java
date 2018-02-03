package t52airport;

public class Runway  implements  ILandingCommand{
    private ITower tower;

    public String getId() {
        return id;
    }

    private String id;

    public Plane getPlane() {
        return plane;
    }

    private Plane plane;


    public Runway(ITower tower, String id) {
        this.tower = tower;
        this.id = id;
    }

    public void initiateLandingPermission(Runway runway, Plane plane) {
        if(runway == this) {
            this.plane = plane;
        }
    }
}
