class TreeMirror {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    public static boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    public static TreeNode mirror(TreeNode root) {
        if (root == null) return null;
        TreeNode left = mirror(root.left);
        TreeNode right = mirror(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        a.left = new TreeNode(2);
        a.right = new TreeNode(2);
        a.left.left = new TreeNode(3);
        a.right.right = new TreeNode(3);
        System.out.println("Is Symmetric: " + isSymmetric(a));
    }
}