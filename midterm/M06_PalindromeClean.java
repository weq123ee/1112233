import java.util.*;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) if (Character.isLetter(c)) sb.append(c);
        String clean = sb.toString();
        if (isPal(clean, 0, clean.length() - 1)) System.out.println("Yes");
        else System.out.println("No");
    }

    static boolean isPal(String s, int l, int r) {
        if (l >= r) return true;
        if (s.charAt(l) != s.charAt(r)) return false;
        return isPal(s, l + 1, r - 1);
    }
}
