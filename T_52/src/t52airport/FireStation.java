package t52airport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a fire station.
 */
public class FireStation implements IPlaneCrashAlarmButtonListener {
    /**
     * The tower acts as mediator for the fire station.
     */
    private final ITower tower;
    /**
     * Contains all listeners for the disaster button.
     */
    private final List<ITower> disasterButtonListeners;
    /**
     * Contains all fire trucks of the station.
     */
    private final List<FireTruck> fireTrucks;
    /**
     * The id of the fire station.
     */
    private final String id;
    /**
     * A command to send a fire truck out to a plane crash.
     */
    private IFireTruckMoveOutCommand fireTruckMoveOutCommand;
    /**
     * The runway which the fire station protects.
     */
    private Runway runway;

    /**
     * Constructor for the fire station.
     *
     * @param tower The tower, which acts as mediator for the fire station.
     * @param id The id of the fire station.
     */
    public FireStation(ITower tower, String id) {
        this.tower = tower;
        this.id = id;
        this.disasterButtonListeners = new ArrayList<>();
        this.fireTrucks = new ArrayList<>();
        this.generateFireTrucks();
    }

    /**
     * Getter for the fire trucks.
     *
     * @return A list of fire trucks.
     */
    public List<FireTruck> getFireTrucks() {
        return fireTrucks;
    }

    /**
     * Get the runway of the fire station.
     *
     * @return The runway.
     */
    public Runway getRunway() {
        return runway;
    }

    /**
     * Set the runway of the fire station.
     *
     * @param runway The runway to set.
     */
    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    public String getId() {
        return id;
    }

    /**
     * Add a disaster button listener.
     *
     * @param listener The disaster button listener.
     */
    public void addDisasterButtonListener(ITower listener) {
        this.disasterButtonListeners.add(listener);
    }

    /**
     * Removes a disaster button listener.
     *
     * @param listener The listener to remove.
     */
    public void removeDisasterButtonListener(ITower listener) {
        if (this.disasterButtonListeners.contains(listener)) {
            this.disasterButtonListeners.remove(listener);
        }
    }

    /**
     * Trigger the disaster alarm.
     *
     * @param location The location of the disaster.
     * @param message The reason for the disaster.
     */
    private void triggerDisasterAlarm(String location, String message) {
        for (ITower tower : this.disasterButtonListeners) {
            tower.disasterOccurred(location, message);
        }
    }

    /**
     * React to a list of runways with crashed planes
     *
     * @param planeCrashes The list of runways with a crashed plane.
     */
    public void planesCrashed(List<Runway> planeCrashes) {
        for (Runway runway : planeCrashes) {
            if (runway.getId().equalsIgnoreCase(this.runway.getId())) {
                System.out.printf("FireStation <%s>: Plane <%s> is crashed on my runway! %n", this.getId(), runway.getPlane().getShortPlaneIdentifier());
                this.handleOwnRunwayCrash(runway.getPlane());
            } else {
                this.handleSupportForOtherRunway(runway);
            }
        }
    }

    /**
     * Handling the support for the other runway.
     *
     * @param runway The runway with the crashed plane.
     */
    private void handleSupportForOtherRunway(Runway runway) {
        switch (runway.getPlane().getType()) {
            case A350:
            case B787:
                System.out.printf("FireStation <%s>: Plane <%s> is crashed on the other runway. Sending support! %n", this.getId(), runway.getPlane().getShortPlaneIdentifier());
                Optional<FireTruck> a350Support = this.fireTrucks.stream().filter(truck -> truck.getId().equalsIgnoreCase("V06")).findFirst();
                this.sendSupportToOtherRunway(a350Support, runway);
                break;
            case A380:
            case B747:
            case B777:
                System.out.printf("FireStation <%s>: Plane <%s> is crashed on the other runway. Sending support! %n", this.getId(), runway.getPlane().getShortPlaneIdentifier());

                List<FireTruck> a380Support = this.fireTrucks.stream().filter(truck -> truck.getId().equalsIgnoreCase("V08") || truck.getId().equalsIgnoreCase("V06"))
                        .collect(Collectors.toList());
                this.sendSupportToOtherRunway(a380Support, runway);
                break;
        }
    }

