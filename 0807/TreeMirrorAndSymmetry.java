public class TreeMirrorAndSymmetry {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        /*
                1
               / \
              2   2
             /     \
            3       3
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.right.right = new TreeNode(3);

        System.out.println("是否為對稱樹: " + isSymmetric(root));

        TreeNode mirror = mirrorTree(root);
        System.out.print("鏡像樹前序: ");
        preOrder(mirror);
        System.out.println();

        System.out.println("root 和 mirror 是否鏡像: " + isMirror(root, mirror));

        TreeNode subTree = new TreeNode(2);
        subTree.left = new TreeNode(3);
        System.out.println("subTree 是否為 root 子樹: " + isSubtree(root, subTree));
    }

    // 判斷是否對稱樹
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    // 判斷兩樹是否鏡像
    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    // 將樹轉成鏡像樹
    public static TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // 前序遍歷
    public static void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // 判斷t是否為s的子樹
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        if (isSameTree(s, t)) return true;
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    // 判斷兩棵樹是否相同
    public static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}
