// TreeVisualizerExercise.java
import java.util.*;

public class TreeVisualizerExercise {
    static class Node { int key; Node left,right; Node(int k){key=k;} }

    public static void printTree(Node root) {
        List<List<String>> lines = new ArrayList<>();
        List<Node> level = new ArrayList<>();
        List<Node> next = new ArrayList<>();
        level.add(root);
        int nn = 1;
        int widest = 0;
        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;
            for (Node n : level) {
                if (n==null) {
                    line.add(null);
                    next.add(null); next.add(null);
                } else {
                    String aa = String.valueOf(n.key);
                    line.add(aa);
                    if (aa.length() > widest) widest = aa.length();
                    next.add(n.left); next.add(n.right);
                    if (n.left != null) nn++;
                    if (n.right != null) nn++;
                }
            }
            if (widest % 2 == 1) widest++;
            lines.add(line);
            level = new ArrayList<>(next);
            next.clear();
        }
        int perpiece = lines.get(lines.size()-1).size() * (widest + 4);
        for (int i=0;i<lines.size();i++) {
            List<String> line = lines.get(i);
            int hpw = (int)Math.floor(perpiece/2f) - 1;
            for (int j=0;j<line.size();j++) {
                String f = line.get(j);
                if (f==null) f="";
                int gap1 = (int)Math.ceil(hpw - f.length()/2f);
                int gap2 = (int)Math.floor(hpw - f.length()/2f);
                printSpaces(gap1);
                System.out.print(f);
                printSpaces(gap2);
            }
            System.out.println();
            perpiece /= 2;
        }
    }
    private static void printSpaces(int n) { for (int i=0;i<n;i++) System.out.print(" "); }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(15);
        root.left.left = new Node(2); root.left.right = new Node(7);
        root.right.left = new Node(12); root.right.right = new Node(20);
        printTree(root);
    }
}
