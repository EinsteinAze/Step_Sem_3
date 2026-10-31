import java.util.Arrays;

public class sorta1 {

    static int[] bubbleStats(int[] scores) {
        int passes = 0;
        int swaps = 0;
        int n = scores.length;
        for (int pass = 0; pass < n - 1; pass++) {
            passes++;
            boolean swapped = false;
            for (int j = 0; j < n - 1 - pass; j++) {
                if (scores[j] > scores[j + 1]) {
                    int temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return new int[]{passes, swaps};
    }

    static void run(int[] scores) {
        int[] stats = bubbleStats(scores);
        System.out.println(Arrays.toString(scores) + "    passes = " + stats[0] + ", swaps = " + stats[1]);
    }

    public static void main(String[] args) {
        run(new int[]{5, 1, 4, 2, 8});
        run(new int[]{1, 2, 3, 4});
        run(new int[]{4, 3, 2, 1});
        run(new int[]{7});
        run(new int[]{});
    }
}
