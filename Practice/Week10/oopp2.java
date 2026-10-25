import java.util.HashMap;
import java.util.Map;

interface Chargeable {
    String bookChargingBay();
}

abstract class Vehicle {
    private final String passNumber;
    private final String owner;

    Vehicle(String passNumber, String owner) {
        this.passNumber = passNumber;
        this.owner = owner;
    }

    String getPassNumber() {
        return passNumber;
    }

    String getOwner() {
        return owner;
    }

    abstract String typeName();

    abstract int yearlyFee();

    String passSummary() {
        return passNumber + " (" + typeName() + ") pass fee " + yearlyFee();
    }
}

class Bike extends Vehicle {
    Bike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    String typeName() {
        return "Bike";
    }

    int yearlyFee() {
        return 300;
    }
}

class Car extends Vehicle {
    Car(String passNumber, String owner) {
        super(passNumber, owner);
    }

    String typeName() {
        return "Car";
    }

    int yearlyFee() {
        return 1000;
    }
}

class EBike extends Vehicle implements Chargeable {
    EBike(String passNumber, String owner) {
        super(passNumber, owner);
    }

    String typeName() {
        return "EBike";
    }

    int yearlyFee() {
        return 300;
    }

    public String bookChargingBay() {
        return getPassNumber() + " charging bay allotted";
    }
}

class ECar extends Vehicle implements Chargeable {
    ECar(String passNumber, String owner) {
        super(passNumber, owner);
    }

    String typeName() {
        return "ECar";
    }

    int yearlyFee() {
        return 1000;
    }

    public String bookChargingBay() {
        return getPassNumber() + " charging bay allotted";
    }
}

public class oopp2 {

    static Vehicle create(String type, String passNumber, String owner) {
        String key = type.replace("-", "").toLowerCase();
        if (key.equals("bike")) {
            return new Bike(passNumber, owner);
        }
        if (key.equals("car")) {
            return new Car(passNumber, owner);
        }
        if (key.equals("ebike")) {
            return new EBike(passNumber, owner);
        }
        if (key.equals("ecar")) {
            return new ECar(passNumber, owner);
        }
        return null;
    }

    static void process(String[] operations) {
        Map<String, Vehicle> passes = new HashMap<>();
        for (String operation : operations) {
            String[] parts = operation.trim().split("\\s+");
            if (parts[0].equals("PASS")) {
                Vehicle vehicle = create(parts[1], parts[2], parts[3]);
                if (vehicle == null) {
                    System.out.println(parts[2] + " rejected: unknown vehicle type");
                } else {
                    passes.put(vehicle.getPassNumber(), vehicle);
                    System.out.println(vehicle.passSummary());
                }
            } else if (parts[0].equals("CHARGE")) {
                Vehicle vehicle = passes.get(parts[1]);
                if (vehicle == null) {
                    System.out.println(parts[1] + " rejected: pass not found");
                } else if (vehicle instanceof Chargeable) {
                    System.out.println(((Chargeable) vehicle).bookChargingBay());
                } else {
                    System.out.println(vehicle.getPassNumber() + " rejected: charging unsupported");
                }
            }
        }
    }

    public static void main(String[] args) {
        process(new String[]{
                "PASS Bike KA01 Asha",
                "PASS ECar KA02 Ravi",
                "CHARGE KA02",
                "CHARGE KA01"
        });
        System.out.println();
        process(new String[]{
                "PASS Car KA03 Neha",
                "PASS E-Bike KA04 Kiran",
                "CHARGE KA04",
                "CHARGE KA03",
                "CHARGE KA99"
        });
    }
}
