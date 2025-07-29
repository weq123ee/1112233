import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class F09_BinaryTreeLeftView {
    public static TreeNode buildTree(String[] data) {
        if (data.length == 0 || data[0].equals("-1")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(data[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int i = 1;
        while (i < data.length) {
            TreeNode node = q.poll();
            if (!data[i].equals("-1")) {
                node.left = new TreeNode(Integer.parseInt(data[i]));
                q.add(node.left);
            }
            i++;
            if (i < data.length && !data[i].equals("-1")) {
                node.right = new TreeNode(Integer.parseInt(data[i]));
                q.add(node.right);
            }
            i++;
        }
        return root;
    }

    public static void printLeftView(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == 0) System.out.print(node.val + " ");
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        TreeNode root = buildTree(input);
        System.out.print("LeftView: ");
        printLeftView(root);
    }
}
