import java.util.*;
public class LC04_Median_QuakeFeeds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        double[] A = new double[n];
        for (int i=0;i<n;i++) A[i]=sc.nextDouble();
        double[] B = new double[m];
        for (int i=0;i<m;i++) B[i]=sc.nextDouble();
        System.out.println(String.format("%.1f", findMedianSortedArrays(A,B)));
    }
    public static double findMedianSortedArrays(double[] A, double[] B) {
        if (A.length > B.length) return findMedianSortedArrays(B, A);
        int n = A.length, m = B.length;
        int imin = 0, imax = n, half = (n + m + 1)/2;
        while (imin <= imax) {
            int i = (imin + imax)/2;
            int j = half - i;
            double Aleft = (i==0) ? Double.NEGATIVE_INFINITY : A[i-1];
            double Aright = (i==n) ? Double.POSITIVE_INFINITY : A[i];
            double Bleft = (j==0) ? Double.NEGATIVE_INFINITY : B[j-1];
            double Bright = (j==m) ? Double.POSITIVE_INFINITY : B[j];
            if (Aleft <= Bright && Bleft <= Aright) {
                if ((n+m)%2==1) return Math.max(Aleft, Bleft);
                else return (Math.max(Aleft,Bleft) + Math.min(Aright,Bright))/2.0;
            } else if (Aleft > Bright) {
                imax = i-1;
            } else {
                imin = i+1;
            }
        }
        return 0.0;
    }
}
