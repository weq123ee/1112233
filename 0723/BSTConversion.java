class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class BSTConversion {
    static int sum = 0;

    public static void convertBST(TreeNode root) {
        if (root == null) return;
        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        convertBST(root);
        System.out.println("Root after conversion: " + root.val);
    }
}