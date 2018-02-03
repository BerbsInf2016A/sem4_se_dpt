package t52airport;

import t52airport.Plane;
import t52airport.Runway;

public interface ILandingCommand {
    void initiateLandingPermission(Runway runway, Plane plane);
}
