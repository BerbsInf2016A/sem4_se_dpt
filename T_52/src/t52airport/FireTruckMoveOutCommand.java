package t52airport;

public class FireTruckMoveOutCommand implements IFireTruckMoveOutCommand {

    private FireTruck fireTruck;

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
