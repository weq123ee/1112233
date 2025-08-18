// RBInsertFixupExercise.java
import java.util.*;

public class RBInsertFixupExercise {
    enum Color { RED, BLACK }

    static class Node {
        int key;
        Color color;
        Node left, right, parent;
        Node(int k) { key=k; color=Color.RED; }
    }

    static class RBTree {
        Node nil = new Node(-1); // sentinel
        Node root;

        RBTree() {
            nil.color = Color.BLACK;
            nil.left = nil.right = nil.parent = null;
            root = nil;
        }

        // standard BST insert then fixup
        public Node insert(int key) {
            Node z = new Node(key);
            z.left = z.right = z.parent = nil;
            Node y = nil;
            Node x = root;
            while (x != nil) {
                y = x;
                if (z.key < x.key) x = x.left;
                else x = x.right;
            }
            z.parent = y;
            if (y == nil) root = z;
            else if (z.key < y.key) y.left = z;
            else y.right = z;

            z.left = z.right = nil;
            z.color = Color.RED;
            insertFixup(z);
            return z;
        }

        private void insertFixup(Node z) {
            while (z.parent != nil && z.parent.color == Color.RED) {
                if (z.parent == z.parent.parent.left) {
                    Node y = z.parent.parent.right;
                    if (y != null && y.color == Color.RED) {
                        // case 1
                        z.parent.color = Color.BLACK;
                        y.color = Color.BLACK;
                        z.parent.parent.color = Color.RED;
                        z = z.parent.parent;
                    } else {
                        if (z == z.parent.right) {
                            // case 2
                            z = z.parent;
                            leftRotate(z);
                        }
                        // case 3
                        z.parent.color = Color.BLACK;
                        z.parent.parent.color = Color.RED;
                        rightRotate(z.parent.parent);
                    }
                } else {
                    Node y = z.parent.parent.left;
                    if (y != null && y.color == Color.RED) {
                        z.parent.color = Color.BLACK;
                        y.color = Color.BLACK;
                        z.parent.parent.color = Color.RED;
                        z = z.parent.parent;
                    } else {
                        if (z == z.parent.left) {
                            z = z.parent;
                            rightRotate(z);
                        }
                        z.parent.color = Color.BLACK;
                        z.parent.parent.color = Color.RED;
                        leftRotate(z.parent.parent);
                    }
                }
            }
            root.color = Color.BLACK;
        }

        private void leftRotate(Node x) {
            Node y = x.right;
            x.right = y.left;
            if (y.left != nil) y.left.parent = x;
            y.parent = x.parent;
            if (x.parent == nil) root = y;
            else if (x == x.parent.left) x.parent.left = y;
            else x.parent.right = y;
            y.left = x;
            x.parent = y;
        }

        private void rightRotate(Node y) {
            Node x = y.left;
            y.left = x.right;
            if (x.right != nil) x.right.parent = y;
            x.parent = y.parent;
            if (y.parent == nil) root = x;
            else if (y == y.parent.right) y.parent.right = x;
            else y.parent.left = x;
            x.right = y;
            y.parent = x;
        }

        // inorder print with colors
        public void inorderPrint(Node node) {
            if (node == nil) return;
            inorderPrint(node.left);
            System.out.println(node.key + "(" + node.color + ")");
            inorderPrint(node.right);
        }
    }

    public static void main(String[] args) {
        RBTree tree = new RBTree();
        int[] keys = {10, 20, 30, 15, 25, 5, 1};
        for (int k : keys) tree.insert(k);

        System.out.println("中序列 (key(color)):");
        tree.inorderPrint(tree.root);
    }
}
