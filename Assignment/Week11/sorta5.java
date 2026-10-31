public class sorta5 {

    static class Entry {
        final String name;
        final int marks;

        Entry(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return name + " " + marks;
        }
    }

    static Entry[] leaderboard(Entry[] entries) {
        int range = 101;
        int[] count = new int[range];
        for (Entry entry : entries) {
            if (entry.marks < 0 || entry.marks > 100) {
                throw new IllegalArgumentException("marks must be between 0 and 100");
            }
            count[100 - entry.marks]++;
        }
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        Entry[] sorted = new Entry[entries.length];
        for (int i = entries.length - 1; i >= 0; i--) {
            int key = 100 - entries[i].marks;
            count[key]--;
            sorted[count[key]] = entries[i];
        }
        return sorted;
    }

    static String join(Entry[] entries) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < entries.length; i++) {
            if (i > 0) {
                line.append(", ");
            }
            line.append(entries[i]);
        }
        return line.toString();
    }

    static void run(Entry... entries) {
        System.out.println(join(leaderboard(entries)));
    }

    public static void main(String[] args) {
        run(new Entry("Asha", 90), new Entry("Ravi", 75), new Entry("Neha", 90), new Entry("Kiran", 100), new Entry("Meera", 75));
        run(new Entry("A", 50), new Entry("B", 50), new Entry("C", 50));
        run(new Entry("Low", 0), new Entry("Top", 100));
        run(new Entry("Solo", 64));
        run();
    }
}
