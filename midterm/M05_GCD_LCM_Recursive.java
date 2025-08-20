import java.util.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong(), b = sc.nextLong();
        long g = gcd(a, b);
        long l = (a / g) * b; // 避免溢位
        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}

/*
 * Time Complexity: O(log min(a, b))
 * 說明：
 * - 歐幾里得演算法，每次取模數字至少減半。
 * - 最差情況 O(log min(a, b))。
 */
