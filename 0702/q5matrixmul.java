import java.util.Scanner;

public class q5matrixmul {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        int[][] C = new int[n][n];
        int ops = 0;

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                A[i][j] = sc.nextInt();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                B[i][j] = sc.nextInt();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                    ops += 2; // 一次乘法 + 一次加法
                }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(C[i][j] + " ");
            System.out.println();
        }

        System.out.println(ops);
    }
}
