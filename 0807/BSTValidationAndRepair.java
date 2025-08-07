import java.util.*;

public class BSTValidationAndRepair {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 驗證是否為有效BST
    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }

    private boolean validate(TreeNode node, Integer low, Integer high) {
        if (node == null) return true;
        if ((low != null && node.val <= low) || (high != null && node.val >= high)) return false;
        return validate(node.left, low, node.val) && validate(node.right, node.val, high);
    }

    // 找出不符合規則的節點（簡單版本：找第一個違規節點）
    public List<TreeNode> findViolations(TreeNode root) {
        List<TreeNode> violations = new ArrayList<>();
        findViolationsHelper(root, null, null, violations);
        return violations;
    }

    private void findViolationsHelper(TreeNode node, Integer low, Integer high, List<TreeNode> violations) {
        if (node == null) return;
        if ((low != null && node.val <= low) || (high != null && node.val >= high)) {
            violations.add(node);
        }
        findViolationsHelper(node.left, low, node.val, violations);
        findViolationsHelper(node.right, node.val, high, violations);
    }

    // 修復有兩個節點錯誤位置的BST
    TreeNode first = null, second = null, prev = null;

    public void recoverTree(TreeNode root) {
        inorder(root);
        if (first != null && second != null) {
            int temp = first.val;
            first.val = second.val;
            second.val = temp;
        }
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        if (prev != null && root.val < prev.val) {
            if (first == null) first = prev;
            second = root;
        }
        prev = root;
        inorder(root.right);
    }

    // 計算移除多少節點能使樹成為有效BST (複雜問題，這裡簡化為計算最大BST子樹大小)
    public int nodesToRemove(TreeNode root) {
        int totalNodes = countNodes(root);
        int maxBSTSize = maxBSTSubtree(root).maxSize;
        return totalNodes - maxBSTSize;
    }

    private int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    static class Result {
        boolean isBST;
        int min, max, maxSize;
        Result(boolean isBST, int min, int max, int maxSize) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.maxSize = maxSize;
        }
    }

    private Result maxBSTSubtree(TreeNode root) {
        if (root == null) return new Result(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

        Result left = maxBSTSubtree(root.left);
        Result right = maxBSTSubtree(root.right);

        if (left.isBST && right.isBST && root.val > left.max && root.val < right.min) {
            int size = left.maxSize + right.maxSize + 1;
            return new Result(true, Math.min(root.val, left.min), Math.max(root.val, right.max), size);
        } else {
            return new Result(false, 0, 0, Math.max(left.maxSize, right.maxSize));
        }
    }

    // 測試
    public static void main(String[] args) {
        BSTValidationAndRepair solver = new BSTValidationAndRepair();

        // 建立一棵錯誤BST (2節點交換)
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);

        System.out.println("是否為有效BST: " + solver.isValidBST(root));

        List<TreeNode> violations = solver.findViolations(root);
        System.out.print("違規節點: ");
        for (TreeNode n : violations) System.out.print(n.val + " ");
        System.out.println();

        solver.recoverTree(root);
        System.out.println("修復後是否為有效BST: " + solver.isValidBST(root));

        int toRemove = solver.nodesToRemove(root);
        System.out.println("需要移除節點數以成為有效BST: " + toRemove);
    }
}
