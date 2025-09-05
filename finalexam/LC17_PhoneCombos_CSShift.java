import java.util.*;
public class LC17_PhoneCombos_CSShift {
    static String[] map = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = "";
        if (sc.hasNextLine()) digits = sc.nextLine().trim();
        if (digits.length()==0) return;
        List<String> res = new ArrayList<>();
        backtrack(0, new StringBuilder(), digits, res);
        for (String r: res) System.out.println(r);
    }
    static void backtrack(int idx, StringBuilder cur, String digits, List<String> res) {
        if (idx==digits.length()) { res.add(cur.toString()); return; }
        char d = digits.charAt(idx);
        String letters = map[d-'2'];
        for (char c: letters.toCharArray()) {
            cur.append(c);
            backtrack(idx+1, cur, digits, res);
            cur.deleteCharAt(cur.length()-1);
        }
    }
}
