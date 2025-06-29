import java.util.Scanner;

public class ds_01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("請輸入第一個整數：");
        int num1 = sc.nextInt();
        System.out.print("請輸入第二個整數：");
        int num2 = sc.nextInt();

        int sum = num1 + num2;
        System.out.println("兩數之和為：" + sum);
        sc.close();
    }
}
