import java.util.*;

public class AdvancedArrayRecursion {
    public static void main(String[] args) {
        int[] arr1 = {3,5,7,9};
        int[] arr2 = {2,4,6,8};
        int[] arr3 = {10,20,30,40,50};
        int targetSum = 15;
        int k = 3;

        System.out.println("快速排序前: " + Arrays.toString(arr3));
        quickSort(arr3, 0, arr3.length -1);
        System.out.println("快速排序後: " + Arrays.toString(arr3));

        int[] merged = mergeSorted(arr1, arr2);
        System.out.println("合併排序陣列: " + Arrays.toString(merged));

        int kthSmallest = kthSmallestElement(arr3, 0, arr3.length -1, k);
        System.out.println("第 " + k + " 小元素: " + kthSmallest);

        boolean exists = subsetSum(arr3, arr3.length, targetSum);
        System.out.println("是否存在子序列和為 " + targetSum + "? " + exists);
    }

    // 快速排序遞迴實作
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p -1);
            quickSort(arr, p +1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }

    // 遞迴合併兩個已排序陣列
    public static int[] mergeSorted(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        mergeRecursive(a, 0, b, 0, result, 0);
        return result;
    }

    private static void mergeRecursive(int[] a, int i, int[] b, int j, int[] result, int k) {
        if (i == a.length) {
            while (j < b.length) result[k++] = b[j++];
            return;
        }
        if (j == b.length) {
            while (i < a.length) result[k++] = a[i++];
            return;
        }
        if (a[i] <= b[j]) {
            result[k] = a[i];
            mergeRecursive(a, i+1, b, j, result, k+1);
        } else {
            result[k] = b[j];
            mergeRecursive(a, i, b, j+1, result, k+1);
        }
    }

    // 遞迴尋找第 k 小元素（使用快速選擇）
    public static int kthSmallestElement(int[] arr, int low, int high, int k) {
        if (low <= high) {
            int p = partition(arr, low, high);
            int count = p - low + 1;
            if (count == k) return arr[p];
            else if (k < count) return kthSmallestElement(arr, low, p -1, k);
            else return kthSmallestElement(arr, p +1, high, k - count);
        }
        return Integer.MAX_VALUE; // 不存在
    }

    // 遞迴檢查子序列是否存在和為目標值
    public static boolean subsetSum(int[] arr, int n, int sum) {
        if (sum == 0) return true;
        if (n == 0) return false;
        if (arr[n-1] > sum) return subsetSum(arr, n-1, sum);
        return subsetSum(arr, n-1, sum) || subsetSum(arr, n-1, sum - arr[n-1]);
    }
}
