import java.util.*;
public class LC40_CombinationSum2_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), target = sc.nextInt();
        int[] a = new int[n];
        for (int i=0;i<n;i++) a[i]=sc.nextInt();
        Arrays.sort(a);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(a, target, 0, new ArrayList<>(), res);
        for (List<Integer> r: res) {
            StringJoiner sj = new StringJoiner(" ");
            for (int v: r) sj.add(String.valueOf(v));
            System.out.println(sj.toString());
        }
    }
    static void backtrack(int[] a, int remain, int start, List<Integer> cur, List<List<Integer>> res) {
        if (remain==0) { res.add(new ArrayList<>(cur)); return; }
        if (remain<0) return;
        for (int i=start;i<a.length;i++) {
            if (i>start && a[i]==a[i-1]) continue;
            cur.add(a[i]);
            backtrack(a, remain - a[i], i+1, cur, res);
            cur.remove(cur.size()-1);
        }
    }
}
