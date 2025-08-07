import java.util.Arrays;

public class GradeStatisticsSystem {
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        double average = calculateAverage(scores);
        int max = findMax(scores);
        int min = findMin(scores);
        int[] gradeCounts = countGrades(scores);
        int aboveAverage = countAboveAverage(scores, average);

        System.out.println("成績報表:");
        System.out.println("平均分數: " + average);
        System.out.println("最高分: " + max);
        System.out.println("最低分: " + min);
        System.out.println("等第統計:");
        System.out.println("A: " + gradeCounts[0]);
        System.out.println("B: " + gradeCounts[1]);
        System.out.println("C: " + gradeCounts[2]);
        System.out.println("D: " + gradeCounts[3]);
        System.out.println("F: " + gradeCounts[4]);
        System.out.println("高於平均分的人數: " + aboveAverage);
        System.out.println("所有成績: " + Arrays.toString(scores));
    }

    public static double calculateAverage(int[] arr) {
        int sum = 0;
        for (int score : arr) {
            sum += score;
        }
        return (double) sum / arr.length;
    }

    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int score : arr) {
            if (score > max) max = score;
        }
        return max;
    }

    public static int findMin(int[] arr) {
        int min = arr[0];
        for (int score : arr) {
            if (score < min) min = score;
        }
        return min;
    }

    public static int[] countGrades(int[] arr) {
        int[] count = new int[5]; // A, B, C, D, F
        for (int score : arr) {
            if (score >= 90) count[0]++;
            else if (score >= 80) count[1]++;
            else if (score >= 70) count[2]++;
            else if (score >= 60) count[3]++;
            else count[4]++;
        }
        return count;
    }

    public static int countAboveAverage(int[] arr, double avg) {
        int count = 0;
        for (int score : arr) {
            if (score > avg) count++;
        }
        return count;
    }
}
