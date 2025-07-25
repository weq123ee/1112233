class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class BSTBalance {
    public static int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int diff = Math.abs(height(root.left) - height(root.right));
        return diff <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        System.out.println("Is Balanced: " + isBalanced(root));
    }
}