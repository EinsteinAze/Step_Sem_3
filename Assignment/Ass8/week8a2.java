import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

abstract class Assignment {
    private final String title;
    private final int maxMarks;
    private final LocalDate dueDate;

    Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    String getTitle() {
        return title;
    }

    int getMaxMarks() {
        return maxMarks;
    }

    int daysLate(LocalDate submittedOn) {
        long days = ChronoUnit.DAYS.between(dueDate, submittedOn);
        return days > 0 ? (int) days : 0;
    }

    abstract double penaltyPercent(int daysLate);

    double applyLatePenalty(double awardedMarks, int daysLate) {
        double percent = Math.min(100.0, penaltyPercent(daysLate));
        return awardedMarks * (100.0 - percent) / 100.0;
    }
}

class CodingAssignment extends Assignment {
    CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    double penaltyPercent(int daysLate) {
        return 10.0 * daysLate;
    }
}

class WrittenAssignment extends Assignment {
    WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    @Override
    double penaltyPercent(int daysLate) {
        return 20.0 * daysLate;
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

enum SubmissionStatus {
    SUBMITTED("Submitted"),
    GRADED("Graded");

    private final String label;

    SubmissionStatus(String label) {
        this.label = label;
    }

    String getLabel() {
        return label;
    }
}

class Submission {
    private final Student student;
    private final Assignment assignment;
    private final LocalDate submittedOn;
    private SubmissionStatus status = SubmissionStatus.SUBMITTED;
    private double finalMarks;

    Submission(Student student, Assignment assignment, LocalDate submittedOn) {
        this.student = student;
        this.assignment = assignment;
        this.submittedOn = submittedOn;
    }

    Student getStudent() {
        return student;
    }

    Assignment getAssignment() {
        return assignment;
    }

    SubmissionStatus getStatus() {
        return status;
    }

    boolean isGraded() {
        return status == SubmissionStatus.GRADED;
    }

    int daysLate() {
        return assignment.daysLate(submittedOn);
    }

    boolean grade(double awardedMarks) {
        if (status != SubmissionStatus.SUBMITTED) {
            System.out.println("Cannot grade: '" + assignment.getTitle() + "' has already been graded.");
            return false;
        }
        if (awardedMarks < 0 || awardedMarks > assignment.getMaxMarks()) {
            System.out.println("Cannot grade: marks must be between 0 and " + assignment.getMaxMarks() + ".");
            return false;
        }
        int late = daysLate();
        finalMarks = assignment.applyLatePenalty(awardedMarks, late);
        status = SubmissionStatus.GRADED;

        String shown = format(finalMarks) + "/" + assignment.getMaxMarks();
        if (late > 0) {
            double percent = Math.min(100.0, assignment.penaltyPercent(late));
            System.out.println(student.getName() + " graded: " + shown + " after " + format(percent)
                    + "% late penalty. Status: " + status.getLabel() + ".");
        } else {
            System.out.println(student.getName() + " graded: " + shown + ". Status: " + status.getLabel() + ".");
        }
        return true;
    }

    private static String format(double value) {
        if (value == Math.rint(value)) {
            return String.valueOf((long) value);
        }
        return String.format("%.1f", value);
    }
}

class SubmissionPortal {
    private final List<Submission> submissions = new ArrayList<>();

    private Submission find(Student student, Assignment assignment) {
        for (Submission s : submissions) {
            if (s.getStudent() == student && s.getAssignment() == assignment) {
                return s;
            }
        }
        return null;
    }

    Submission submit(Student student, Assignment assignment, LocalDate date) {
        Submission existing = find(student, assignment);
        if (existing != null && existing.isGraded()) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
            return null;
        }
        if (existing != null) {
            submissions.remove(existing);
        }
        Submission submission = new Submission(student, assignment, date);
        submissions.add(submission);
        int late = submission.daysLate();
        String timing = late == 0 ? "on time" : late + (late == 1 ? " day late" : " days late");
        System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received ("
                + timing + "). Status: " + submission.getStatus().getLabel() + ".");
        return submission;
    }

    boolean grade(Student student, Assignment assignment, double awardedMarks) {
        Submission submission = find(student, assignment);
        if (submission == null) {
            System.out.println("No submission found for " + student.getName() + " on '" + assignment.getTitle() + "'.");
            return false;
        }
        return submission.grade(awardedMarks);
    }
}

public class week8a2 {
    public static void main(String[] args) {
        SubmissionPortal portal = new SubmissionPortal();

        Assignment linkedListLab = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        Assignment designEssay = new WrittenAssignment("Design Essay", 50, LocalDate.of(2026, 3, 12));

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        portal.submit(asha, linkedListLab, LocalDate.of(2026, 3, 10));
        portal.submit(ravi, designEssay, LocalDate.of(2026, 3, 14));
        portal.grade(asha, linkedListLab, 45);
        portal.grade(ravi, designEssay, 40);
        portal.submit(asha, linkedListLab, LocalDate.of(2026, 3, 11));
    }
}
