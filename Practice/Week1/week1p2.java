public class week1p2 {

    boolean isPalindromeIterative(String text) {
        int left = 0, right = text.length() - 1;
        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) return true;
        if (text.charAt(0) != text.charAt(text.length() - 1)) return false;
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        return new String(reversed).equals(text);
    }

    public static void main(String[] args) {
        week1p2 checker = new week1p2();
        String[] inputs = {"madam", "hello"};

        for (String input : inputs) {
            String iterative = checker.isPalindromeIterative(input) ? "Palindrome" : "Not Palindrome";
            String recursive = checker.isPalindromeRecursive(input) ? "Palindrome" : "Not Palindrome";
            String arrayReversal = checker.isPalindromeArrayReversal(input) ? "Palindrome" : "Not Palindrome";
            System.out.println("\"" + input + "\" -> Iterative: " + iterative + " | Recursive: " + recursive + " | Array Reversal: " + arrayReversal);
        }
    }
}
