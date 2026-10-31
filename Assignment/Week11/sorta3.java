public class sorta3 {

    static long countInversions(int[] roll) {
        if (roll.length < 2) {
            return 0;
        }
        int[] work = roll.clone();
        int[] buffer = new int[work.length];
        return sortAndCount(work, buffer, 0, work.length - 1);
    }

    private static long sortAndCount(int[] a, int[] buffer, int low, int high) {
        if (low >= high) {
            return 0;
        }
        int mid = low + (high - low) / 2;
        long count = sortAndCount(a, buffer, low, mid) + sortAndCount(a, buffer, mid + 1, high);
        int i = low;
        int j = mid + 1;
        int k = low;
        while (i <= mid && j <= high) {
            if (a[i] <= a[j]) {
                buffer[k++] = a[i++];
            } else {
                buffer[k++] = a[j++];
                count += mid - i + 1;
            }
        }
        while (i <= mid) {
            buffer[k++] = a[i++];
        }
        while (j <= high) {
            buffer[k++] = a[j++];
        }
        for (k = low; k <= high; k++) {
            a[k] = buffer[k];
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(countInversions(new int[]{2, 4, 1, 3, 5}));
        System.out.println(countInversions(new int[]{5, 4, 3, 2, 1}));
        System.out.println(countInversions(new int[]{1, 2, 3, 4}));
        System.out.println(countInversions(new int[]{1, 1, 1}));
        System.out.println(countInversions(new int[]{3, 1, 2}));
        System.out.println(countInversions(new int[]{7}));
        System.out.println(countInversions(new int[]{}));
    }
}
