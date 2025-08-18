public class AVLDeleteExercise {
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

        Node delete(Node root, int key) {
            if (root == null) return root;

            if (key < root.key) root.left = delete(root.left, key);
            else if (key > root.key) root.right = delete(root.right, key);
            else {
                if ((root.left == null) || (root.right == null)) {
                    Node temp = (root.left != null) ? root.left : root.right;

                    if (temp == null) {
                        root = null;
                    } else {
                        root = temp;
                    }
                } else {
                    Node temp = minValueNode(root.right);
                    root.key = temp.key;
                    root.right = delete(root.right, temp.key);
                }
            }

            if (root == null) return root;

            root.height = 1 + Math.max(height(root.left), height(root.right));
            return rebalance(root, key);
        }

        Node minValueNode(Node node) {
            Node current = node;
            while (current.left != null) current = current.left;
            return current;
        }

        Node rebalance(Node node, int key) {
            int balance = getBalance(node);

            if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);
            if (balance > 1 && getBalance(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);
            if (balance < -1 && getBalance(node.right) > 0) {
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

        void inorder(Node node) {
            if (node != null) {
                inorder(node.left);
                System.out.print(node.key + " ");
                inorder(node.right);
            }
        }
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] values = {9, 5, 10, 0, 6, 11, -1, 1, 2};

        for (int v : values) {
            tree.root = tree.insert(tree.root, v);
        }

        System.out.print("原始樹 (中序): ");
        tree.inorder(tree.root);
        System.out.println();

        tree.root = tree.delete(tree.root, 10);
        System.out.print("刪除 10 後 (中序): ");
        tree.inorder(tree.root);
        System.out.println();
    }
}
