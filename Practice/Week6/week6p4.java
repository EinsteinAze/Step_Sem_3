class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    void borrowBook() {
        booksBorrowed++;
    }

    int getBooksBorrowed() {
        return booksBorrowed;
    }

    String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    String getCourse() {
        return course;
    }

    @Override
    String displayInfo() {
        return "Student | Course: " + course + " | Books: " + getBooksBorrowed();
    }
}

public class week6p4 {
    static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();
        for (LibraryMember member : members) {
            sb.append(member.displayInfo());
            if (member instanceof StudentMember) {
                StudentMember sm = (StudentMember) member;
                sb.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
            }
            sb.append(" | ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(batchPrint(new LibraryMember[]{
                new LibraryMember("LB5", 3),
                new StudentMember("STU6", 3, "ECE")
        }));

        LibraryMember plain = new LibraryMember("LB6", 3);
        try {
            StudentMember bad = (StudentMember) plain;
        } catch (ClassCastException e) {
            System.out.println("ClassCastException at runtime");
        }
    }
}
