import java.util.*;

class BSTNode {
    int val;
    BSTNode left, right;
    BSTNode(int val) { this.val = val; }
}

public class BSTRangeQuery {
    public static ArrayList<Integer> rangeQuery(BSTNode root, int min, int max) {
        ArrayList<Integer> result = new ArrayList<>();
        inOrder(root, min, max, result);
        return result;
    }

    private static void inOrder(BSTNode node, int min, int max, List<Integer> list) {
        if (node == null) return;
        if (node.val > min) inOrder(node.left, min, max, list);
        if (node.val >= min && node.val <= max) list.add(node.val);
        if (node.val < max) inOrder(node.right, min, max, list);
    }

    public static void main(String[] args) {
        BSTNode root = new BSTNode(20);
        root.left = new BSTNode(10);
        root.right = new BSTNode(30);
        root.left.left = new BSTNode(5);
        root.left.right = new BSTNode(15);
        root.right.left = new BSTNode(25);
        root.right.right = new BSTNode(35);

        ArrayList<Integer> result = rangeQuery(root, 12, 27);
        System.out.println("Range [12, 27]: " + result);
    }
}