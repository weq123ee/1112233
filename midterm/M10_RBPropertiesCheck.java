import java.util.*;

public class M10_RBPropertiesCheck {
    static class Node {
        int val; char color; Node left,right;
        Node(int v,char c){val=v;color=c;}
    }

    static boolean violation=false;
    static String msg="";

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] vals=new int[n];
        char[] cols=new char[n];
        for(int i=0;i<n;i++){vals[i]=sc.nextInt(); cols[i]=sc.next().charAt(0);}
        Node[] nodes=new Node[n];
        for(int i=0;i<n;i++) if(vals[i]!=-1) nodes[i]=new Node(vals[i],cols[i]);
        for(int i=0;i<n;i++){
            if(nodes[i]!=null){
                int l=2*i+1,r=2*i+2;
                if(l<n && vals[l]!=-1) nodes[i].left=nodes[l];
                if(r<n && vals[r]!=-1) nodes[i].right=nodes[r];
            }
        }
        Node root=nodes[0];
        if(root==null){System.out.println("RB Valid");return;}
        if(root.color!='B'){System.out.println("RootNotBlack");return;}
        int bh=check(root);
        if(!violation) System.out.println("RB Valid");
        else System.out.println(msg);
    }

    static int check(Node node){
        if(node==null) return 1;
        if(node.color=='R'){
            if((node.left!=null && node.left.color=='R')||(node.right!=null && node.right.color=='R')){
                violation=true; msg="RedRedViolation"; return -1;
            }
        }
        int L=check(node.left),R=check(node.right);
        if(L==-1||R==-1) return -1;
        if(L!=R){violation=true; msg="BlackHeightMismatch"; return -1;}
        return L+(node.color=='B'?1:0);
    }
}
