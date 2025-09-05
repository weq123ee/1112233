import java.util.*;
public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String hay = "";
        if (sc.hasNextLine()) hay = sc.nextLine();
        String needle = "";
        if (sc.hasNextLine()) needle = sc.nextLine();
        if (needle.length()==0) { System.out.println(0); return; }
        int idx = hay.indexOf(needle);
        System.out.println(idx);
    }
}
