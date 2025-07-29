import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class F10_BSTRangeSum {
    public static int rangeSum(TreeNode root, int L, int R) {
        if (root == null) return 0;
        if (root.val < L) return rangeSum(root.right, L, R);
        if (root.val > R) return rangeSum(root.left, L, R);
        return root.val + rangeSum(root.left, L, R) + rangeSum(root.right, L, R);
    }

    public static TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else root.right = insert(root.right, val);
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] vals = sc.nextLine().split(" ");
        int L = sc.nextInt();
        int R = sc.nextInt();
        TreeNode root = null;
        for (String s : vals) root = insert(root, Integer.parseInt(s));
        System.out.println("Sum: " + rangeSum(root, L, R));
    }
}
