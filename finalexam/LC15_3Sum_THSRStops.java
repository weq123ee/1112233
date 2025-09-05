import java.util.*;
public class LC15_3Sum_THSRStops {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i=0;i<n;i++) a[i]=sc.nextInt();
        Arrays.sort(a);
        List<String> out = new ArrayList<>();
        for (int i=0;i<n-2;i++) {
            if (i>0 && a[i]==a[i-1]) continue;
            if (a[i] > 0) break;
            int L = i+1, R = n-1;
            while (L < R) {
                int sum = a[i]+a[L]+a[R];
                if (sum==0) {
                    out.add(a[i]+" "+a[L]+" "+a[R]);
                    int vL=a[L], vR=a[R];
                    while (L<n && a[L]==vL) L++;
                    while (R>0 && a[R]==vR) R--;
                } else if (sum < 0) L++; else R--;
            }
        }
        for (String s: out) System.out.println(s);
    }
}
