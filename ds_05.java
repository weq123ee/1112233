public class ds_05 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        System.out.println("陣列元素總和為：" + sum);
    }
}
