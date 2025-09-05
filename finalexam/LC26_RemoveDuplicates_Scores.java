import java.util.*;
public class LC26_RemoveDuplicates_Scores {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n==0) { System.out.println(0); return; }
        int[] a = new int[n];
        for (int i=0;i<n;i++) a[i]=sc.nextInt();
        int write=1;
        for (int i=1;i<n;i++) {
            if (a[i] != a[write-1]) { a[write++] = a[i]; }
        }
        System.out.println(write);
        StringJoiner sj = new StringJoiner(" ");
        for (int i=0;i<write;i++) sj.add(String.valueOf(a[i]));
        System.out.println(sj.toString());
    }
}
