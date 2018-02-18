package t52airport;

/**
 * A command to send a fire truck to a runway.
 */
public class FireTruckMoveOutCommand implements IFireTruckMoveOutCommand {

    /**
     * The fire truck to act on.
     */
    private final FireTruck fireTruck;

    /**
     * Constructor for the command.
     *
     * @param truck The fire truck to act on.
     */
    public FireTruckMoveOutCommand(FireTruck truck) {
        this.fireTruck = truck;
    }

    /**
     * Execute the command.
     *
     * @param runway The runway to move out to.
     */
    public void execute(Runway runway) {
        this.fireTruck.moveOut(runway);
    }
}
