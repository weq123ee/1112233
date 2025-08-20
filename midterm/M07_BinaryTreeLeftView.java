import java.util.*;

public class M07_BinaryTreeLeftView {
    static class Node {
        int val; Node left, right;
        Node(int v) {val=v;}
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n == 0) { System.out.println("LeftView:"); return; }
        int[] vals = new int[n];
        for (int i=0;i<n;i++) vals[i]=sc.nextInt();
        Node root = buildTree(vals);
        List<Integer> res = leftView(root);
        System.out.print("LeftView:");
        for (int v: res) System.out.print(" " + v);
    }

    static Node buildTree(int[] arr) {
        if (arr.length==0 || arr[0]==-1) return null;
        Node root = new Node(arr[0]);
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        int i=1;
        while(!q.isEmpty() && i<arr.length){
            Node cur=q.poll();
            if(i<arr.length && arr[i]!=-1){cur.left=new Node(arr[i]); q.add(cur.left);} i++;
            if(i<arr.length && arr[i]!=-1){cur.right=new Node(arr[i]); q.add(cur.right);} i++;
        }
        return root;
    }

    static List<Integer> leftView(Node root){
        List<Integer> ans=new ArrayList<>();
        if(root==null) return ans;
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            int sz=q.size();
            for(int i=0;i<sz;i++){
                Node cur=q.poll();
                if(i==0) ans.add(cur.val);
                if(cur.left!=null) q.add(cur.left);
                if(cur.right!=null) q.add(cur.right);
            }
        }
        return ans;
    }
}
