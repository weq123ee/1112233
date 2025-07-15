import java.util.Scanner;

public class Q4_TieredElectricBill {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        int[] kWhs = new int[n];
        for (int i = 0; i < n; i++) {
            kWhs[i] = Integer.parseInt(sc.nextLine());
        }

        double total = 0;
        for (int i = 0; i < n; i++) {
            double bill = calc(kWhs[i]);
            System.out.printf("Bill: $%d\n", Math.round(bill));
            total += bill;
        }

        System.out.printf("Total: $%d\n", Math.round(total));
        System.out.printf("Average: $%d\n", Math.round(total / n));

        sc.close();
    }

    // 計算分段電費
    private static double calc(int kWh) {
        double cost = 0;
        int remain = kWh;

        int[] limits = {120, 210, 170, 200, 300}; // 分段電量數量: 120, (330-120)=210, (500-330)=170, (700-500)=200, (1000-700)=300
        double[] prices = {1.68, 2.45, 3.70, 5.04, 6.24, 8.46};

        // 依序計算每個階段
        for (int i = 0; i < limits.length; i++) {
            if (remain <= 0) break;
            int used = Math.min(remain, limits[i]);
            cost += used * prices[i];
            remain -= used;
        }
        // 超過 1000 kWh 部分
        if (remain > 0) {
            cost += remain * prices[5];
        }

        return cost;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：讀取 n 筆用電量，對每筆用電量固定做 6 段計費，段數為常數，總計時間與 n 成正比。
 */
