import java.util.*;

class ArrayEntry {
    int value;
    int arrayIndex;
    int elementIndex;

    ArrayEntry(int value, int arrayIndex, int elementIndex) {
        this.value = value;
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }
}

public class MergeKSortedArrays {
    public static List<Integer> mergeKSortedArrays(List<List<Integer>> arrays) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.value));

        for (int i = 0; i < arrays.size(); i++) {
            if (!arrays.get(i).isEmpty()) {
                minHeap.offer(new ArrayEntry(arrays.get(i).get(0), i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            ArrayEntry entry = minHeap.poll();
            result.add(entry.value);
            int nextIndex = entry.elementIndex + 1;
            if (nextIndex < arrays.get(entry.arrayIndex).size()) {
                minHeap.offer(new ArrayEntry(arrays.get(entry.arrayIndex).get(nextIndex),
                        entry.arrayIndex, nextIndex));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> arrays = Arrays.asList(
                Arrays.asList(1, 4, 5),
                Arrays.asList(1, 3, 4),
                Arrays.asList(2, 6)
        );
        System.out.println(mergeKSortedArrays(arrays)); // [1,1,2,3,4,4,5,6]
    }
}
