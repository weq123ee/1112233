public class AVLRotationExercise {
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

        Node insert(Node node, int key) {
            if (node == null) return new Node(key);

            if (key < node.key) node.left = insert(node.left, key);
            else if (key > node.key) node.right = insert(node.right, key);
            else return node;

            node.height = 1 + Math.max(height(node.left), height(node.right));

            int balance = getBalance(node);

            // LL
            if (balance > 1 && key < node.left.key) return rightRotate(node);

            // RR
            if (balance < -1 && key > node.right.key) return leftRotate(node);

            // LR
            if (balance > 1 && key > node.left.key) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // RL
            if (balance < -1 && key < node.right.key) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        int getBalance(Node node) {
            return node == null ? 0 : height(node.left) - height(node.right);
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

        // 測試 RR Case
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 30);

        System.out.print("RR Case 中序: ");
        tree.inorder(tree.root);
        System.out.println();

        // 測試 LL Case
        tree = new AVLTree();
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 20);
        tree.root = tree.insert(tree.root, 10);

        System.out.print("LL Case 中序: ");
        tree.inorder(tree.root);
        System.out.println();

        // 測試 LR Case
        tree = new AVLTree();
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 20);

        System.out.print("LR Case 中序: ");
        tree.inorder(tree.root);
        System.out.println();

        // 測試 RL Case
        tree = new AVLTree();
        tree.root = tree.insert(tree.root, 10);
        tree.root = tree.insert(tree.root, 30);
        tree.root = tree.insert(tree.root, 20);

        System.out.print("RL Case 中序: ");
        tree.inorder(tree.root);
        System.out.println();
    }
}
