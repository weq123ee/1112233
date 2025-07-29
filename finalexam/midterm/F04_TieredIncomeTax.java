import java.util.*;

public class F04_TieredIncomeTax {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] brackets = {540000, 1210000, 2420000, 4530000};
        double[] rates = {0.05, 0.12, 0.20, 0.30, 0.40};
        int[] bases = {0, 27000, 142000, 350000, 655000};
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int income = sc.nextInt();
            double tax = 0;
            for (int j = brackets.length - 1; j >= 0; j--) {
                if (income > brackets[j]) {
                    tax = (income - brackets[j]) * rates[j + 1] + bases[j + 1];
                    break;
                }
            }
            if (income <= brackets[0]) tax = income * rates[0];
            int taxInt = (int)Math.round(tax);
            System.out.println("Tax: $" + taxInt);
            sum += taxInt;
        }
        System.out.println("Average: $" + (sum / n));
    }
}
/*
 * Time Complexity: O(n)
 * 說明：每筆收入最多判斷5級距，整體為線性次數處理 n 筆資料
 */
