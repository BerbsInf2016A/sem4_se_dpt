package t53landingPlane.Plane;

public class Plane {
    private Cockpit cockpit;

    public Plane() {
        this.cockpit = new Cockpit();
    }

    public void descend() {
        this.cockpit.descend();
    }

    public Cockpit getCockpit() {
        return cockpit;
    }
}
