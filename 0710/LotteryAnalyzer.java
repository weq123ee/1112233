import java.util.*;

public class LotteryAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入樂透歷史資料筆數：");
        int n = sc.nextInt();

        int[] frequency = new int[50]; // 假設號碼範圍為 1~49

        System.out.println("請輸入每組樂透號碼（以空白分隔，結尾 Enter）：");
        for (int i = 0; i < n; i++) {
            System.out.print("第 " + (i + 1) + " 組：");
            for (int j = 0; j < 6; j++) {
                int num = sc.nextInt();
                if (num >= 1 && num <= 49) {
                    frequency[num]++;
                } else {
                    System.out.println("錯誤：號碼應介於 1 到 49");
                    j--;
                }
            }
        }

        System.out.println("\n====== 出現次數統計 ======");
        for (int i = 1; i < frequency.length; i++) {
            if (frequency[i] > 0) {
                System.out.printf("號碼 %2d：%d 次\n", i, frequency[i]);
            }
        }

        // 找最多與最少出現號碼
        int maxFreq = 0, minFreq = Integer.MAX_VALUE;
        List<Integer> mostCommon = new ArrayList<>();
        List<Integer> leastCommon = new ArrayList<>();

        for (int i = 1; i < frequency.length; i++) {
            if (frequency[i] > maxFreq) {
                maxFreq = frequency[i];
                mostCommon.clear();
                mostCommon.add(i);
            } else if (frequency[i] == maxFreq) {
                mostCommon.add(i);
            }

            if (frequency[i] > 0 && frequency[i] < minFreq) {
                minFreq = frequency[i];
                leastCommon.clear();
                leastCommon.add(i);
            } else if (frequency[i] == minFreq) {
                leastCommon.add(i);
            }
        }

        System.out.println("\n最常出現的號碼：" + mostCommon + "（" + maxFreq + " 次）");
        System.out.println("最少出現的號碼：" + leastCommon + "（" + minFreq + " 次）");

        sc.close();
    }
}
