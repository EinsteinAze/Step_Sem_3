package Asignment.Week1;

public class TypingAccuracyChecker {
    static void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int mismatchPos = -1;

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (mismatchPos == -1) {
                mismatchPos = i;
            }
        }

        double accuracy = ((double) matched / original.length()) * 100;

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ", matched, original.length(), accuracy);
        if (mismatchPos == -1) {
            System.out.println("No Mismatches");
        } else {
            System.out.println("First Mismatch at position " + (mismatchPos + 1)
                    + " ('" + original.charAt(mismatchPos) + "' vs '" + typed.charAt(mismatchPos) + "')");
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}