import java.util.*;

public class M12_MergeKTimeTables {
    static class Entry implements Comparable<Entry> {
        int time;
        int listIndex;
        int elementIndex;

        Entry(int time, int listIndex, int elementIndex) {
            this.time = time;
            this.listIndex = listIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(Entry other) {
            return Integer.compare(this.time, other.time);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int K = sc.nextInt();
        List<List<Integer>> lists = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            List<Integer> arr = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                arr.add(sc.nextInt());
            }
            lists.add(arr);
        }

        PriorityQueue<Entry> minHeap = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            if (!lists.get(i).isEmpty()) {
                minHeap.offer(new Entry(lists.get(i).get(0), i, 0));
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            Entry curr = minHeap.poll();
            result.add(curr.time);

            int nextIndex = curr.elementIndex + 1;
            if (nextIndex < lists.get(curr.listIndex).size()) {
                minHeap.offer(new Entry(lists.get(curr.listIndex).get(nextIndex), curr.listIndex, nextIndex));
            }
        }

        for (int i = 0; i < result.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(result.get(i));
        }
        System.out.println();

        sc.close();
    }
}
