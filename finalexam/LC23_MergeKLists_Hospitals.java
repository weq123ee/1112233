import java.util.*;
public class LC23_MergeKLists_Hospitals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        List<int[]> lists = new ArrayList<>();
        sc.nextLine();
        for (int i=0;i<k;i++) {
            String line = sc.nextLine().trim();
            if (line.equals("")) { lists.add(new int[0]); continue; }
            String[] parts = line.split("\\s+");
            List<Integer> tmp = new ArrayList<>();
            for (String p: parts) {
                if (p.equals("-1")) break;
                tmp.add(Integer.parseInt(p));
            }
            int[] arr = new int[tmp.size()];
            for (int t=0;t<tmp.size();t++) arr[t]=tmp.get(t);
            lists.add(arr);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
        // store {value, listIndex, pos}
        for (int i=0;i<lists.size();i++) {
            if (lists.get(i).length>0) pq.add(new int[]{lists.get(i)[0], i, 0});
        }
        StringJoiner sj = new StringJoiner(" ");
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int val = cur[0], li = cur[1], pos = cur[2];
            sj.add(String.valueOf(val));
            pos++;
            if (pos < lists.get(li).length) pq.add(new int[]{lists.get(li)[pos], li, pos});
        }
        System.out.println(sj.toString());
    }
}
