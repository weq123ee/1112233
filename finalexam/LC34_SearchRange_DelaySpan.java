import java.util.*;
public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] a = new int[n];
        for (int i=0;i<n;i++) a[i]=sc.nextInt();
        int left = lowerBound(a, target);
        int right = lowerBound(a, target+1)-1;
        if (left<=right) System.out.println(left + " " + right);
        else System.out.println("-1 -1");
    }
    static int lowerBound(int[] a, int x) {
        int l=0, r=a.length;
        while (l<r) {
            int m = (l+r)/2;
            if (a[m] < x) l = m+1; else r = m;
        }
        return l;
    }
}
