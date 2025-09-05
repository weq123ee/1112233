import java.util.*;
public class LC25_ReverseKGroup_Shifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        List<Integer> a = new ArrayList<>();
        while (sc.hasNextInt()) a.add(sc.nextInt());
        for (int i=0;i+k<=a.size(); i+=k) {
            int l=i, r=i+k-1;
            while (l<r) {
                int t=a.get(l); a.set(l,a.get(r)); a.set(r,t);
                l++; r--;
            }
        }
        StringJoiner sj = new StringJoiner(" ");
        for (int x: a) sj.add(String.valueOf(x));
        System.out.println(sj.toString());
    }
}
