public class RecursiveMathCalculator {
    public static void main(String[] args) {
        int n = 5;
        int k = 2;

        System.out.println("C(" + n + ", " + k + ") = " + combination(n, k));
        System.out.println("Catalan(" + n + ") = " + catalan(n));
        System.out.println("Hanoi(" + n + ") 移動步數 = " + hanoiSteps(n));

        int palindromeNumber = 12321;
        int nonPalindromeNumber = 12345;
        System.out.println(palindromeNumber + " 是回文數? " + isPalindrome(palindromeNumber));
        System.out.println(nonPalindromeNumber + " 是回文數? " + isPalindrome(nonPalindromeNumber));
    }

    // 計算組合數 C(n, k)
    public static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n -1, k -1) + combination(n -1, k);
    }

    // 計算卡塔蘭數 C(n)
    public static int catalan(int n) {
        if (n <= 1) return 1;
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    // 漢諾塔移動步數 hanoi(n) = 2*hanoi(n-1) + 1
    public static int hanoiSteps(int n) {
        if (n == 1) return 1;
        return 2 * hanoiSteps(n - 1) + 1;
    }

    // 判斷是否為回文數
    public static boolean isPalindrome(int num) {
        return num == reverse(num, 0);
    }

    private static int reverse(int num, int rev) {
        if (num == 0) return rev;
        return reverse(num / 10, rev * 10 + num % 10);
    }
}
