import java.util.Scanner;

public class ds_03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("請輸入姓名：");
        String name = sc.nextLine();
        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine(); // 吸收換行符號
        System.out.print("請輸入城市：");
        String city = sc.nextLine();

        System.out.println("姓名：" + name);
        System.out.println("年齡：" + age);
        System.out.println("城市：" + city);
        sc.close();
    }
}
