import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Student {
    private final String rollNumber;
    private final String name;

    Student(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    String getRollNumber() {
        return rollNumber;
    }

    String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Student that = (Student) other;
        return rollNumber.equals(that.rollNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber);
    }
}

class ClubRegistry {
    private final Set<Student> members = new HashSet<>();

    boolean register(Student student) {
        return members.add(student);
    }

    boolean isRegistered(Student student) {
        return members.contains(student);
    }

    int memberCount() {
        return members.size();
    }
}

public class oopp3 {

    static void process(String[] operations) {
        ClubRegistry club = new ClubRegistry();
        boolean countPrinted = false;
        boolean anyAdd = false;
        for (String operation : operations) {
            String[] parts = operation.trim().split("\\s+");
            Student student = new Student(parts[1], parts[2]);
            if (parts[0].equals("ADD")) {
                anyAdd = true;
                System.out.println(club.register(student) ? "Added" : "duplicate rejected");
            } else if (parts[0].equals("CONTAINS")) {
                if (anyAdd && !countPrinted) {
                    System.out.println("member count " + club.memberCount());
                    countPrinted = true;
                }
                System.out.println("contains: " + club.isRegistered(student));
            }
        }
        if (anyAdd && !countPrinted) {
            System.out.println("member count " + club.memberCount());
        }
    }

    public static void main(String[] args) {
        process(new String[]{
                "ADD 21CS01 Asha",
                "ADD 21CS01 Asha",
                "ADD 21CS02 Ravi",
                "CONTAINS 21CS02 Ravi",
                "CONTAINS 21CS03 Priya"
        });
        System.out.println();
        process(new String[]{
                "ADD 21CS05 Neha",
                "ADD 21CS05 Nehaa",
                "ADD 21CS06 Kiran"
        });
        System.out.println();
        process(new String[]{
                "ADD 21CS07 Meera",
                "CONTAINS 21CS07 SomeoneElse"
        });
    }
}
