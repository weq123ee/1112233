import java.util.*;

public class M01_BuildHeap {
    static boolean isMaxHeap;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        isMaxHeap = type.equals("max");

        // Build-Heap: 從最後一個非葉節點開始
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
            if (i < n - 1) System.out.print(" ");
        }
    }

    static void heapify(int[] arr, int n, int i) {
        int extreme = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && compare(arr[left], arr[extreme])) extreme = left;
        if (right < n && compare(arr[right], arr[extreme])) extreme = right;

        if (extreme != i) {
            int tmp = arr[i];
            arr[i] = arr[extreme];
            arr[extreme] = tmp;
            heapify(arr, n, extreme);
        }
    }

    static boolean compare(int a, int b) {
        return isMaxHeap ? a > b : a < b;
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * - 每個節點最多呼叫一次 heapify。
 * - 樹高 h = log n，每層花費時間逐層遞減。
 * - 總合為 O(n)。
 */
