import java.util.*;
public class LC24_SwapPairs_Shifts {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> a = new ArrayList<>();
        while (sc.hasNextInt()) a.add(sc.nextInt());
        for (int i=0;i+1<a.size(); i+=2) {
            int t = a.get(i); a.set(i, a.get(i+1)); a.set(i+1, t);
        }
        StringJoiner sj = new StringJoiner(" ");
        for (int x: a) sj.add(String.valueOf(x));
        System.out.println(sj.toString());
    }
}
