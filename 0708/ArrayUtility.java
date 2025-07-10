import java.util.Arrays;

public class ArrayUtility {
    public static void main(String[] args) {
        int[] array = {3, 7, 1, 9, 4, 6, 8, 2, 5};

        System.out.print("原始陣列: ");
        printArray(array);

        reverseArray(array);
        System.out.print("反轉後: ");
        printArray(array);

        int[] copied = copyArray(array);
        System.out.print("複製陣列: ");
        printArray(copied);

        System.out.println("第二大元素: " + findSecondLargest(array));
    }

    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void reverseArray(int[] array) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            left++;
            right--;
        }
    }

    public static int[] copyArray(int[] array) {
        return Arrays.copyOf(array, array.length);
    }

    public static int findSecondLargest(int[] array) {
        int max = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int value : array) {
            if (value > max) {
                second = max;
                max = value;
            } else if (value > second && value < max) {
                second = value;
            }
        }
        return second;
    }
}