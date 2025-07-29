import java.util.*;

public class F06_ArraySecondLargest {
    public static int[] helper(int[] arr, int l, int r) {
        if (l == r) return new int[]{arr[l], Integer.MIN_VALUE};
        int m = (l + r) / 2;
        int[] left = helper(arr, l, m);
        int[] right = helper(arr, m + 1, r);
        int max, second;
        if (left[0] > right[0]) {
            max = left[0];
            second = Math.max(left[1], right[0]);
        } else {
            max = right[0];
            second = Math.max(right[1], left[0]);
        }
        return new int[]{max, second};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int[] result = helper(arr, 0, n - 1);
        System.out.println("SecondMax: " + result[1]);
    }
}
/*
 * Time Complexity: O(n)
 * 說明：每層合併最大與次大值，總共 log n 層，每層處理所有元素，故為 O(n)
 */
