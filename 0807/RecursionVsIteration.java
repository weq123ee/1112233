public class RecursionVsIteration {
    public static void main(String[] args) {
        int n = 5;
        int[] arr = {1, 2, 3, 4};
        String str = "Hello World!";
        String parentheses = "(()())";

        // 計算二項式係數
        System.out.println("C(" + n + ", 2) 遞迴: " + binomialCoefficientRec(n, 2));
        System.out.println("C(" + n + ", 2) 迭代: " + binomialCoefficientIter(n, 2));

        // 陣列元素乘積
        System.out.println("陣列乘積 遞迴: " + productRec(arr, arr.length));
        System.out.println("陣列乘積 迭代: " + productIter(arr));

        // 字串中元音字母數量
        System.out.println("字串中元音數 遞迴: " + countVowelsRec(str, 0));
        System.out.println("字串中元音數 迭代: " + countVowelsIter(str));

        // 括號配對檢查
        System.out.println("括號配對 遞迴: " + checkParenthesesRec(parentheses, 0, 0));
        System.out.println("括號配對 迭代: " + checkParenthesesIter(parentheses));
    }

    // 二項式係數遞迴
    public static int binomialCoefficientRec(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialCoefficientRec(n - 1, k - 1) + binomialCoefficientRec(n - 1, k);
    }

    // 二項式係數迭代 (Pascal三角形)
    public static int binomialCoefficientIter(int n, int k) {
        int[] C = new int[k+1];
        C[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(i, k); j > 0; j--) {
                C[j] = C[j] + C[j-1];
            }
        }
        return C[k];
    }

    // 陣列乘積遞迴
    public static int productRec(int[] arr, int n) {
        if (n == 0) return 1;
        return arr[n-1] * productRec(arr, n-1);
    }

    // 陣列乘積迭代
    public static int productIter(int[] arr) {
        int prod = 1;
        for (int val : arr) prod *= val;
        return prod;
    }

    // 計算元音遞迴
    public static int countVowelsRec(String s, int index) {
        if (index == s.length()) return 0;
        char c = Character.toLowerCase(s.charAt(index));
        int count = "aeiou".indexOf(c) >= 0 ? 1 : 0;
        return count + countVowelsRec(s, index + 1);
    }

    // 計算元音迭代
    public static int countVowelsIter(String s) {
        int count = 0;
        for (char c : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(c) >= 0) count++;
        }
        return count;
    }

    // 括號配對遞迴
    public static boolean checkParenthesesRec(String s, int index, int count) {
        if (count < 0) return false;
        if (index == s.length()) return count == 0;
        char c = s.charAt(index);
        if (c == '(') count++;
        else if (c == ')') count--;
        return checkParenthesesRec(s, index + 1, count);
    }

    // 括號配對迭代
    public static boolean checkParenthesesIter(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') {
                count--;
                if (count < 0) return false;
            }
        }
        return count == 0;
    }
}
