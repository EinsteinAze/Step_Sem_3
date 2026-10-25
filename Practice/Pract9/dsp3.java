import java.util.HashSet;
import java.util.Set;

public class dsp3 {

    static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((long) nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean hasPairWithSum(int[] nums, int target) {
        Set<Long> seen = new HashSet<>();
        for (int value : nums) {
            long complement = (long) target - value;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add((long) value);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2, 7, 11, 15};
        int[] b = {3, 4, 6};
        int[] c = {3, 3};

        System.out.println(hasPairWithSum(a, 9));
        System.out.println(hasPairWithSum(b, 20));
        System.out.println(hasPairWithSumBruteForce(a, 9));
        System.out.println(hasPairWithSumBruteForce(b, 20));
        System.out.println(hasPairWithSum(c, 6));
        System.out.println(hasPairWithSum(new int[]{3}, 6));
    }
}
