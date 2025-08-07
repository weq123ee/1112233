import java.util.*;

public class BSTRangeQuerySystem {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    TreeNode root;

    // 插入節點
    public void insert(int val) {
        root = insertRec(root, val);
    }

    private TreeNode insertRec(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insertRec(root.left, val);
        else root.right = insertRec(root.right, val);
        return root;
    }

    // 範圍查詢：找出[min, max]的所有節點
    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryRec(root, min, max, result);
        return result;
    }

    private void rangeQueryRec(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) return;
        if (node.val > min) rangeQueryRec(node.left, min, max, result);
        if (node.val >= min && node.val <= max) result.add(node.val);
        if (node.val < max) rangeQueryRec(node.right, min, max, result);
    }

    // 範圍計數：計算[min, max]內節點數量
    public int rangeCount(int min, int max) {
        return rangeQuery(min, max).size();
    }

    // 範圍總和：計算[min, max]內節點值總和
    public int rangeSum(int min, int max) {
        List<Integer> list = rangeQuery(min, max);
        int sum = 0;
        for (int val : list) sum += val;
        return sum;
    }

    // 找出最接近給定值的節點
    public int closestValue(int target) {
        return closestValueRec(root, target, root.val);
    }

    private int closestValueRec(TreeNode node, int target, int closest) {
        if (node == null) return closest;
        if (Math.abs(node.val - target) < Math.abs(closest - target)) {
            closest = node.val;
        }
        if (target < node.val) return closestValueRec(node.left, target, closest);
        else return closestValueRec(node.right, target, closest);
    }

    // 測試程式
    public static void main(String[] args) {
        BSTRangeQuerySystem bst = new BSTRangeQuerySystem();
        int[] values = {15, 10, 20, 8, 12, 16, 25};
        for (int v : values) bst.insert(v);

        System.out.println("範圍查詢 [10, 20]: " + bst.rangeQuery(10, 20));
        System.out.println("範圍計數 [10, 20]: " + bst.rangeCount(10, 20));
        System.out.println("範圍總和 [10, 20]: " + bst.rangeSum(10, 20));
        System.out.println("最接近 18 的節點值: " + bst.closestValue(18));
    }
}
