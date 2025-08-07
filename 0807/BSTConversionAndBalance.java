import java.util.*;

public class BSTConversionAndBalance {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 將BST轉換為排序的雙向鏈結串列（中序線索化）
    TreeNode head = null;
    TreeNode prev = null;

    public TreeNode bstToDoublyLinkedList(TreeNode root) {
        if (root == null) return null;
        inorder(root);
        // Make it circular
        head.left = prev;
        prev.right = head;
        return head;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        if (prev == null) {
            head = node;
        } else {
            prev.right = node;
            node.left = prev;
        }
        prev = node;
        inorder(node.right);
    }

    // 將排序陣列轉換為平衡BST
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length -1);
    }

    private TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left + right)/2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBSTHelper(nums, left, mid -1);
        node.right = sortedArrayToBSTHelper(nums, mid +1, right);
        return node;
    }

    // 檢查BST是否平衡，並計算平衡因子（高度差）
    public boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }

    private int checkBalance(TreeNode node) {
        if (node == null) return 0;
        int left = checkBalance(node.left);
        if (left == -1) return -1;
        int right = checkBalance(node.right);
        if (right == -1) return -1;
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }

    // 將BST中每個節點改為所有大於等於該節點值的總和 (反向中序)
    int sum = 0;

    public void bstToGreaterSumTree(TreeNode root) {
        if (root == null) return;
        bstToGreaterSumTree(root.right);
        sum += root.val;
        root.val = sum;
        bstToGreaterSumTree(root.left);
    }

    // 測試
    public static void printInorder(TreeNode root) {
        if(root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    public static void main(String[] args) {
        BSTConversionAndBalance solver = new BSTConversionAndBalance();

        int[] sortedArr = {1,2,3,4,5,6,7};
        TreeNode bstRoot = solver.sortedArrayToBST(sortedArr);

        System.out.print("平衡BST中序: ");
        printInorder(bstRoot);
        System.out.println();

        System.out.println("是否平衡: " + solver.isBalanced(bstRoot));

        solver.bstToGreaterSumTree(bstRoot);
        System.out.print("Greater Sum Tree中序: ");
        printInorder(bstRoot);
        System.out.println();

        TreeNode head = solver.bstToDoublyLinkedList(bstRoot);
        System.out.print("雙向鏈結串列節點值（順序）: ");
        TreeNode curr = head;
        do {
            System.out.print(curr.val + " ");
            curr = curr.right;
        } while (curr != head);
        System.out.println();
    }
}
