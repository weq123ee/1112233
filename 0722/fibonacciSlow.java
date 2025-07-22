public class fibonacciSlow {
    public static int fibonacciSlow(int n) {
        if (n <= 1) return n;
        return fibonacciSlow(n - 1) + fibonacciSlow(n - 2);
    }

    public static int fibonacciFast(int n, int[] memo) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        memo[n] = fibonacciFast(n - 1, memo) + fibonacciFast(n - 2, memo);
        return memo[n];
    }

    public static void main(String[] args) {
        System.out.println("fibonacciSlow(6) = " + fibonacciSlow(6));
        int[] memo = new int[7];
        System.out.println("fibonacciFast(6) = " + fibonacciFast(6, memo));
    }
}
