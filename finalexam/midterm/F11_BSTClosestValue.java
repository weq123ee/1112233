import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class F11_BSTClosestValue {
    public static int closestValue(TreeNode root, int target) {
        int closest = root.val;
        while (root != null) {
            if (Math.abs(root.val - target) < Math.abs(closest - target) ||
                (Math.abs(root.val - target) == Math.abs(closest - target) && root.val < closest)) {
                closest = root.val;
            }
            root = target < root.val ? root.left : root.right;
        }
        return closest;
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
        int target = sc.nextInt();
        TreeNode root = null;
        for (String s : vals) root = insert(root, Integer.parseInt(s));
        System.out.println("Closest: " + closestValue(root, target));
    }
}
