// RBDeleteFixupExercise.java
import java.util.*;

public class RBDeleteFixupExercise {
    enum Color { RED, BLACK }
    static class Node {
        int key;
        Color color;
        Node left,right,parent;
        Node(int k){key=k; color=Color.RED;}
    }

    static class RBTree {
        Node nil = new Node(-1);
        Node root;

        RBTree(){ nil.color=Color.BLACK; nil.left=nil.right=nil.parent=null; root=nil; }

        public void insert(int key) {
            Node z = new Node(key);
            z.left=z.right=z.parent=nil;
            Node y = nil;
            Node x = root;
            while (x!=nil) {
                y = x;
                if (z.key < x.key) x = x.left;
                else x = x.right;
            }
            z.parent = y;
            if (y==nil) root = z;
            else if (z.key < y.key) y.left = z;
            else y.right = z;
            z.left=z.right=nil; z.color=Color.RED;
            insertFixup(z);
        }

        private void insertFixup(Node z) {
            while (z.parent.color==Color.RED) {
                if (z.parent == z.parent.parent.left) {
                    Node y = z.parent.parent.right;
                    if (y.color==Color.RED) {
                        z.parent.color=Color.BLACK; y.color=Color.BLACK; z.parent.parent.color=Color.RED; z=z.parent.parent;
                    } else {
                        if (z==z.parent.right) { z=z.parent; leftRotate(z); }
                        z.parent.color=Color.BLACK; z.parent.parent.color=Color.RED; rightRotate(z.parent.parent);
                    }
                } else {
                    Node y = z.parent.parent.left;
                    if (y.color==Color.RED) {
                        z.parent.color=Color.BLACK; y.color=Color.BLACK; z.parent.parent.color=Color.RED; z=z.parent.parent;
                    } else {
                        if (z==z.parent.left) { z=z.parent; rightRotate(z); }
                        z.parent.color=Color.BLACK; z.parent.parent.color=Color.RED; leftRotate(z.parent.parent);
                    }
                }
            }
            root.color = Color.BLACK;
        }

        private void transplant(Node u, Node v) {
            if (u.parent==nil) root = v;
            else if (u==u.parent.left) u.parent.left = v;
            else u.parent.right = v;
            v.parent = u.parent;
        }

        public void delete(int key) {
            Node z = search(root, key);
            if (z==nil) return;
            Node y = z;
            Color yOriginalColor = y.color;
            Node x;
            if (z.left==nil) {
                x = z.right;
                transplant(z, z.right);
            } else if (z.right==nil) {
                x = z.left;
                transplant(z, z.left);
            } else {
                y = minimum(z.right);
                yOriginalColor = y.color;
                x = y.right;
                if (y.parent == z) x.parent = y;
                else {
                    transplant(y, y.right);
                    y.right = z.right; y.right.parent = y;
                }
                transplant(z, y);
                y.left = z.left; y.left.parent = y;
                y.color = z.color;
            }
            if (yOriginalColor == Color.BLACK) deleteFixup(x);
        }

        private void deleteFixup(Node x) {
            while (x!=root && x.color==Color.BLACK) {
                if (x==x.parent.left) {
                    Node w = x.parent.right;
                    if (w.color==Color.RED) {
                        w.color=Color.BLACK; x.parent.color=Color.RED; leftRotate(x.parent); w=x.parent.right;
                    }
                    if (w.left.color==Color.BLACK && w.right.color==Color.BLACK) {
                        w.color=Color.RED; x=x.parent;
                    } else {
                        if (w.right.color==Color.BLACK) {
                            w.left.color=Color.BLACK; w.color=Color.RED; rightRotate(w); w=x.parent.right;
                        }
                        w.color = x.parent.color;
                        x.parent.color = Color.BLACK;
                        w.right.color = Color.BLACK;
                        leftRotate(x.parent);
                        x = root;
                    }
                } else {
                    Node w = x.parent.left;
                    if (w.color==Color.RED) {
                        w.color=Color.BLACK; x.parent.color=Color.RED; rightRotate(x.parent); w=x.parent.left;
                    }
                    if (w.right.color==Color.BLACK && w.left.color==Color.BLACK) {
                        w.color=Color.RED; x=x.parent;
                    } else {
                        if (w.left.color==Color.BLACK) {
                            w.right.color=Color.BLACK; w.color=Color.RED; leftRotate(w); w=x.parent.left;
                        }
                        w.color = x.parent.color;
                        x.parent.color = Color.BLACK;
                        w.left.color = Color.BLACK;
                        rightRotate(x.parent);
                        x = root;
                    }
                }
            }
            x.color = Color.BLACK;
        }

        private Node search(Node node, int key) {
            while (node!=nil && node.key!=key) {
                node = key < node.key ? node.left : node.right;
            }
            return node;
        }

        private Node minimum(Node node) {
            while (node.left!=nil) node = node.left;
            return node;
        }

        private void leftRotate(Node x) {
            Node y = x.right;
            x.right = y.left;
            if (y.left!=nil) y.left.parent = x;
            y.parent = x.parent;
            if (x.parent==nil) root = y;
            else if (x==x.parent.left) x.parent.left = y;
            else x.parent.right = y;
            y.left = x; x.parent = y;
        }

        private void rightRotate(Node y) {
            Node x = y.left;
            y.left = x.right;
            if (x.right!=nil) x.right.parent = y;
            x.parent = y.parent;
            if (y.parent==nil) root = x;
            else if (y==y.parent.right) y.parent.right = x;
            else y.parent.left = x;
            x.right = y; y.parent = x;
        }

        // debug: inorder print
        public void inorder(Node n) {
            if (n==nil) return;
            inorder(n.left);
            System.out.println(n.key + "(" + n.color + ")");
            inorder(n.right);
        }
    }

    public static void main(String[] args) {
        RBTree t = new RBTree();
        int[] keys = {20,10,30,5,15,25,35,2,7};
        for (int k: keys) t.insert(k);
        System.out.println("Before delete:");
        t.inorder(t.root);

        System.out.println("Delete 10");
        t.delete(10);
        System.out.println("After delete:");
        t.inorder(t.root);
    }
}
