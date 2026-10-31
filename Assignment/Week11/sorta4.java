public class sorta4 {

    static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, high);
        return i + 1;
    }

    static int kthHighest(int[] marks, int k) {
        if (k < 1 || k > marks.length) {
            throw new IllegalArgumentException("k must be between 1 and " + marks.length);
        }
        int[] work = marks.clone();
        int target = work.length - k;
        int low = 0;
        int high = work.length - 1;
        while (low <= high) {
            int pivotIndex = partition(work, low, high);
            if (pivotIndex == target) {
                return work[pivotIndex];
            }
            if (pivotIndex < target) {
                low = pivotIndex + 1;
            } else {
                high = pivotIndex - 1;
            }
        }
        throw new IllegalStateException("target index was never reached");
    }

    public static void main(String[] args) {
        System.out.println(kthHighest(new int[]{72, 95, 60, 88, 79}, 2));
        System.out.println(kthHighest(new int[]{50, 90, 90, 70}, 2));
        System.out.println(kthHighest(new int[]{72, 95, 60, 88, 79}, 1));
        System.out.println(kthHighest(new int[]{72, 95, 60, 88, 79}, 5));
        System.out.println(kthHighest(new int[]{5, 5, 5}, 2));
        System.out.println(kthHighest(new int[]{42}, 1));
    }
}
