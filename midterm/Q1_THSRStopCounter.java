import java.util.*;

public class Q1_THSRStopCounter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 停靠站數
        String[] stations = new String[n];

        for (int i = 0; i < n; i++) {
            stations[i] = sc.next();
        }

        String start = sc.next();
        String end = sc.next();

        int startIdx = -1, endIdx = -1;

        for (int i = 0; i < n; i++) {
            if (stations[i].equals(start)) startIdx = i;
            if (stations[i].equals(end)) endIdx = i;
        }

        if (startIdx == -1 || endIdx == -1) {
            System.out.println("Invalid");
        } else {
            int count = Math.abs(endIdx - startIdx) + 1;
            System.out.println(count);
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * - 走訪陣列一次找起點與終點站，各最多一次比對共 O(n)
 * - 其他操作為常數時間，總體時間與站數 n 成正比。
 */
