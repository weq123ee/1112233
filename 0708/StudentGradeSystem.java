public class StudentGradeSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 81, 88};
        char[] grades = new char[scores.length];
        int countA = 0, countB = 0, countC = 0, countD = 0;
        int sum = 0, max = scores[0], min = scores[0];
        int maxIdx = 0, minIdx = 0;

        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
            if (scores[i] > max) {
                max = scores[i];
                maxIdx = i;
            }
            if (scores[i] < min) {
                min = scores[i];
                minIdx = i;
            }

            if (scores[i] >= 90) {
                grades[i] = 'A'; countA++;
            } else if (scores[i] >= 80) {
                grades[i] = 'B'; countB++;
            } else if (scores[i] >= 70) {
                grades[i] = 'C'; countC++;
            } else {
                grades[i] = 'D'; countD++;
            }
        }

        double avg = (double) sum / scores.length;
        int aboveAvgCount = 0;
        for (int score : scores) {
            if (score > avg) aboveAvgCount++;
        }

        System.out.println("===== 成績報告 =====");
        System.out.println("學生編號\t分數\t等級");
        for (int i = 0; i < scores.length; i++) {
            System.out.printf("%d\t\t%d\t%c\n", i, scores[i], grades[i]);
        }
        System.out.println("--------------------------");
        System.out.printf("平均分數: %.2f\n", avg);
        System.out.printf("最高分: %d (學生編號 %d)\n", max, maxIdx);
        System.out.printf("最低分: %d (學生編號 %d)\n", min, minIdx);
        System.out.printf("A:%d B:%d C:%d D:%d\n", countA, countB, countC, countD);
        System.out.printf("高於平均的學生比例: %.2f%%\n", (aboveAvgCount * 100.0 / scores.length));
    }
}