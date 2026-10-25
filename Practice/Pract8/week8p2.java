import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

interface LeavePolicy {
    boolean allows(int days);
}

enum LeaveStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private final String label;

    LeaveStatus(String label) {
        this.label = label;
    }

    String getLabel() {
        return label;
    }
}

abstract class Employee {
    private final String name;

    Employee(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    abstract LeavePolicy leavePolicy();

    LeaveRequest submitLeave(LocalDate start, LocalDate end) {
        int days = (int) ChronoUnit.DAYS.between(start, end) + 1;
        if (!leavePolicy().allows(days)) {
            System.out.println("Leave request rejected for " + name + ": " + days + " days exceeds the allowed limit.");
            return null;
        }
        LeaveRequest request = new LeaveRequest(this, start, end);
        System.out.println("Leave request submitted for " + name + " (" + request.getPeriod() + "). Status: "
                + request.getStatus().getLabel() + ".");
        return request;
    }
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    LeavePolicy leavePolicy() {
        return days -> days <= 20;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    LeavePolicy leavePolicy() {
        return days -> days <= 10;
    }
}

class Contractor extends Employee {
    Contractor(String name) {
        super(name);
    }

    @Override
    LeavePolicy leavePolicy() {
        return days -> days <= 5;
    }
}

class LeaveRequest {
    private static final DateTimeFormatter MONTH_DAY = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);

    private final Employee employee;
    private final LocalDate start;
    private final LocalDate end;
    private LeaveStatus status = LeaveStatus.PENDING;
    private Manager reviewedBy;

    LeaveRequest(Employee employee, LocalDate start, LocalDate end) {
        this.employee = employee;
        this.start = start;
        this.end = end;
    }

    Employee getEmployee() {
        return employee;
    }

    LeaveStatus getStatus() {
        return status;
    }

    String getPeriod() {
        if (start.getMonth() == end.getMonth()) {
            return start.format(MONTH_DAY) + "-" + end.getDayOfMonth();
        }
        return start.format(MONTH_DAY) + "-" + end.format(MONTH_DAY);
    }

    boolean approve(Manager reviewer) {
        return review(reviewer, LeaveStatus.APPROVED);
    }

    boolean reject(Manager reviewer) {
        return review(reviewer, LeaveStatus.REJECTED);
    }

    private boolean review(Manager reviewer, LeaveStatus target) {
        if (reviewer == null) {
            System.out.println("A reviewer is required to approve or reject a leave request.");
            return false;
        }
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + status.getLabel() + " to "
                    + target.getLabel() + ".");
            return false;
        }
        status = target;
        reviewedBy = reviewer;
        return true;
    }

    boolean requestStatusChange(LeaveStatus target) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + status.getLabel() + " to "
                    + target.getLabel() + ".");
            return false;
        }
        System.out.println("Only a reviewer can approve or reject a leave request.");
        return false;
    }
}

class Manager {
    private final String name;

    Manager(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void approve(LeaveRequest request) {
        if (request.approve(this)) {
            System.out.println(request.getEmployee().getName() + "'s leave request (" + request.getPeriod()
                    + ") approved. Status: " + request.getStatus().getLabel() + ".");
        }
    }

    void reject(LeaveRequest request) {
        if (request.reject(this)) {
            System.out.println(request.getEmployee().getName() + "'s leave request (" + request.getPeriod()
                    + ") rejected. Status: " + request.getStatus().getLabel() + ".");
        }
    }
}

public class week8p2 {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");
        Manager alice = new Manager("Alice");
        Manager bob = new Manager("Bob");

        LeaveRequest johnRequest = john.submitLeave(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        alice.approve(johnRequest);

        LeaveRequest janeRequest = jane.submitLeave(LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 11));
        bob.reject(janeRequest);

        johnRequest.requestStatusChange(LeaveStatus.PENDING);
    }
}
