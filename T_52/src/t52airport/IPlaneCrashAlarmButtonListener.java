package t52airport;

import java.util.List;

/**
 * a listener interface for crashes on runways.
 */
public interface IPlaneCrashAlarmButtonListener {
    /**
     * Planes crashed alarm.
     *
     * @param planeCrashes The list of runways with a crashed plane.
     */
    void planesCrashed(List<Runway> planeCrashes);
}
