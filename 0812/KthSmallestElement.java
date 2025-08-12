import java.util.*;

public class KthSmallestElement {

    // 方法1：Max Heap（維持大小為K）
    public static int kthSmallestUsingMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    // 方法2：Min Heap（提取K次）
    public static int kthSmallestUsingMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.offer(num);
        }
        int val = -1;
        for (int i = 0; i < k; i++) {
            val = minHeap.poll();
        }
        return val;
    }

    public static void main(String[] args) {
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        System.out.println(kthSmallestUsingMaxHeap(arr1, 3)); // 7
        System.out.println(kthSmallestUsingMinHeap(arr1, 3)); // 7

        int[] arr2 = {1};
        System.out.println(kthSmallestUsingMaxHeap(arr2, 1)); // 1

        int[] arr3 = {3, 1, 4, 1, 5, 9, 2, 6};
        System.out.println(kthSmallestUsingMaxHeap(arr3, 4)); // 3
    }
}
