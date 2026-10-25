import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class dsp4 {

    static boolean hasPairWithSumSorting(int[] nums, int target) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        int left = 0;
        int right = sorted.length - 1;
        while (left < right) {
            long sum = (long) sorted[left] + sorted[right];
            if (sum == target) {
                return true;
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    static boolean hasPairWithSumHashing(int[] nums, int target) {
        Set<Long> seen = new HashSet<>();
        for (int value : nums) {
            if (seen.contains((long) target - value)) {
                return true;
            }
            seen.add((long) value);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a = {2, 7, 11, 15};
        int[] b = {3, 4, 6};

        System.out.println(hasPairWithSumHashing(a, 9));
        System.out.println(hasPairWithSumHashing(b, 20));
        System.out.println(hasPairWithSumSorting(a, 9));
        System.out.println(hasPairWithSumSorting(b, 20));
        System.out.println(hasPairWithSumSorting(new int[]{-4, 10, 4, -4}, 0));
        System.out.println(hasPairWithSumHashing(new int[]{-4, 10, 4, -4}, 0));
    }
}
