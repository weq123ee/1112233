
import java.util.*;

public class F03_ConvenienceHotItems {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] names = new String[n];
        int[] qtys = new int[n];

        for (int i = 0; i < n; i++) {
            names[i] = sc.next();
            qtys[i] = sc.nextInt();
        }

        for (int i = 1; i < n; i++) {
            String keyName = names[i];
            int keyQty = qtys[i];
            int j = i - 1;
            while (j >= 0 && qtys[j] < keyQty) {
                names[j + 1] = names[j];
                qtys[j + 1] = qtys[j];
                j--;
            }
            names[j + 1] = keyName;
            qtys[j + 1] = keyQty;
        }

        for (int i = 0; i < Math.min(10, n); i++) {
            System.out.println(names[i] + " " + qtys[i]);
        }
    }
}
/*
 * Time Complexity: O(n^2)
 * 說明：插入排序最壞情況下需比較與移動每個元素，時間複雜度為平方級
 */
