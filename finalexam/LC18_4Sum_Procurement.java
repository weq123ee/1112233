import java.util.*;
public class LC18_4Sum_Procurement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long target = sc.nextLong();
        int[] a = new int[n];
        for (int i=0;i<n;i++) a[i]=sc.nextInt();
        Arrays.sort(a);
        List<String> out = new ArrayList<>();
        for (int i=0;i<n-3;i++) {
            if (i>0 && a[i]==a[i-1]) continue;
            for (int j=i+1;j<n-2;j++) {
                if (j>i+1 && a[j]==a[j-1]) continue;
                int L=j+1, R=n-1;
                while (L<R) {
                    long sum = (long)a[i]+a[j]+a[L]+a[R];
                    if (sum==target) {
                        out.add(a[i]+" "+a[j]+" "+a[L]+" "+a[R]);
                        int vL=a[L], vR=a[R];
                        while (L<n && a[L]==vL) L++;
                        while (R>0 && a[R]==vR) R--;
                    } else if (sum < target) L++; else R--;
                }
            }
        }
        for (String s: out) System.out.println(s);
    }
}
