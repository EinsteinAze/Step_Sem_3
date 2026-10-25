import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

interface NotificationChannel {
    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {
    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[Email → " + student.getName() + "] " + notice.getTitle() + ".");
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[SMS → " + student.getName() + "] " + notice.getTitle() + ".");
    }
}

class AppChannel implements NotificationChannel {
    @Override
    public void send(Student student, Notice notice) {
        System.out.println("[App → " + student.getName() + "] " + notice.getTitle() + ".");
    }
}

class Student {
    private final String name;
    private final String department;
    private final List<NotificationChannel> preferredChannels = new ArrayList<>();

    Student(String name, String department, NotificationChannel... channels) {
        this.name = name;
        this.department = department;
        Collections.addAll(preferredChannels, channels);
    }

    String getName() {
        return name;
    }

    String getDepartment() {
        return department;
    }

    void addChannel(NotificationChannel channel) {
        preferredChannels.add(channel);
    }

    List<NotificationChannel> getPreferredChannels() {
        return Collections.unmodifiableList(preferredChannels);
    }
}

class Notice {
    private final String title;
    private final List<String> targetDepartments;

    Notice(String title, String... departments) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("A title is required.");
        }
        if (departments == null || departments.length == 0) {
            throw new IllegalArgumentException("At least one target department is required.");
        }
        this.title = title.trim();
        this.targetDepartments = new ArrayList<>(Arrays.asList(departments));
    }

    String getTitle() {
        return title;
    }

    List<String> getTargetDepartments() {
        return Collections.unmodifiableList(targetDepartments);
    }

    boolean targets(String department) {
        for (String d : targetDepartments) {
            if (d.equalsIgnoreCase(department)) {
                return true;
            }
        }
        return false;
    }
}

class NoticeBoard {
    private final List<Student> students = new ArrayList<>();

    void register(Student student) {
        students.add(student);
    }

    void post(Notice notice) {
        StringJoiner departments = new StringJoiner(", ");
        for (String d : notice.getTargetDepartments()) {
            departments.add(d);
        }
        System.out.println("Notice '" + notice.getTitle() + "' posted to " + departments + ".");
        for (Student student : students) {
            if (notice.targets(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.send(student, notice);
                }
            }
        }
    }
}

public class week8a5 {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha = new Student("Asha", "CSE", new EmailChannel(), new AppChannel());
        Student ravi = new Student("Ravi", "ECE", new SmsChannel());
        board.register(asha);
        board.register(ravi);

        board.post(new Notice("Lab Closed Tomorrow", "CSE"));
        board.post(new Notice("Fee Deadline Extended", "CSE", "ECE"));

        try {
            board.post(new Notice("Sports Day"));
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot post notice: " + e.getMessage());
        }
    }
}
