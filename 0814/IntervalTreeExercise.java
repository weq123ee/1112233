// IntervalTreeExercise.java
import java.util.*;

public class IntervalTreeExercise {
    static class Interval {
        int lo, hi;
        Interval(int l,int h){lo=l;hi=h;}
        public String toString(){ return "["+lo+","+hi+"]"; }
    }

    static class Node {
        Interval interval;
        int max; // max hi in subtree
        Node left,right,parent;
        Node(Interval it){ interval=it; max=it.hi; }
    }

    static class IntervalTree {
        Node root;
        private void update(Node n) {
            if (n==null) return;
            n.max = n.interval.hi;
            if (n.left!=null) n.max = Math.max(n.max, n.left.max);
            if (n.right!=null) n.max = Math.max(n.max, n.right.max);
        }

        public void insert(int lo,int hi) {
            Interval it = new Interval(lo,hi);
            Node z=new Node(it);
            Node y=null; Node x=root;
            while (x!=null) { y=x; if (lo < x.interval.lo) x = x.left; else x = x.right; }
            z.parent = y;
            if (y==null) root = z;
            else if (lo < y.interval.lo) y.left = z;
            else y.right = z;
            // update path
            Node cur = z;
            while (cur!=null) { update(cur); cur = cur.parent; }
        }

        public List<Interval> searchOverlapping(int lo,int hi) {
            List<Interval> res = new ArrayList<>();
            searchOverlappingRec(root, lo, hi, res);
            return res;
        }

        private void searchOverlappingRec(Node node, int lo, int hi, List<Interval> res) {
            if (node==null) return;
            if (doOverlap(node.interval.lo,node.interval.hi, lo,hi)) res.add(node.interval);
            if (node.left!=null && node.left.max >= lo) searchOverlappingRec(node.left, lo, hi, res);
            if (node.right!=null && node.interval.lo <= hi) searchOverlappingRec(node.right, lo, hi, res);
        }

        public List<Interval> searchPoint(int x) {
            List<Interval> res = new ArrayList<>();
            searchPointRec(root, x, res);
            return res;
        }

        private void searchPointRec(Node node, int x, List<Interval> res) {
            if (node==null) return;
            if (node.interval.lo <= x && x <= node.interval.hi) res.add(node.interval);
            if (node.left!=null && node.left.max >= x) searchPointRec(node.left, x, res);
            if (node.right!=null && node.interval.lo <= x) searchPointRec(node.right, x, res);
        }

        private boolean doOverlap(int a1,int b1,int a2,int b2) {
            return a1 <= b2 && a2 <= b1;
        }

        // inorder print
        public void inorder(Node n) {
            if (n==null) return;
            inorder(n.left);
            System.out.println(n.interval + " max=" + n.max);
            inorder(n.right);
        }
    }

    public static void main(String[] args) {
        IntervalTree it = new IntervalTree();
        it.insert(15,20);
        it.insert(10,30);
        it.insert(17,19);
        it.insert(5,20);
        it.insert(12,15);
        it.insert(30,40);

        System.out.println("Inorder:");
        it.inorder(it.root);

        System.out.println("Overlap with [14,16]: " + it.searchOverlapping(14,16));
        System.out.println("Point 16 intervals: " + it.searchPoint(16));
    }
}
