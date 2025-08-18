// RBTreeIteratorExercise.java
import java.util.*;

public class RBTreeIteratorExercise {
    static class Node {
        int key; Node left,right,parent;
        Node(int k){key=k;}
    }

    static class RBTree {
        Node root;
        public void insertBST(int k) { root = insertRec(root, k, null); }
        private Node insertRec(Node node, int k, Node parent){
            if (node==null) { Node n=new Node(k); n.parent=parent; return n;}
            if (k < node.key) node.left = insertRec(node.left,k,node);
            else node.right = insertRec(node.right,k,node);
            return node;
        }
    }

    // inorder iterator
    static class InorderIterator implements Iterator<Integer> {
        Deque<Node> stack = new ArrayDeque<>();
        Node curr;
        InorderIterator(Node root) {
            curr = root;
            pushLeft(root);
        }
        private void pushLeft(Node node) {
            while (node!=null) { stack.push(node); node=node.left; }
        }
        public boolean hasNext(){ return !stack.isEmpty(); }
        public Integer next(){
            Node node = stack.pop();
            pushLeft(node.right);
            return node.key;
        }
    }

    // reverse iterator
    static class ReverseIterator implements Iterator<Integer> {
        Deque<Node> stack = new ArrayDeque<>();
        ReverseIterator(Node root) { pushRight(root); }
        private void pushRight(Node node) {
            while (node!=null) { stack.push(node); node=node.right; }
        }
        public boolean hasNext(){ return !stack.isEmpty(); }
        public Integer next() {
            Node node = stack.pop();
            pushRight(node.left);
            return node.key;
        }
    }

    // range iterator [lo,hi]
    static class RangeIterator implements Iterator<Integer> {
        Deque<Node> stack = new ArrayDeque<>();
        int lo, hi;
        RangeIterator(Node root, int lo, int hi){ this.lo=lo; this.hi=hi; pushLeftInRange(root); }
        private void pushLeftInRange(Node node){
            while (node!=null) {
                if (node.key < lo) node = node.right;
                else { stack.push(node); node = node.left; }
            }
        }
        public boolean hasNext(){ return !stack.isEmpty(); }
        public Integer next(){
            Node node = stack.pop();
            int key = node.key;
            if (node.right != null) pushLeftInRange(node.right);
            if (key < lo || key > hi) return next();
            return key;
        }
    }

    public static void main(String[] args) {
        RBTree tree = new RBTree();
        int[] arr = {20,10,5,15,30,25,35};
        for (int v: arr) tree.insertBST(v);

        System.out.print("Inorder: ");
        InorderIterator it = new InorderIterator(tree.root);
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();

        System.out.print("Reverse: ");
        ReverseIterator rit = new ReverseIterator(tree.root);
        while (rit.hasNext()) System.out.print(rit.next() + " ");
        System.out.println();

        System.out.print("Range [10,30]: ");
        RangeIterator rIt = new RangeIterator(tree.root, 10, 30);
        while (rIt.hasNext()) System.out.print(rIt.next() + " ");
        System.out.println();
    }
}
