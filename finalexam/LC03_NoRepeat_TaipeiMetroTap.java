import java.util.*;
public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "";
        if (sc.hasNextLine()) s = sc.nextLine();
        // if multiple lines, use only first
        Map<Character, Integer> last = new HashMap<>();
        int left = 0, best = 0;
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (last.containsKey(c)) left = Math.max(left, last.get(c) + 1);
            last.put(c, i);
            best = Math.max(best, i - left + 1);
        }
        System.out.println(best);
    }
}
