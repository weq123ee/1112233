public class GradeProcessor {
    public static void main(String[] args) {
        int[] grades = {78, 85, 92, 67, 88, 95, 73, 90};

        int sum = 0, max = grades[0], min = grades[0];
        int maxIdx = 0, minIdx = 0;

        for (int i = 0; i < grades.length; i++) {
            sum += grades[i];
            if (grades[i] > max) {
                max = grades[i];
                maxIdx = i;
            }
            if (grades[i] < min) {
                min = grades[i];
                minIdx = i;
            }
        }

        double avg = (double) sum / grades.length;
        int countAboveAvg = 0;
        for (int g : grades) {
            if (g > avg) countAboveAvg++;
        }

        System.out.printf("總分: %d\n平均分數: %.2f\n", sum, avg);
        System.out.printf("最高分: %d (索引 %d)\n最低分: %d (索引 %d)\n", max, maxIdx, min, minIdx);
        System.out.println("成績高於平均的學生人數: " + countAboveAvg);
        for (int i = 0; i < grades.length; i++) {
            System.out.println("學生編號 " + i + ": " + grades[i]);
        }
    }
}