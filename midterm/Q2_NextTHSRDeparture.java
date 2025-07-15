import java.util.*;

public class Q2_NextTHSRDeparture {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String[] timeStrs = new String[n];
        int[] times = new int[n];

        // 讀取時間並轉為分鐘
        for (int i = 0; i < n; i++) {
            timeStrs[i] = sc.nextLine();
            times[i] = toMinutes(timeStrs[i]);
        }

        String queryStr = sc.nextLine();
        int query = toMinutes(queryStr);

        // 二分搜尋找第一個 > query 的時間
        int left = 0, right = n - 1;
        int ansIdx = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (times[mid] > query) {
                ansIdx = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (ansIdx == -1) {
            System.out.println("No train");
        } else {
            System.out.println(timeStrs[ansIdx]);
        }
    }

    // 時間轉換為分鐘
    static int toMinutes(String time) {
        String[] parts = time.split(":");
        int h = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        return h * 60 + m;
    }
}

/*
 * Time Complexity: O(log n)
 * 說明：
 * - 轉換時間需 O(n)
 * - 使用二分搜尋找第一個大於 query 的時間，時間複雜度為 O(log n)
 * - 總體主流程為 O(n + log n)
 */
