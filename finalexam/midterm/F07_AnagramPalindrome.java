import java.util.*;

public class F07_AnagramPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().toLowerCase();
        int[] freq = new int[26];
        for (char c : line.toCharArray()) {
            if (Character.isLetter(c)) freq[c - 'a']++;
        }
        int odd = 0;
        for (int f : freq) if (f % 2 != 0) odd++;
        System.out.println(odd > 1 ? "Impossible" : "Possible");
    }
}
