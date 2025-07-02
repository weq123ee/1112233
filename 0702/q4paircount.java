import java.util.Scanner;

public class q4paircount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        int ops = 0;

        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                count++;
                ops++;
            }

        System.out.println(count);
        System.out.println(ops);
    }
}
