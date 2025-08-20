import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = calc(income);
            System.out.println("Tax: " + tax);
            sum += tax;
        }
        System.out.println("Average: " + (sum / n));
    }

    static long calc(long x) {
        long tax = 0;
        if (x > 1000000) { tax += (x - 1000000) * 30 / 100; x = 1000000; }
        if (x > 500000) { tax += (x - 500000) * 20 / 100; x = 500000; }
        if (x > 120000) { tax += (x - 120000) * 12 / 100; x = 120000; }
        tax += x * 5 / 100;
        return tax;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * - 每筆收入計算稅額需要常數時間。
 * - 總共 n 筆輸入 → O(n)。
 */
