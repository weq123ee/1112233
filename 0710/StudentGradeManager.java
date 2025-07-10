import java.util.Scanner;

public class StudentGradeManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入學生人數：");
        int n = sc.nextInt();
        String[] names = new String[n];
        int[] scores = new int[n];
        char[] grades = new char[n];

        int sum = 0, max = -1, min = 101, maxIdx = 0, minIdx = 0;

        for (int i = 0; i < n; i++) {
            System.out.print("輸入第 " + (i + 1) + " 位學生姓名：");
            names[i] = sc.next();
            System.out.print("輸入分數：");
            scores[i] = sc.nextInt();

            sum += scores[i];
            if (scores[i] > max) {
                max = scores[i];
                maxIdx = i;
            }
            if (scores[i] < min) {
                min = scores[i];
                minIdx = i;
            }

            if (scores[i] >= 90)
                grades[i] = 'A';
            else if (scores[i] >= 80)
                grades[i] = 'B';
            else if (scores[i] >= 70)
                grades[i] = 'C';
            else
                grades[i] = 'D';
        }

        double avg = (double) sum / n;
        int aboveAvgCount = 0;
        for (int s : scores) {
            if (s > avg) aboveAvgCount++;
        }

        System.out.println("\n======= 成績報表 =======");
        System.out.println("姓名\t分數\t等級");
        for (int i = 0; i < n; i++) {
            System.out.printf("%s\t%d\t%c\n", names[i], scores[i], grades[i]);
        }

        System.out.println("\n======= 統計資料 =======");
        System.out.printf("平均分數：%.2f\n", avg);
        System.out.printf("最高分：%s (%d)\n", names[maxIdx], max);
        System.out.printf("最低分：%s (%d)\n", names[minIdx], min);
        System.out.printf("高於平均的人數：%d (%.2f%%)\n", aboveAvgCount, aboveAvgCount * 100.0 / n);

        sc.close();
    }
}
