class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class BSTKthElement {
    static int count = 0, result = -1;

    public static void kthSmallest(TreeNode root, int k) {
        if (root == null) return;
        kthSmallest(root.left, k);
        count++;
        if (count == k) {
            result = root.val;
            return;
        }
        kthSmallest(root.right, k);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        kthSmallest(root, 3);
        System.out.println("Kth smallest: " + result);
    }
}