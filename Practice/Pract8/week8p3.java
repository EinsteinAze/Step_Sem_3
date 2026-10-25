import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;

abstract class Question {
    private final int number;
    private final String text;
    private final int points;

    Question(int number, String text, int points) {
        this.number = number;
        this.text = text;
        this.points = points;
    }

    int getNumber() {
        return number;
    }

    String getText() {
        return text;
    }

    int getPoints() {
        return points;
    }

    abstract boolean isCorrect(String answer);
}

class MultipleChoiceQuestion extends Question {
    private final String correctOption;

    MultipleChoiceQuestion(int number, String text, int points, String correctOption) {
        super(number, text, points);
        this.correctOption = correctOption;
    }

    @Override
    boolean isCorrect(String answer) {
        return answer != null && answer.trim().equalsIgnoreCase(correctOption);
    }
}

class TrueFalseQuestion extends Question {
    private final boolean correctValue;

    TrueFalseQuestion(int number, String text, int points, boolean correctValue) {
        super(number, text, points);
        this.correctValue = correctValue;
    }

    @Override
    boolean isCorrect(String answer) {
        if (answer == null) {
            return false;
        }
        String value = answer.trim();
        if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
            return false;
        }
        return Boolean.parseBoolean(value) == correctValue;
    }
}

class ShortAnswerQuestion extends Question {
    private final String expectedAnswer;

    ShortAnswerQuestion(int number, String text, int points, String expectedAnswer) {
        super(number, text, points);
        this.expectedAnswer = expectedAnswer;
    }

    @Override
    boolean isCorrect(String answer) {
        return answer != null && answer.trim().equalsIgnoreCase(expectedAnswer);
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

enum AttemptState {
    IN_PROGRESS,
    SUBMITTED
}

class Examination {
    private final String name;
    private final List<Question> questions = new ArrayList<>();
    private final Set<Student> submittedBy = new HashSet<>();

    Examination(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void addQuestion(Question question) {
        questions.add(question);
    }

    List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    int getMaxScore() {
        int total = 0;
        for (Question q : questions) {
            total += q.getPoints();
        }
        return total;
    }

    Attempt start(Student student) {
        if (submittedBy.contains(student)) {
            System.out.println(student.getName() + " has already submitted " + name + ".");
            return null;
        }
        System.out.println(name + " started by " + student.getName() + ".");
        return new Attempt(student, this);
    }

    void registerSubmission(Student student) {
        submittedBy.add(student);
    }
}

class Attempt {
    private final Student student;
    private final Examination examination;
    private final Map<Integer, String> answers = new LinkedHashMap<>();
    private AttemptState state = AttemptState.IN_PROGRESS;

    Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    AttemptState getState() {
        return state;
    }

    void answer(int questionNumber, String answer) {
        if (state == AttemptState.SUBMITTED) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        boolean exists = false;
        for (Question q : examination.getQuestions()) {
            if (q.getNumber() == questionNumber) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            System.out.println("Question " + questionNumber + " does not exist in " + examination.getName() + ".");
            return;
        }
        answers.put(questionNumber, answer);
        System.out.println("Answer recorded for Question " + questionNumber + ".");
    }

    void submit() {
        if (state == AttemptState.SUBMITTED) {
            System.out.println(examination.getName() + " has already been submitted.");
            return;
        }
        state = AttemptState.SUBMITTED;
        examination.registerSubmission(student);
        System.out.println(examination.getName() + " submitted by " + student.getName() + ".");

        StringJoiner joiner = new StringJoiner(", ");
        int score = 0;
        for (Question q : examination.getQuestions()) {
            boolean correct = q.isCorrect(answers.get(q.getNumber()));
            int earned = correct ? q.getPoints() : 0;
            score += earned;
            joiner.add("Question " + q.getNumber() + ": " + (correct ? "Correct" : "Incorrect")
                    + " (" + earned + " points)");
        }
        System.out.println("Result: " + joiner + ". Total score: " + score + "/" + examination.getMaxScore() + ".");
    }
}

public class week8p3 {
    public static void main(String[] args) {
        Examination examA = new Examination("Exam A");
        examA.addQuestion(new MultipleChoiceQuestion(1, "Which keyword creates a subclass?", 5, "C"));
        examA.addQuestion(new TrueFalseQuestion(2, "Java supports multiple class inheritance.", 5, false));

        Student student1 = new Student("Student 1");

        Attempt attempt = examA.start(student1);
        attempt.answer(1, "C");
        attempt.answer(2, "True");
        attempt.submit();
        attempt.answer(1, "A");
    }
}
