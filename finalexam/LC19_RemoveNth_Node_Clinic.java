import java.util.*;
public class LC19_RemoveNth_Node_Clinic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i=0;i<n;i++) list.add(sc.nextInt());
        int k = sc.nextInt();
        // remove nth from end -> remove index n-k
        int idx = n - k;
        if (idx>=0 && idx<list.size()) list.remove(idx);
        StringJoiner sj = new StringJoiner(" ");
        for (int x: list) sj.add(String.valueOf(x));
        System.out.println(sj.toString());
    }
}
