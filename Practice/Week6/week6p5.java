class LibraryMember {
    private static int counter = 100;
    public final String memberNumber;
    private int borrowLimit;
    private int booksBorrowed;
    private String lastGenre;

    public LibraryMember(int borrowLimit) {
        counter++;
        this.memberNumber = "LIB-" + counter;
        this.borrowLimit = borrowLimit;
    }

    void borrowBook() {
        booksBorrowed++;
    }

    void borrowBook(String genre) {
        lastGenre = genre;
        borrowBook();
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'R') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }
        return true;
    }

    static int getMembersEnrolled() {
        return counter - 100;
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }
}

public class week6p5 {
    static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        if (members != null) {
            for (LibraryMember m : members) {
                if (m == null) {
                    nullSkipped++;
                    continue;
                }
                processed++;
                if (m instanceof FacultyMember) {
                    faculty++;
                } else {
                    regular++;
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + faculty + " faculty | " + regular + " regular";
    }

    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember(3);
        System.out.println(m1.memberNumber);
        System.out.println(LibraryMember.getMembersEnrolled());

        System.out.println(LibraryMember.isValidRenewalCode("R12A"));
        System.out.println(LibraryMember.isValidRenewalCode("R1A"));
        System.out.println(LibraryMember.isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());

        System.out.println(processNightlyAudit(new LibraryMember[]{
                new FacultyMember(5, "Physics"),
                null,
                new LibraryMember(3)
        }));
    }
}
