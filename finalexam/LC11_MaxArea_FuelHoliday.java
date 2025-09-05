import java.util.*;
public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] h = new long[n];
        for (int i=0;i<n;i++) h[i]=sc.nextLong();
        int l=0, r=n-1;
        long best=0;
        while (l<r) {
            long area = (long)(r-l) * Math.min(h[l], h[r]);
            best = Math.max(best, area);
            if (h[l] < h[r]) l++; else r--;
        }
        System.out.println(best);
    }
}
