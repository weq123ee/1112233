import java.util.*;
public class LC33_SearchRotated_RentHot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] a = new int[n];
        for (int i=0;i<n;i++) a[i]=sc.nextInt();
        int l=0, r=n-1;
        int ans = -1;
        while (l<=r) {
            int mid = (l+r)/2;
            if (a[mid]==target) { ans = mid; break; }
            if (a[l] <= a[mid]) {
                if (a[l] <= target && target < a[mid]) r = mid-1;
                else l = mid+1;
            } else {
                if (a[mid] < target && target <= a[r]) l = mid+1;
                else r = mid-1;
            }
        }
        System.out.println(ans);
    }
}
