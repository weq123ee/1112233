import java.util.Scanner;

public class q3binsearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int ops = 0;

        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        int key = sc.nextInt();

        int left = 0, right = n - 1, mid;
        int result = -1;

        while (left <= right) {
            mid = (left + right) / 2;
            ops++;
            if (arr[mid] == key) {
                result = mid;
                break;
            } else if (arr[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
        System.out.println(ops);
    }
}
