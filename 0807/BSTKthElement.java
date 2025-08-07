import java.util.*;

public class BSTKthElement {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    TreeNode root;

    public void insert(int val) {
        root = insertRec(root, val);
    }

    private TreeNode insertRec(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);
        if (val < node.val) node.left = insertRec(node.left, val);
        else node.right = insertRec(node.right, val);
        return node;
    }

    // 找出第k小元素
    public int kthSmallest(int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        if (k <= 0 || k > list.size()) throw new IllegalArgumentException("k不合法");
        return list.get(k - 1);
    }

    // 找出第k大元素
    public int kthLargest(int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        if (k <= 0 || k > list.size()) throw new IllegalArgumentException("k不合法");
        return list.get(list.size() - k);
    }

    // 找出第k小到第j小元素（包含）
    public List<Integer> kthRange(int k, int j) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        if (k <= 0 || j > list.size() || k > j) throw new IllegalArgumentException("區間不合法");
        return list.subList(k - 1, j);
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }

    // 支援動態插入刪除（示範插入已在上面）

    public void delete(int val) {
        root = deleteRec(root, val);
    }

    private TreeNode deleteRec(TreeNode root, int val) {
        if (root == null) return null;
        if (val < root.val) root.left = deleteRec(root.left, val);
        else if (val > root.val) root.right = deleteRec(root.right, val);
        else {
            // 找到節點
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;
            // 兩個子節點：找右子樹最小節點代替
            root.val = minValue(root.right);
            root.right = deleteRec(root.right, root.val);
        }
        return root;
    }

    private int minValue(TreeNode node) {
        int minv = node.val;
        while (node.left != null) {
            minv = node.left.val;
            node = node.left;
        }
        return minv;
    }

    // 測試
    public static void main(String[] args) {
        BSTKthElement bst = new BSTKthElement();
        int[] values = {20, 8, 22, 4, 12, 10, 14};
        for (int v : values) bst.insert(v);

        System.out.println("第3小元素: " + bst.kthSmallest(3));
        System.out.println("第2大元素: " + bst.kthLargest(2));
        System.out.println("第2小到第5小元素: " + bst.kthRange(2, 5));

        bst.delete(10);
        System.out.println("刪除10後，第3小元素: " + bst.kthSmallest(3));
    }
}
