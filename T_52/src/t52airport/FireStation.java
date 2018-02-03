package t52airport;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FireStation implements IPlaneCrashAlarmButtonListener {
    private final ITower tower;
    private final List<ITower> disasterButtonListeners;
    private final List<FireTruck> fireTrucks;
    private final String id;
    private IFireTruckMoveOutCommand fireTruckMoveOutCommand;
    private Runway runway;
    public FireStation(ITower tower, String id) {
        this.tower = tower;
        this.id = id;
        this.disasterButtonListeners = new ArrayList<>();
        this.fireTrucks = new ArrayList<>();
        this.generateFireTrucks();
    }

    public List<FireTruck> getFireTrucks() {
        return fireTrucks;
    }

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }

    public String getId() {
        return id;
    }

    private void generateFireTrucks() {
        IntStream.range(1, 9).forEach(i -> this.fireTrucks.add(new FireTruck("V0" + i, this.id)));
    }


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

    /*
    (i) A319: V01, V02;
    (ii) A320: wie A319 + V03;
    (iii) A330: wie A320 + V04 und V05;
    (iv) A350: wie A320 + 2x V06;           support
    (v) A380: wie A350 + V07 + 2x V08;      support
    (vi) B737: wie A320;
    (vii) B747: wie A380; (
    viii) B777: wie A380 und
    (ix) B787: wie A350

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

    public void addDisasterButtonListener(ITower listener) {
        this.disasterButtonListeners.add(listener);
    }

    public void removeDisasterButtonListener(ITower listener) {
        if (this.disasterButtonListeners.contains(listener)) {
            this.disasterButtonListeners.remove(listener);
        }
    }

    public void triggerDisasterAlarm(String location, String message) {
        for (ITower tower : this.disasterButtonListeners) {
            tower.disasterOccurred(location, message);
        }
    }
}
