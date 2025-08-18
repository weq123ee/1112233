import java.util.*;

public class AVLRangeQueryExercise {
    static class Node {
        int key, height;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.height = 1;
        }
    }

    static class AVLTree {
        Node root;

        int height(Node node) {
            return node == null ? 0 : node.height;
        }

        int getBalance(Node node) {
            return node == null ? 0 : height(node.left) - height(node.right);
        }

        Node insert(Node node, int key) {
            if (node == null) return new Node(key);

            if (key < node.key) node.left = insert(node.left, key);
            else if (key > node.key) node.right = insert(node.right, key);
            else return node;

            node.height = 1 + Math.max(height(node.left), height(node.right));
            return rebalance(node, key);
        }

        Node rebalance(Node node, int key) {
            int balance = getBalance(node);

            if (balance > 1 && key < node.left.key) return rightRotate(node);
            if (balance < -1 && key > node.right.key) return leftRotate(node);
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            return node;
        }

        Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;
            x.right = y;
            y.left = T2;
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            return x;
        }

        Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;
            y.left = x;
            x.right = T2;
            x.height = Math.max(height(x.left), height(x.right)) + 1;
            y.height = Math.max(height(y.left), height(y.right)) + 1;
            return y;
        }

        List<Integer> rangeQuery(Node node, int min, int max) {
            List<Integer> res = new ArrayList<>();
            rangeQueryHelper(node, min, max, res);
            return res;
        }

        void rangeQueryHelper(Node node, int min, int max, List<Integer> res) {
            if (node == null) return;

            if (min < node.key) rangeQueryHelper(node.left, min, max, res);
            if (min <= node.key && node.key <= max) res.add(node.key);
            if (max > node.key) rangeQueryHelper(node.right, min, max, res);
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        for (int v : values) {
            tree.root = tree.insert(tree.root, v);
        }

        System.out.println("範圍查詢 [10, 25]: " + tree.rangeQuery(tree.root, 10, 25));
    }
}
