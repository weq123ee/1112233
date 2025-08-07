import java.util.*;

public class MultiWayTreeAndDecisionTree {
    // 多路樹節點
    static class MultiWayNode {
        String val;
        List<MultiWayNode> children;

        MultiWayNode(String v) {
            val = v;
            children = new ArrayList<>();
        }
    }

    // 建立簡單多路樹（示範）
    public MultiWayNode createSampleTree() {
        MultiWayNode root = new MultiWayNode("root");
        MultiWayNode c1 = new MultiWayNode("child1");
        MultiWayNode c2 = new MultiWayNode("child2");
        MultiWayNode c3 = new MultiWayNode("child3");
        root.children.add(c1);
        root.children.add(c2);
        root.children.add(c3);

        c1.children.add(new MultiWayNode("child1.1"));
        c1.children.add(new MultiWayNode("child1.2"));
        c2.children.add(new MultiWayNode("child2.1"));

        return root;
    }

    // 多路樹深度優先走訪
    public void dfs(MultiWayNode node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        for (MultiWayNode child : node.children) {
            dfs(child);
        }
    }

    // 多路樹廣度優先走訪
    public void bfs(MultiWayNode root) {
        if (root == null) return;
        Queue<MultiWayNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            MultiWayNode node = queue.poll();
            System.out.print(node.val + " ");
            for (MultiWayNode child : node.children) {
                queue.offer(child);
            }
        }
    }

    // 模擬簡單決策樹（猜數字遊戲）
    static class DecisionNode {
        String question;
        DecisionNode yesBranch, noBranch;

        DecisionNode(String q) {
            question = q;
        }
    }

    public void playGuessingGame() {
        DecisionNode root = new DecisionNode("Is it an animal?");
        root.yesBranch = new DecisionNode("Is it a cat?");
        root.noBranch = new DecisionNode("Is it a car?");

        root.yesBranch.yesBranch = new DecisionNode("It's a cat!");
        root.yesBranch.noBranch = new DecisionNode("It's a dog!");

        root.noBranch.yesBranch = new DecisionNode("It's a car!");
        root.noBranch.noBranch = new DecisionNode("It's a bicycle!");

        Scanner sc = new Scanner(System.in);
        DecisionNode current = root;

        while (current.yesBranch != null && current.noBranch != null) {
            System.out.println(current.question + " (yes/no)");
            String answer = sc.nextLine().trim().toLowerCase();
            if (answer.equals("yes")) {
                current = current.yesBranch;
            } else if (answer.equals("no")) {
                current = current.noBranch;
            } else {
                System.out.println("請輸入 yes 或 no");
            }
        }
        System.out.println(current.question);
    }

    // 計算多路樹高度
    public int height(MultiWayNode node) {
        if (node == null) return 0;
        int maxChildHeight = 0;
        for (MultiWayNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, height(child));
        }
        return maxChildHeight + 1;
    }

    // 計算多路樹每個節點的度數(子節點數量)
    public void printDegrees(MultiWayNode node) {
        if (node == null) return;
        System.out.println(node.val + " 的度數: " + node.children.size());
        for (MultiWayNode child : node.children) {
            printDegrees(child);
        }
    }

    // 測試
    public static void main(String[] args) {
        MultiWayTreeAndDecisionTree solver = new MultiWayTreeAndDecisionTree();

        System.out.println("=== 多路樹深度優先走訪 ===");
        MultiWayNode root = solver.createSampleTree();
        solver.dfs(root);
        System.out.println();

        System.out.println("=== 多路樹廣度優先走訪 ===");
        solver.bfs(root);
        System.out.println();

        System.out.println("=== 多路樹高度 ===");
        System.out.println(solver.height(root));

        System.out.println("=== 多路樹節點度數 ===");
        solver.printDegrees(root);

        System.out.println("=== 簡單決策樹遊戲 ===");
        solver.playGuessingGame();
    }
}
