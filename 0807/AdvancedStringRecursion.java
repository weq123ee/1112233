import java.util.*;

public class AdvancedStringRecursion {
    public static void main(String[] args) {
        String str = "abc";
        String str2 = "abca";

        System.out.println("字串 " + str + " 的所有排列組合:");
        List<String> permutations = new ArrayList<>();
        permute(str, "", permutations);
        System.out.println(permutations);

        System.out.println("字串 " + str2 + " 是否匹配 'a.c' ? " + match(str2, "a.c"));

        System.out.println("字串 " + str + " 移除重複字符: " + removeDuplicates(str));

        System.out.println("字串 " + str + " 的所有子字串組合:");
        List<String> substrings = new ArrayList<>();
        substringsRecursion(str, 0, "", substrings);
        System.out.println(substrings);
    }

    // 遞迴產生排列組合
    public static void permute(String str, String prefix, List<String> result) {
        if (str.length() == 0) {
            result.add(prefix);
            return;
        }
        for (int i=0; i < str.length(); i++) {
            permute(str.substring(0,i) + str.substring(i+1), prefix + str.charAt(i), result);
        }
    }

    // 遞迴字串匹配（簡單 '.' 代表任意字元）
    public static boolean match(String text, String pattern) {
        if (pattern.length() == 0) return text.length() == 0;
        boolean firstMatch = (text.length() > 0 &&
                             (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return (match(text, pattern.substring(2)) ||
                    (firstMatch && match(text.substring(1), pattern)));
        } else {
            return firstMatch && match(text.substring(1), pattern.substring(1));
        }
    }

    // 遞迴移除字串中重複字符
    public static String removeDuplicates(String str) {
        if (str.length() < 2) return str;
        char first = str.charAt(0);
        String rest = removeDuplicates(str.substring(1));
        if (rest.indexOf(first) >= 0) {
            return rest;
        } else {
            return first + rest;
        }
    }

    // 遞迴計算所有子字串組合
    public static void substringsRecursion(String str, int index, String current, List<String> result) {
        if (index == str.length()) {
            if (!current.equals("")) result.add(current);
            return;
        }
        substringsRecursion(str, index + 1, current + str.charAt(index), result);
        substringsRecursion(str, index + 1, current, result);
    }
}
