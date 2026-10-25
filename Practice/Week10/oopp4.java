import java.util.Locale;

public class oopp4 {

    static void analyze(String[] lines) {
        int students = lines.length;
        if (students == 0) {
            return;
        }
        String[] names = new String[students];
        int[][] marks = new int[students][];
        for (int i = 0; i < students; i++) {
            String line = lines[i].trim();
            int open = line.indexOf('[');
            int close = line.indexOf(']');
            names[i] = line.substring(0, open).trim();
            String[] parts = line.substring(open + 1, close).split(",");
            marks[i] = new int[parts.length];
            for (int j = 0; j < parts.length; j++) {
                marks[i][j] = Integer.parseInt(parts[j].trim());
            }
        }

        int subjects = marks[0].length;
        int[] totals = new int[students];
        double[] subjectSums = new double[subjects];
        int topperIndex = 0;
        for (int i = 0; i < students; i++) {
            for (int j = 0; j < subjects; j++) {
                totals[i] += marks[i][j];
                subjectSums[j] += marks[i][j];
            }
            if (totals[i] > totals[topperIndex]) {
                topperIndex = i;
            }
        }

        StringBuilder totalLine = new StringBuilder("Totals ");
        for (int i = 0; i < students; i++) {
            if (i > 0) {
                totalLine.append(", ");
            }
            totalLine.append(names[i]).append(' ').append(totals[i]);
        }
        System.out.println(totalLine);

        StringBuilder averageLine = new StringBuilder("averages ");
        for (int j = 0; j < subjects; j++) {
            if (j > 0) {
                averageLine.append(", ");
            }
            averageLine.append(String.format(Locale.US, "%.2f", subjectSums[j] / students));
        }
        System.out.println(averageLine);

        System.out.println("topper " + names[topperIndex] + " (" + totals[topperIndex] + ")");
    }

    public static void main(String[] args) {
        analyze(new String[]{
                "Asha [78, 85, 90]",
                "Ravi [88, 92, 79]",
                "Neha [65, 70, 95]"
        });
        System.out.println();
        analyze(new String[]{
                "Kiran [50, 50, 50]",
                "Meera [60, 40, 50]"
        });
        System.out.println();
        analyze(new String[]{
                "Solo [91, 82, 73]"
        });
    }
}
