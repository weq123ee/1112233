import java.util.*;
public class LC32_LongestValidParen_Metro {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = "";
        if (sc.hasNextLine()) s = sc.nextLine().trim();
        Deque<Integer> st = new ArrayDeque<>();
        st.push(-1);
        int best = 0;
        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            if (c=='(') st.push(i);
            else {
                if (!st.isEmpty()) st.pop();
                if (st.isEmpty()) st.push(i);
                else best = Math.max(best, i - st.peek());
            }
        }
        System.out.println(best);
    }
}
