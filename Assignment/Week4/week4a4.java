class MembershipCard {
    static String libraryName;
    static String validUntil;

    static {
        libraryName = "SRM Central Library";
        validUntil = "May 2027";
        System.out.println("Library info loaded");
    }

    String studentName;

    MembershipCard(String studentName) {
        this.studentName = studentName;
        System.out.println("Membership card issued: " + studentName);
    }
}

public class week4a4 {
    public static void main(String[] args) {
        String[] names = {"Ananya", "Rohan", "Priya", "Arjun", "Sneha"};

        for (String name : names) {
            new MembershipCard(name);
        }
    }
}
