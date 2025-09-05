import java.util.*;
public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val = sc.nextInt();
        int[] a = new int[n];
        for (int i=0;i<n;i++) a[i]=sc.nextInt();
        int write=0;
        for (int i=0;i<n;i++) if (a[i] != val) a[write++] = a[i];
        System.out.println(write);
        StringJoiner sj = new StringJoiner(" ");
        for (int i=0;i<write;i++) sj.add(String.valueOf(a[i]));
        System.out.println(sj.toString());
    }
}
