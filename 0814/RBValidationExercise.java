// RBValidationExercise.java
import java.util.*;

public class RBValidationExercise {
    enum Color { RED, BLACK }

    static class Node {
        int key;
        Color color;
        Node left, right, parent;
        Node(int k, Color c) { key=k; color=c; }
    }

    // Simple RB tree builder for tests
    static class RBTree {
        Node root;

        // utility: insert naive as BST then color manually (not full RB insert)
        public Node bstInsert(int key, Color color) {
            Node z = new Node(key, color);
            Node y = null;
            Node x = root;
            while (x != null) {
                y = x;
                if (key < x.key) x = x.left;
                else x = x.right;
            }
            z.parent = y;
            if (y == null) root = z;
            else if (key < y.key) y.left = z;
            else y.right = z;
            return z;
        }
    }

    // Validation result with message
    static class Result {
        boolean ok;
        String message;
        Result(boolean ok, String m){ this.ok=ok; this.message=m; }
        public String toString(){ return (ok?"OK: ":"ERR: ") + message; }
    }

    // Validate RB properties
    public static Result validateRB(Node root) {
        if (root == null) return new Result(true, "Empty tree is valid");

        // 1. Root is black
        if (root.color != Color.BLACK) return new Result(false, "Root is not black");

        // 2. No red node has red child
        Result r = validateRedRule(root);
        if (!r.ok) return r;

        // 3. All paths from root to leaf have same number of black nodes
        int blackCount = -1;
        try {
            blackCount = checkBlackHeight(root, 0);
        } catch (IllegalStateException e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true, "Valid RB tree (black-height = " + blackCount + ")");
    }

    private static Result validateRedRule(Node node) {
        if (node == null) return new Result(true, "ok");
        if (node.color == Color.RED) {
            if ((node.left != null && node.left.color == Color.RED) ||
                (node.right != null && node.right.color == Color.RED)) {
                return new Result(false, "Red node " + node.key + " has red child");
            }
        }
        Result l = validateRedRule(node.left);
        if (!l.ok) return l;
        return validateRedRule(node.right);
    }

    // returns black height or throws if mismatch
    private static int checkBlackHeight(Node node, int currBlack) {
        if (node == null) {
            return currBlack;
        }
        if (node.color == Color.BLACK) currBlack++;
        int leftBH = checkBlackHeight(node.left, currBlack);
        int rightBH = checkBlackHeight(node.right, currBlack);
        if (leftBH != rightBH) throw new IllegalStateException(
            "Black-height mismatch at node " + node.key + ": left=" + leftBH + " right=" + rightBH);
        return leftBH;
    }

    // Build and test
    public static void main(String[] args) {
        RBTree t = new RBTree();
        // build tiny valid RB tree:
        Node n1 = t.bstInsert(10, Color.BLACK);
        Node n2 = t.bstInsert(5, Color.RED);
        Node n3 = t.bstInsert(15, Color.RED);
        n1.left = n2; n2.parent = n1;
        n1.right = n3; n3.parent = n1;

        System.out.println(validateRB(t.root));

        // introduce violation: make root red
        t.root.color = Color.RED;
        System.out.println(validateRB(t.root));
    }
}
