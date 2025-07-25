import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class TreeReconstruction {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) map.put(inorder[i], i);
        return helper(preorder, 0, preorder.length - 1, 0, map);
    }

    private static TreeNode helper(int[] pre, int ps, int pe, int is, Map<Integer, Integer> map) {
        if (ps > pe) return null;
        TreeNode root = new TreeNode(pre[ps]);
        int idx = map.get(pre[ps]);
        int leftSize = idx - is;
        root.left = helper(pre, ps + 1, ps + leftSize, is, map);
        root.right = helper(pre, ps + leftSize + 1, pe, idx + 1, map);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = buildTree(preorder, inorder);
        System.out.println("Tree built with root: " + root.val);
    }
}