import java.util.*;
public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long target = sc.nextLong();
        int[] a = new int[n];
        for (int i=0;i<n;i++) a[i] = sc.nextInt();
        Map<Long,Integer> need = new HashMap<>();
        for (int i=0;i<n;i++) {
            long x = a[i];
            if (need.containsKey((long)x)) {
                System.out.println(need.get((long)x) + " " + i);
                return;
            }
            need.put(target - x, i);
        }
        System.out.println("-1 -1");
    }
}
