import java.util.*;

public class M09_AVLValidate {
    static class Node {
        int val; Node left,right;
        Node(int v){val=v;}
    }

    static class Info {
        boolean valid; int height;
        Info(boolean v,int h){valid=v;height=h;}
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++) arr[i]=sc.nextInt();
        Node root=buildTree(arr);
        if(!isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE))
            System.out.println("Invalid BST");
        else if(!isAVL(root).valid)
            System.out.println("Invalid AVL");
        else
            System.out.println("Valid");
    }

    static Node buildTree(int[] arr){
        if(arr.length==0||arr[0]==-1) return null;
        Node root=new Node(arr[0]);
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        int i=1;
        while(!q.isEmpty()&&i<arr.length){
            Node cur=q.poll();
            if(i<arr.length&&arr[i]!=-1){cur.left=new Node(arr[i]); q.add(cur.left);} i++;
            if(i<arr.length&&arr[i]!=-1){cur.right=new Node(arr[i]); q.add(cur.right);} i++;
        }
        return root;
    }

    static boolean isBST(Node root,int min,int max){
        if(root==null) return true;
        if(root.val<=min||root.val>=max) return false;
        return isBST(root.left,min,root.val)&&isBST(root.right,root.val,max);
    }

    static Info isAVL(Node root){
        if(root==null) return new Info(true,0);
        Info L=isAVL(root.left),R=isAVL(root.right);
        boolean ok=L.valid&&R.valid&&Math.abs(L.height-R.height)<=1;
        return new Info(ok,1+Math.max(L.height,R.height));
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * - BST 驗證：每節點訪問一次 → O(n)。
 * - AVL 驗證：後序遞迴，每節點計算高度 → O(n)。
 * - 總計 O(n)。
 */
