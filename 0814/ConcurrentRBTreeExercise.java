// ConcurrentRBTreeExercise.java
import java.util.concurrent.locks.*;
import java.util.*;

public class ConcurrentRBTreeExercise {
    static class Node { int key; Node left,right; Node(int k){key=k;} }

    static class ConcurrentBST {
        private Node root;
        private final ReadWriteLock rw = new ReentrantReadWriteLock();

        public void insert(int key) {
            rw.writeLock().lock();
            try {
                root = insertRec(root, key);
            } finally {
                rw.writeLock().unlock();
            }
        }
        private Node insertRec(Node node,int k) {
            if (node==null) return new Node(k);
            if (k < node.key) node.left = insertRec(node.left,k);
            else if (k > node.key) node.right = insertRec(node.right,k);
            return node;
        }

        public boolean contains(int key) {
            rw.readLock().lock();
            try {
                Node cur = root;
                while (cur!=null) {
                    if (key==cur.key) return true;
                    cur = key < cur.key ? cur.left : cur.right;
                }
                return false;
            } finally {
                rw.readLock().unlock();
            }
        }

        public void inorderPrint() {
            rw.readLock().lock();
            try {
                inorder(root); System.out.println();
            } finally {
                rw.readLock().unlock();
            }
        }

        private void inorder(Node n) {
            if (n==null) return;
            inorder(n.left);
            System.out.print(n.key + " ");
            inorder(n.right);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentBST tree = new ConcurrentBST();
        // writer thread
        Thread writer = new Thread(() -> {
            for (int i=0;i<20;i++) {
                tree.insert(i);
                try { Thread.sleep(10); } catch (InterruptedException e) {}
            }
        });
        // reader threads
        Runnable readerTask = () -> {
            for (int i=0;i<10;i++) {
                tree.inorderPrint();
                try { Thread.sleep(20); } catch (InterruptedException e) {}
            }
        };
        Thread r1 = new Thread(readerTask);
        Thread r2 = new Thread(readerTask);

        writer.start(); r1.start(); r2.start();
        writer.join(); r1.join(); r2.join();
    }
}
