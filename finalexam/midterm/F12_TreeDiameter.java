import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class F12_TreeDiameter {
    static int diameter = 0;

    public static int height(TreeNode root) {
        if (root == null) return 0;
        int lh = height(root.left);
        int rh = height(root.right);
        diameter = Math.max(diameter, lh + rh);
        return Math.max(lh, rh) + 1;
    }

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        TreeNode root = buildTree(input);
        height(root);
        System.out.println("Diameter: " + diameter);
    }
}
