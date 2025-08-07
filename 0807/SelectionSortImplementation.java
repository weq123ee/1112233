import java.util.Arrays;

public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};

        int comparisons = 0;
        int swaps = 0;

        System.out.println("原始陣列: " + Arrays.toString(arr));

        for (int i = 0; i < arr.length -1; i++) {
            int minIndex = i;
            for (int j = i +1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swaps++;
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
            System.out.println("第 " + (i+1) + " 輪排序結果: " + Arrays.toString(arr));
        }

        System.out.println("總比較次數: " + comparisons);
        System.out.println("總交換次數: " + swaps);

        // 比較氣泡排序效能
        int[] bubbleArr = {64, 25, 12, 22, 11};
        int bubbleComparisons = 0;
        int bubbleSwaps = 0;
        for (int i = 0; i < bubbleArr.length -1; i++) {
            for (int j = 0; j < bubbleArr.length - 1 - i; j++) {
                bubbleComparisons++;
                if (bubbleArr[j] > bubbleArr[j+1]) {
                    bubbleSwaps++;
                    int temp = bubbleArr[j];
                    bubbleArr[j] = bubbleArr[j+1];
                    bubbleArr[j+1] = temp;
                }
            }
        }
        System.out.println("氣泡排序比較次數: " + bubbleComparisons);
        System.out.println("氣泡排序交換次數: " + bubbleSwaps);
    }
}
