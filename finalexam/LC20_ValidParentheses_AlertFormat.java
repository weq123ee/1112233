import java.util.*;
public class LC20_ValidParentheses_AlertFormat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "";
        if (sc.hasNextLine()) s = sc.nextLine().trim();
        Map<Character, Character> pair = new HashMap<>();
        pair.put(')','('); pair.put(']','['); pair.put('}','{');
        Deque<Character> st = new ArrayDeque<>();
        boolean ok = true;
        for (char c: s.toCharArray()) {
            if (c=='('||c=='['||c=='{') st.push(c);
            else {
                if (st.isEmpty() || st.peek() != pair.get(c)) { ok = false; break; }
                st.pop();
            }
        }
        if (!st.isEmpty()) ok = false;
        System.out.println(ok ? "true" : "false");
    }
}
