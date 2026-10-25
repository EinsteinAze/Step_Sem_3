import java.util.Locale;

abstract class MembershipPlan {
    static final double BASE_RATE_PER_MONTH = 1000.0;

    abstract String name();

    abstract int months();

    abstract double discountPercent();

    double calculateFee() {
        return BASE_RATE_PER_MONTH * months() * (100.0 - discountPercent()) / 100.0;
    }
}

class MonthlyPlan extends MembershipPlan {
    @Override
    String name() {
        return "Monthly";
    }

    @Override
    int months() {
        return 1;
    }

    @Override
    double discountPercent() {
        return 0.0;
    }
}

class QuarterlyPlan extends MembershipPlan {
    @Override
    String name() {
        return "Quarterly";
    }

    @Override
    int months() {
        return 3;
    }

    @Override
    double discountPercent() {
        return 10.0;
    }
}

class AnnualPlan extends MembershipPlan {
    @Override
    String name() {
        return "Annual";
    }

    @Override
    int months() {
        return 12;
    }

    @Override
    double discountPercent() {
        return 25.0;
    }
}

enum MembershipStatus {
    ACTIVE("Active"),
    FROZEN("Frozen"),
    EXPIRED("Expired");

    private final String label;

    MembershipStatus(String label) {
        this.label = label;
    }

    String getLabel() {
        return label;
    }
}

class Membership {
    private final Member member;
    private final MembershipPlan plan;
    private final double fee;
    private MembershipStatus status = MembershipStatus.ACTIVE;

    Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee();
    }

    MembershipPlan getPlan() {
        return plan;
    }

    double getFee() {
        return fee;
    }

    MembershipStatus getStatus() {
        return status;
    }

    boolean checkIn() {
        if (status != MembershipStatus.ACTIVE) {
            System.out.println("Check-in denied: " + member.getName() + "'s membership is " + status.getLabel() + ".");
            return false;
        }
        System.out.println(member.getName() + " checked in successfully.");
        return true;
    }

    boolean freeze() {
        if (status != MembershipStatus.ACTIVE) {
            System.out.println("Cannot freeze " + article(status) + " " + status.getLabel() + " membership.");
            return false;
        }
        status = MembershipStatus.FROZEN;
        System.out.println(member.getName() + "'s membership frozen. Status: " + status.getLabel() + ".");
        return true;
    }

    boolean unfreeze() {
        if (status != MembershipStatus.FROZEN) {
            System.out.println("Cannot unfreeze " + article(status) + " " + status.getLabel() + " membership.");
            return false;
        }
        status = MembershipStatus.ACTIVE;
        System.out.println(member.getName() + "'s membership unfrozen. Status: " + status.getLabel() + ".");
        return true;
    }

    boolean expire() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println(member.getName() + "'s membership has already expired.");
            return false;
        }
        status = MembershipStatus.EXPIRED;
        System.out.println(member.getName() + "'s membership expired. Status: " + status.getLabel() + ".");
        return true;
    }

    private static String article(MembershipStatus s) {
        return s == MembershipStatus.ACTIVE || s == MembershipStatus.EXPIRED ? "an" : "a";
    }
}

class Member {
    private final String name;

    Member(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    Membership buy(MembershipPlan plan) {
        Membership membership = new Membership(this, plan);
        System.out.println(plan.name() + " membership created for " + name + ". Fee: ₹"
                + String.format(Locale.ENGLISH, "%,.2f", membership.getFee()) + ". Status: "
                + membership.getStatus().getLabel() + ".");
        return membership;
    }
}

public class week8a4 {
    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership = asha.buy(new QuarterlyPlan());
        Membership raviMembership = ravi.buy(new MonthlyPlan());

        ashaMembership.checkIn();
        ashaMembership.freeze();
        ashaMembership.checkIn();
        raviMembership.expire();
        raviMembership.freeze();
    }
}