    /**
     * Sending a single fire truck to the other runway.
     *
     * @param support The fire truck which should be send.
     * @param runway The target runway.
     */
    private void sendSupportToOtherRunway(Optional<FireTruck> support, Runway runway) {
        if (!support.isPresent()) {
            String message = String.format("FireStation <%s>: Can not send support. Truck not found!", this.getId());
            this.triggerDisasterAlarm(runway.getId(), message);
        } else {
            if (!support.get().isInUse()) {
                this.fireTruckMoveOutCommand = new FireTruckMoveOutCommand(support.get());
                this.fireTruckMoveOutCommand.execute(runway);
            } else {
                String message = String.format("FireStation <%s>: Can not send <%s> as support. It is in use!", this.getId(), support.get().getId());
                this.triggerDisasterAlarm(runway.getId(), message);
            }
        }
    }

    /**
     * Sending multiple trucks to the other runway.
     *
     * @param support The fire trucks which should be send.
     * @param runway The target runway.
     */
    private void sendSupportToOtherRunway(List<FireTruck> support, Runway runway) {
        for (FireTruck truck : support) {
            if (truck.isInUse()) {
                String message = String.format("FireStation <%s>: Can not send <%s> as support. It is in use!", this.getId(), truck.getId());
                this.triggerDisasterAlarm(runway.getId(), message);
            } else {
                this.fireTruckMoveOutCommand = new FireTruckMoveOutCommand(truck);
                this.fireTruckMoveOutCommand.execute(runway);
            }
        }
    }

    /**
     * Handling a plane crash on the own runway.
     *
     * @param plane The crashed plane.
     */
    private void handleOwnRunwayCrash(Plane plane) {
        switch (plane.getType()) {
            case A319:
                List<FireTruck> a319trucks = this.fireTrucks.stream().filter(truck -> truck.getId().equalsIgnoreCase("V01") || truck.getId().equalsIgnoreCase("V02"))
                        .collect(Collectors.toList());
                this.sendFireTrucksToOwnRunway(a319trucks);
                break;
            case A320:
            case B737:
                List<FireTruck> a320trucks = this.fireTrucks.stream().filter(truck -> truck.getId().equalsIgnoreCase("V01") || truck.getId().equalsIgnoreCase("V02")
                        || truck.getId().equalsIgnoreCase("V03"))
                        .collect(Collectors.toList());
                this.sendFireTrucksToOwnRunway(a320trucks);
                break;
            case A330:
                List<FireTruck> a330trucks = this.fireTrucks.stream().filter(truck -> truck.getId().equalsIgnoreCase("V01") || truck.getId().equalsIgnoreCase("V02")
                        || truck.getId().equalsIgnoreCase("V03") || truck.getId().equalsIgnoreCase("V04") || truck.getId().equalsIgnoreCase("V05"))
                        .collect(Collectors.toList());
                this.sendFireTrucksToOwnRunway(a330trucks);
                break;
            case A350:
            case B787:
                List<FireTruck> a350trucks = this.fireTrucks.stream().filter(truck -> truck.getId().equalsIgnoreCase("V01") || truck.getId().equalsIgnoreCase("V02")
                        || truck.getId().equalsIgnoreCase("V03") || truck.getId().equalsIgnoreCase("V06"))
                        .collect(Collectors.toList());
                this.sendFireTrucksToOwnRunway(a350trucks);
                break;
            case A380:
            case B747:
            case B777:
                List<FireTruck> a380trucks = this.fireTrucks.stream().filter(truck -> truck.getId().equalsIgnoreCase("V01") || truck.getId().equalsIgnoreCase("V02")
                        || truck.getId().equalsIgnoreCase("V03") || truck.getId().equalsIgnoreCase("V06") || truck.getId().equalsIgnoreCase("V07")
                        || truck.getId().equalsIgnoreCase("V08"))
                        .collect(Collectors.toList());
                this.sendFireTrucksToOwnRunway(a380trucks);
                break;
        }
    }

    /**
     * Sending fire trucks out to the own runway.
     *
     * @param trucks The trucks to send out.
     */
    private void sendFireTrucksToOwnRunway(List<FireTruck> trucks) {
        for (FireTruck truck : trucks) {
            if (truck.isInUse()) {
                String message = String.format("FireStation <%s>: Can not send <%s> as support. It is in use!", this.getId(), truck.getId());
                this.triggerDisasterAlarm(this.runway.getId(), message);
            } else {
                this.fireTruckMoveOutCommand = new FireTruckMoveOutCommand(truck);
                this.fireTruckMoveOutCommand.execute(this.runway);
            }
        }
    }

    /**
     * Generating the fire trucks for the station.
     */
    private void generateFireTrucks() {
        IntStream.range(1, 9).forEach(i -> this.fireTrucks.add(new FireTruck("V0" + i, this.id)));
    }

}
