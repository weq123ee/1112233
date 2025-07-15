import java.util.Scanner;

public class Q5_CPBLPrefixWins {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] games = new int[n];

        String[] parts = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            games[i] = Integer.parseInt(parts[i]);
        }

        int k = Integer.parseInt(sc.nextLine());

        // 建立 prefix sum 陣列，長度 n+1，方便計算
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + games[i - 1];
        }

        // 輸出前 k 場的累積勝場
        System.out.print("PrefixSum:");
        for (int i = 1; i <= k; i++) {
            System.out.print(" " + prefixSum[i]);
        }
        System.out.println();

        sc.close();
    }
}

/*
 * Time Complexity: O(n)
 * 說明：建立 prefix sum 陣列需走訪一次長度為 n 的陣列，時間為 O(n)，查詢 prefix sum 為 O(1)。
 */
