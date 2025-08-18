import java.util.*;

public class AVLBasicExercise {
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

            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            } else {
                return node; // 重複不插入
            }

            node.height = 1 + Math.max(height(node.left), height(node.right));

            int balance = getBalance(node);

            // LL
            if (balance > 1 && key < node.left.key) {
                return rightRotate(node);
            }
            // RR
            if (balance < -1 && key > node.right.key) {
                return leftRotate(node);
            }
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

        boolean search(Node node, int key) {
            if (node == null) return false;
            if (key == node.key) return true;
            return key < node.key ? search(node.left, key) : search(node.right, key);
        }

        boolean isValidAVL(Node node) {
            if (node == null) return true;

            int balance = getBalance(node);
            if (balance < -1 || balance > 1) return false;

            return isValidAVL(node.left) && isValidAVL(node.right);
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
        int[] values = {10, 20, 30, 40, 50, 25};

        for (int v : values) {
            tree.root = tree.insert(tree.root, v);
        }

        System.out.print("中序遍歷: ");
        tree.inorder(tree.root);
        System.out.println();

        System.out.println("搜尋 25: " + tree.search(tree.root, 25));
        System.out.println("搜尋 60: " + tree.search(tree.root, 60));
        System.out.println("樹高度: " + tree.height(tree.root));
        System.out.println("是否為有效AVL: " + tree.isValidAVL(tree.root));
    }
}
