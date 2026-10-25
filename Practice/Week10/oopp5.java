abstract class Staff {
    protected final String name;

    Staff(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    abstract String type();

    abstract long calculatePay();

    Payslip toPayslip() {
        return new Payslip(name, type(), calculatePay());
    }
}

class FullTime extends Staff {
    private final long salary;

    FullTime(String name, long salary) {
        super(name);
        this.salary = salary;
    }

    String type() {
        return "FullTime";
    }

    long calculatePay() {
        return salary;
    }
}

class PartTime extends Staff {
    private final long hours;
    private final long rate;

    PartTime(String name, long hours, long rate) {
        super(name);
        this.hours = hours;
        this.rate = rate;
    }

    String type() {
        return "PartTime";
    }

    long calculatePay() {
        return hours * rate;
    }
}

class Intern extends Staff {
    private final long stipend;

    Intern(String name, long stipend) {
        super(name);
        this.stipend = stipend;
    }

    String type() {
        return "Intern";
    }

    long calculatePay() {
        return stipend;
    }
}

class Payslip {
    private final String name;
    private final String type;
    private final long pay;

    Payslip(String name, String type, long pay) {
        this.name = name;
        this.type = type;
        this.pay = pay;
    }

    String getName() {
        return name;
    }

    long getPay() {
        return pay;
    }

    @Override
    public String toString() {
        return "Payslip[name=" + name + ", type=" + type + ", pay=" + pay + "]";
    }
}

public class oopp5 {

    static Staff parse(String line) {
        String[] parts = line.trim().split("\\s+");
        if (parts[0].equals("FullTime")) {
            return new FullTime(parts[1], Long.parseLong(parts[2]));
        }
        if (parts[0].equals("PartTime")) {
            return new PartTime(parts[1], Long.parseLong(parts[2]), Long.parseLong(parts[3]));
        }
        return new Intern(parts[1], Long.parseLong(parts[2]));
    }

    static void runPayroll(String[] lines) {
        Payslip[] payslips = new Payslip[lines.length];
        long total = 0;
        int topIndex = 0;
        for (int i = 0; i < lines.length; i++) {
            payslips[i] = parse(lines[i]).toPayslip();
            total += payslips[i].getPay();
            if (payslips[i].getPay() > payslips[topIndex].getPay()) {
                topIndex = i;
            }
        }
        for (Payslip payslip : payslips) {
            System.out.println(payslip);
        }
        System.out.println("Total " + total);
        System.out.println("top earner " + payslips[topIndex].getName());
    }

    public static void main(String[] args) {
        runPayroll(new String[]{
                "FullTime Asha 50000",
                "PartTime Ravi 80 300",
                "Intern Neha 15000"
        });
        System.out.println();
        runPayroll(new String[]{
                "Intern Kiran 12000",
                "PartTime Meera 200 400",
                "FullTime Dev 60000"
        });
    }
}
