package t52airport;

/**
 * A command to let the firetruck move out.
 */
public interface IFireTruckMoveOutCommand {
    /**
     * Let the Firetruck move out.
     *
     * @param runway The runway to move out to.
     */
    void execute(Runway runway);
}
