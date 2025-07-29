import java.util.*;

public class F05_LCMRecursive {
    public static int gcd(int a, int b) {
        if (a == b) return a;
        return a > b ? gcd(a - b, b) : gcd(a, b - a);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int gcdVal = gcd(a, b);
        System.out.println("LCM: " + (a * b / gcdVal));
    }
}
/*
 * Time Complexity: O(max(a, b))
 * 說明：使用輾轉相減法，最壞情況下每次只減1，最多遞迴 max(a, b) 次
 */
