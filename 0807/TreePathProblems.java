import java.util.*;

public class TreePathProblems {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 找出從根到所有葉節點的路徑
    public List<List<Integer>> rootToLeafPaths(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(root, path, res);
        return res;
    }

    private void dfs(TreeNode node, List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null) {
            res.add(new ArrayList<>(path));
        } else {
            dfs(node.left, path, res);
            dfs(node.right, path, res);
        }
        path.remove(path.size() - 1);
    }

    // 判斷是否存在根到葉路徑和為目標值
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 找出根到葉最大和路徑值
    public int maxRootToLeafSum(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.left == null && root.right == null) return root.val;
        int leftMax = maxRootToLeafSum(root.left);
        int rightMax = maxRootToLeafSum(root.right);
        return root.val + Math.max(leftMax, rightMax);
    }

    // 計算任意兩節點間最大路徑和（樹的直徑值和節點和最大和）
    int maxPathSumResult = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxPathSumHelper(root);
        return maxPathSumResult;
    }

    private int maxPathSumHelper(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathSumHelper(node.left));
        int right = Math.max(0, maxPathSumHelper(node.right));
        int sum = node.val + left + right;
        maxPathSumResult = Math.max(maxPathSumResult, sum);
        return node.val + Math.max(left, right);
    }

    // 測試
    public static void main(String[] args) {
        TreePathProblems solver = new TreePathProblems();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        System.out.println("所有根到葉路徑: " + solver.rootToLeafPaths(root));
        System.out.println("是否存在根到葉路徑和為8: " + solver.hasPathSum(root, 8));
        System.out.println("根到葉最大和: " + solver.maxRootToLeafSum(root));
        System.out.println("任意兩節點最大路徑和: " + solver.maxPathSum(root));
    }
}
