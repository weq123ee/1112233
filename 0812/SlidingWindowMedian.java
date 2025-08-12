import java.util.*;

public class SlidingWindowMedian {
    private PriorityQueue<Integer> maxHeap; // 存較小的一半
    private PriorityQueue<Integer> minHeap; // 存較大的一半

    public SlidingWindowMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] result = new double[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            addNum(nums[i]);
            if (i >= k) {
                removeNum(nums[i - k]);
            }
            if (i >= k - 1) {
                result[i - k + 1] = getMedian();
            }
        }
        return result;
    }

    private void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balanceHeaps();
    }

    private void removeNum(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balanceHeaps();
    }

    private void balanceHeaps() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return ((double) maxHeap.peek() + (double) minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        SlidingWindowMedian swm = new SlidingWindowMedian();
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        double[] res = swm.medianSlidingWindow(nums, 3);
        System.out.println(Arrays.toString(res)); // [1.0, -1.0, -1.0, 3.0, 5.0, 6.0]
    }
}
