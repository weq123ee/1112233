import java.util.*;

public class F02_YouBikeNextFull {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String[] t = sc.nextLine().split(":");
            times[i] = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
        }
        String[] q = sc.nextLine().split(":");
        int query = Integer.parseInt(q[0]) * 60 + Integer.parseInt(q[1]);

        int l = 0, r = n - 1, res = -1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (times[m] > query) {
                res = m;
                r = m - 1;
            } else l = m + 1;
        }
        if (res == -1) System.out.println("No bike");
        else System.out.printf("%02d:%02d\n", times[res] / 60, times[res] % 60);
    }
}
/*
 * Time Complexity: O(log n)
 * 說明：對已排序時間陣列進行二分搜尋找出第一個大於查詢值的時間
 */
