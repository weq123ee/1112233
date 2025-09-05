import java.util.*;
public class LC21_MergeTwoLists_Clinics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        int[] a = new int[n], b = new int[m];
        for (int i=0;i<n;i++) a[i]=sc.nextInt();
        for (int i=0;i<m;i++) b[i]=sc.nextInt();
        int i=0,j=0;
        StringJoiner sj = new StringJoiner(" ");
        while (i<n && j<m) {
            if (a[i] <= b[j]) { sj.add(String.valueOf(a[i])); i++; }
            else { sj.add(String.valueOf(b[j])); j++; }
        }
        while (i<n) { sj.add(String.valueOf(a[i++])); }
        while (j<m) { sj.add(String.valueOf(b[j++])); }
        System.out.println(sj.toString());
    }
}
