import java.util.*;

public class M03_TopKConvenience {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), K = sc.nextInt();
        sc.nextLine();
        PriorityQueue<Item> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            Item it = new Item(name, qty, i);
            if (pq.size() < K) pq.add(it);
            else if (pq.peek().compareTo(it) < 0) {
                pq.poll(); pq.add(it);
            }
        }
        List<Item> list = new ArrayList<>(pq);
        list.sort((a, b) -> {
            if (b.qty != a.qty) return b.qty - a.qty;
            return a.idx - b.idx;
        });
        for (Item it : list) System.out.println(it.name + " " + it.qty);
    }

    static class Item implements Comparable<Item> {
        String name; int qty; int idx;
        Item(String n, int q, int i) {name=n; qty=q; idx=i;}
        public int compareTo(Item o) {
            if (qty != o.qty) return qty - o.qty;
            return o.idx - idx; // 保持輸入順序
        }
    }
}

/*
 * Time Complexity: O(n log K)
 * 說明：
 * - 每個元素最多進出 heap 一次，代價 O(log K)。
 * - 總計 O(n log K)，遠小於 O(n log n)。
 */
