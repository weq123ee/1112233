public class array_sum_trace {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5};
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            System.out.println("加總過程：total = " + total + " + " + arr[i] + " = " + (total + arr[i]));
            total += arr[i];
        }
        System.out.println("總和為：" + total);
    }
}
