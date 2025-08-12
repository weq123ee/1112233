public class ValidMaxHeapChecker {

    public static boolean isValidMaxHeap(int[] arr) {
        int n = arr.length;
        for (int i = 0; i <= (n - 2) / 2; i++) { // 只檢查非葉節點
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < n && arr[i] < arr[left]) {
                System.out.println("違反規則：索引 " + left + " 的值 " + arr[left] + " 大於父節點 " + arr[i]);
                return false;
            }
            if (right < n && arr[i] < arr[right]) {
                System.out.println("違反規則：索引 " + right + " 的值 " + arr[right] + " 大於父節點 " + arr[i]);
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] testCases = {
                {100, 90, 80, 70, 60, 75, 65},
                {100, 90, 80, 95, 60, 75, 65},
                {50},
                {}
        };

        for (int[] arr : testCases) {
            System.out.print("陣列: ");
            for (int num : arr) System.out.print(num + " ");
            System.out.println();
            boolean result = isValidMaxHeap(arr);
            System.out.println("是否為 Max Heap: " + result);
            System.out.println("--------------");
        }
    }
}
