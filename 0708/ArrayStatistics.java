public class ArrayStatistics {
    public static void main(String[] args) {
        int[] array = {5, 12, 8, 15, 7, 23, 18, 9, 14, 6};

        int sum = 0, max = array[0], min = array[0], maxIdx = 0, minIdx = 0;
        int countAboveAvg = 0, evenCount = 0, oddCount = 0;

        for (int i = 0; i < array.length; i++) {
            sum += array[i];
            if (array[i] > max) {
                max = array[i];
                maxIdx = i;
            }
            if (array[i] < min) {
                min = array[i];
                minIdx = i;
            }
            if (array[i] % 2 == 0) evenCount++;
            else oddCount++;
        }

        double avg = (double) sum / array.length;

        for (int value : array) {
            if (value > avg) countAboveAvg++;
        }

        System.out.println("=== 陣列統計分析 ===");
        System.out.printf("總和: %d\n平均: %.2f\n", sum, avg);
        System.out.printf("最大值: %d (索引 %d)\n", max, maxIdx);
        System.out.printf("最小值: %d (索引 %d)\n", min, minIdx);
        System.out.println("大於平均的數量: " + countAboveAvg);
        System.out.println("偶數數量: " + evenCount);
        System.out.println("奇數數量: " + oddCount);
    }
}