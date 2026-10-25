public class dsa4 {

    static int countAlerts(int[] readings, int k, int threshold) {
        long required = (long) k * threshold;
        long windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += readings[i];
        }
        int alerts = windowSum >= required ? 1 : 0;
        for (int i = k; i < readings.length; i++) {
            windowSum += readings[i];
            windowSum -= readings[i - k];
            if (windowSum >= required) {
                alerts++;
            }
        }
        return alerts;
    }

    public static void main(String[] args) {
        System.out.println(countAlerts(new int[]{2, 2, 2, 2, 5, 5, 5, 8}, 3, 4));
        System.out.println(countAlerts(new int[]{10}, 1, 10));
        System.out.println(countAlerts(new int[]{1, 2, 3}, 3, 3));
    }
}
