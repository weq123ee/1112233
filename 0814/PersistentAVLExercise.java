import java.util.*;

public class PersistentAVLExercise {
    // Immutable node for persistence
    static class Node {
        final int key;
        final Node left, right;
        final int height;
        final int size;

        Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.height = 1 + Math.max(height(left), height(right));
            this.size = 1 + size(left) + size(right);
        }

        static int height(Node n) { return n == null ? 0 : n.height; }
        static int size(Node n) { return n == null ? 0 : n.size; }
    }

    // Persistent AVL manager
    static class PersistentAVL {
        private List<Node> versions; // roots of each version

        public PersistentAVL() {
            versions = new ArrayList<>();
            versions.add(null); // version 0: empty
        }

        public Node getRoot(int version) {
            return versions.get(version);
        }

        public int currentVersion() {
            return versions.size() - 1;
        }

        // insert key into specified version, create new version, return new version index
        public int insert(int version, int key) {
            Node root = getRoot(version);
            Node newRoot = insertRec(root, key);
            versions.add(newRoot);
            return versions.size() - 1;
        }

        private Node insertRec(Node node, int key) {
            if (node == null) return new Node(key, null, null);
            if (key < node.key) {
                Node newLeft = insertRec(node.left, key);
                Node curr = new Node(node.key, newLeft, node.right);
                return rebalance(curr);
            } else if (key > node.key) {
                Node newRight = insertRec(node.right, key);
                Node curr = new Node(node.key, node.left, newRight);
                return rebalance(curr);
            } else {
                // duplicate: ignore, return same node (no new allocation)
                return node;
            }
        }

        private Node rebalance(Node node) {
            int balance = Node.height(node.left) - Node.height(node.right);
            if (balance > 1) {
                if (Node.height(node.left.left) >= Node.height(node.left.right)) {
                    return rotateRight(node);
                } else {
                    Node newLeft = rotateLeft(node.left);
                    Node curr = new Node(node.key, newLeft, node.right);
                    return rotateRight(curr);
                }
            } else if (balance < -1) {
                if (Node.height(node.right.right) >= Node.height(node.right.left)) {
                    return rotateLeft(node);
                } else {
                    Node newRight = rotateRight(node.right);
                    Node curr = new Node(node.key, node.left, newRight);
                    return rotateLeft(curr);
                }
            }
            return node;
        }

        private Node rotateRight(Node y) {
            Node x = y.left;
            Node T2 = x.right;
            return new Node(x.key, x.left, new Node(y.key, T2, y.right));
        }

        private Node rotateLeft(Node x) {
            Node y = x.right;
            Node T2 = y.left;
            return new Node(y.key, new Node(x.key, x.left, T2), y.right);
        }

        // contains check on a version
        public boolean contains(int version, int key) {
            Node node = getRoot(version);
            while (node != null) {
                if (key == node.key) return true;
                node = key < node.key ? node.left : node.right;
            }
            return false;
        }

        // in-order traversal of a version (collect keys)
        public List<Integer> inorder(int version) {
            List<Integer> res = new ArrayList<>();
            inorderRec(getRoot(version), res);
            return res;
        }

        private void inorderRec(Node node, List<Integer> res) {
            if (node == null) return;
            inorderRec(node.left, res);
            res.add(node.key);
            inorderRec(node.right, res);
        }
    }

    // demo
    public static void main(String[] args) {
        PersistentAVL pavl = new PersistentAVL();

        int v0 = pavl.currentVersion(); // 0
        int v1 = pavl.insert(v0, 10); // version1
        int v2 = pavl.insert(v1, 20); // version2
        int v3 = pavl.insert(v2, 5);  // version3

        System.out.println("version " + v1 + " inorder: " + pavl.inorder(v1)); // [10]
        System.out.println("version " + v2 + " inorder: " + pavl.inorder(v2)); // [10,20]
        System.out.println("version " + v3 + " inorder: " + pavl.inorder(v3)); // [5,10,20]

        System.out.println("version " + v2 + " contains 5? " + pavl.contains(v2, 5)); // false
        System.out.println("version " + v3 + " contains 5? " + pavl.contains(v3, 5)); // true
    }
}
