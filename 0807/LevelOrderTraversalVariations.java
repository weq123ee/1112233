import java.util.*;

public class LevelOrderTraversalVariations {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 將每層節點分別存入不同List
    public List<List<Integer>> levelOrderPerLevel(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(level);
        }
        return res;
    }

    // 之字形層序走訪（奇數層左到右，偶數層右到左）
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) level.addLast(node.val);
                else level.addFirst(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(level);
            leftToRight = !leftToRight;
        }
        return res;
    }

    // 只列印每層最後一個節點
    public List<Integer> lastNodePerLevel(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int lastVal = 0;
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                lastVal = node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(lastVal);
        }
        return res;
    }

    // 垂直層序走訪（根據水平位置分組）
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            TreeNode node = p.node;
            int hd = p.hd;

            map.computeIfAbsent(hd, k -> new ArrayList<>()).add(node.val);

            if (node.left != null) queue.offer(new Pair(node.left, hd - 1));
            if (node.right != null) queue.offer(new Pair(node.right, hd + 1));
        }

        res.addAll(map.values());
        return res;
    }

    static class Pair {
        TreeNode node;
        int hd;
        Pair(TreeNode n, int h) { node = n; hd = h; }
    }

    // 測試
    public static void main(String[] args) {
        LevelOrderTraversalVariations treeOps = new LevelOrderTraversalVariations();
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("每層節點列表: " + treeOps.levelOrderPerLevel(root));
        System.out.println("之字形層序走訪: " + treeOps.zigzagLevelOrder(root));
        System.out.println("每層最後一個節點: " + treeOps.lastNodePerLevel(root));
        System.out.println("垂直層序走訪: " + treeOps.verticalOrder(root));
    }
}
