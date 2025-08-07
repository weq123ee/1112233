import java.util.*;

public class BinaryTreeBasicOperations {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    public static void main(String[] args) {
        /*
                10
               /  \
              5    15
             / \     \
            3   7     20
        */
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(20);

        int sum = sumNodes(root);
        System.out.println("節點總和: " + sum);
        System.out.println("節點平均值: " + (sum * 1.0 / countNodes(root)));

        int max = maxNode(root);
        int min = minNode(root);
        System.out.println("最大節點值: " + max);
        System.out.println("最小節點值: " + min);

        System.out.println("樹的寬度: " + maxWidth(root));
        System.out.println("是否為完全二元樹: " + isCompleteTree(root));
    }

    // 計算節點總數
    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // 節點值總和
    public static int sumNodes(TreeNode root) {
        if (root == null) return 0;
        return root.val + sumNodes(root.left) + sumNodes(root.right);
    }

    // 最大節點值
    public static int maxNode(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        return Math.max(root.val, Math.max(maxNode(root.left), maxNode(root.right)));
    }

    // 最小節點值
    public static int minNode(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        return Math.min(root.val, Math.min(minNode(root.left), minNode(root.right)));
    }

    // 計算樹的最大寬度 (每層節點數最大值)
    public static int maxWidth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            maxWidth = Math.max(maxWidth, size);
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return maxWidth;
    }

    // 判斷是否為完全二元樹
    public static boolean isCompleteTree(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                end = true;
            } else {
                if (end) return false;
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return true;
    }
}
