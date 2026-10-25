abstract class WashType {
    abstract String name();

    abstract int durationMinutes();

    abstract double charge();
}

class QuickWash extends WashType {
    @Override
    String name() {
        return "Quick";
    }

    @Override
    int durationMinutes() {
        return 30;
    }

    @Override
    double charge() {
        return 20.0;
    }
}

class NormalWash extends WashType {
    @Override
    String name() {
        return "Normal";
    }

    @Override
    int durationMinutes() {
        return 45;
    }

    @Override
    double charge() {
        return 30.0;
    }
}

class HeavyWash extends WashType {
    @Override
    String name() {
        return "Heavy";
    }

    @Override
    int durationMinutes() {
        return 60;
    }

    @Override
    double charge() {
        return 45.0;
    }
}

class Student {
    private final String name;

    Student(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class WashCycle {
    private final Student student;
    private final WashingMachine machine;
    private final WashType washType;

    WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    Student getStudent() {
        return student;
    }

    WashingMachine getMachine() {
        return machine;
    }

    WashType getWashType() {
        return washType;
    }
}

class WashingMachine {
    private final String id;
    private WashCycle currentCycle;

    WashingMachine(String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }

    boolean isBusy() {
        return currentCycle != null;
    }

    WashCycle begin(Student student, WashType washType) {
        if (isBusy()) {
            return null;
        }
        currentCycle = new WashCycle(student, this, washType);
        return currentCycle;
    }

    boolean finish() {
        if (!isBusy()) {
            return false;
        }
        currentCycle = null;
        return true;
    }
}

class LaundryDesk {
    WashCycle startWash(Student student, WashingMachine machine, WashType washType) {
        WashCycle cycle = machine.begin(student, washType);
        if (cycle == null) {
            System.out.println("Machine " + machine.getId() + " is currently busy.");
            return null;
        }
        System.out.println(washType.name() + " wash started on " + machine.getId() + " for " + student.getName()
                + " (" + washType.durationMinutes() + " min). Charge: ₹"
                + String.format("%.2f", washType.charge()) + ".");
        return cycle;
    }

    void completeWash(WashingMachine machine) {
        if (machine.finish()) {
            System.out.println(machine.getId() + " cycle completed. " + machine.getId() + " is now free.");
        } else {
            System.out.println(machine.getId() + " has no wash in progress.");
        }
    }
}

public class week8a1 {
    public static void main(String[] args) {
        LaundryDesk desk = new LaundryDesk();

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        desk.startWash(asha, m1, new QuickWash());
        desk.startWash(ravi, m1, new HeavyWash());
        desk.startWash(ravi, m2, new HeavyWash());
        desk.completeWash(m1);
        desk.startWash(neha, m1, new NormalWash());
    }
}
