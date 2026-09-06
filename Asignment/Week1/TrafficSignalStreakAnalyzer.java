package Asignment.Week1;

public class TrafficSignalStreakAnalyzer {
    static void findLongestStreak(String signalLog) {
        int maxLen = 1;
        char maxChar = signalLog.charAt(0);

        int currentLen = 1;
        char currentChar = signalLog.charAt(0);

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentChar) {
                currentLen++;
            } else {
                currentChar = signalLog.charAt(i);
                currentLen = 1;
            }

            if (currentLen > maxLen) {
                maxLen = currentLen;
                maxChar = currentChar;
            }
        }

        System.out.println("Longest Streak: '" + maxChar + "' repeated " + maxLen + " times");
    }

    public static void main(String[] args) {
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");
    }
}