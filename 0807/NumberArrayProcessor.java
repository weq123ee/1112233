import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 2, 4, 5, 1};
        int[] arr2 = {3, 4, 6, 7, 8};

        System.out.println("原陣列1: " + Arrays.toString(arr1));
        System.out.println("原陣列2: " + Arrays.toString(arr2));

        int[] unique = removeDuplicates(arr1);
        System.out.println("移除重複後陣列1: " + Arrays.toString(unique));

        int[] merged = mergeSortedArrays(new int[]{1,3,5}, new int[]{2,4,6});
        System.out.println("合併排序陣列: " + Arrays.toString(merged));

        int freqMax = mostFrequentElement(arr1);
        System.out.println("陣列中出現頻率最高的元素: " + freqMax);

        List<int[]> splitArrays = splitArray(arr1);
        System.out.println("分割成兩個子陣列:");
        System.out.println("子陣列1: " + Arrays.toString(splitArrays.get(0)));
        System.out.println("子陣列2: " + Arrays.toString(splitArrays.get(1)));
    }

    // 移除重複元素
    public static int[] removeDuplicates(int[] arr) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }

    // 合併兩個已排序的陣列
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] merged = new int[a.length + b.length];
        int i=0, j=0, k=0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) merged[k++] = a[i++];
            else merged[k++] = b[j++];
        }
        while (i < a.length) merged[k++] = a[i++];
        while (j < b.length) merged[k++] = b[j++];
        return merged;
    }

    // 找出頻率最高的元素
    public static int mostFrequentElement(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int maxCount = 0;
        int maxElem = arr[0];
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxElem = entry.getKey();
            }
        }
        return maxElem;
    }

    // 分割成兩個子陣列（長度近似相等）
    public static List<int[]> splitArray(int[] arr) {
        int mid = arr.length / 2;
        int[] part1 = Arrays.copyOfRange(arr, 0, mid);
        int[] part2 = Arrays.copyOfRange(arr, mid, arr.length);
        return Arrays.asList(part1, part2);
    }
}
