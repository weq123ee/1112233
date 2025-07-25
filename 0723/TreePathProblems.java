import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class TreePathProblems {
    public static void findPaths(TreeNode root, List<Integer> path, List<List<Integer>> res) {
        if (root == null) return;
        path.add(root.val);
        if (root.left == null && root.right == null)
            res.add(new ArrayList<>(path));
        else {
            findPaths(root.left, path, res);
            findPaths(root.right, path, res);
        }
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        List<List<Integer>> res = new ArrayList<>();
        findPaths(root, new ArrayList<>(), res);
        System.out.println(res);
    }
}