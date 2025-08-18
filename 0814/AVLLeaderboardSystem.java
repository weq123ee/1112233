import java.util.*;

public class AVLLeaderboardSystem {
    // Node stores (score, name) as key. We order by score DESC, name ASC for tie-breaker.
    static class Node {
        String name;
        int score;
        int height;
        int size; // subtree size
        Node left, right;

        Node(String name, int score) {
            this.name = name;
            this.score = score;
            this.height = 1;
            this.size = 1;
        }
    }

    static class Leaderboard {
        private Node root;
        // map for quick lookup of player's current score (to support update)
        private Map<String, Integer> playerScore;

        public Leaderboard() {
            root = null;
            playerScore = new HashMap<>();
        }

        // compare keys: return >0 if (a) should go right of (b) in BST ordering
        // but we want tree ordered by score DESC -> larger score goes to left
        private int cmp(String nameA, int scoreA, String nameB, int scoreB) {
            if (scoreA != scoreB) return Integer.compare(scoreB, scoreA); // bigger score first
            return nameA.compareTo(nameB); // tie-breaker
        }

        private int height(Node n) { return n == null ? 0 : n.height; }
        private int size(Node n) { return n == null ? 0 : n.size; }

        private void update(Node n) {
            if (n != null) {
                n.height = 1 + Math.max(height(n.left), height(n.right));
                n.size = 1 + size(n.left) + size(n.right);
            }
        }

        private Node rightRotate(Node y) {
            Node x = y.left;
            Node T2 = x.right;
            x.right = y;
            y.left = T2;
            update(y);
            update(x);
            return x;
        }

        private Node leftRotate(Node x) {
            Node y = x.right;
            Node T2 = y.left;
            y.left = x;
            x.right = T2;
            update(x);
            update(y);
            return y;
        }

        private int getBalance(Node n) {
            return n == null ? 0 : height(n.left) - height(n.right);
        }

        // insert node (name,score)
        private Node insertNode(Node node, String name, int score) {
            if (node == null) return new Node(name, score);
            int c = cmp(name, score, node.name, node.score);
            if (c < 0) node.left = insertNode(node.left, name, score);
            else if (c > 0) node.right = insertNode(node.right, name, score);
            else {
                // same (name,score) shouldn't happen for distinct name, but if same, ignore
                return node;
            }

            update(node);
            int balance = getBalance(node);

            // rotations
            if (balance > 1 && cmp(name, score, node.left.name, node.left.score) < 0) {
                return rightRotate(node);
            }
            if (balance < -1 && cmp(name, score, node.right.name, node.right.score) > 0) {
                return leftRotate(node);
            }
            if (balance > 1 && cmp(name, score, node.left.name, node.left.score) > 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && cmp(name, score, node.right.name, node.right.score) < 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }

            return node;
        }

        // find and remove a node by (name, score)
        private Node deleteNode(Node node, String name, int score) {
            if (node == null) return null;
            int c = cmp(name, score, node.name, node.score);
            if (c < 0) node.left = deleteNode(node.left, name, score);
            else if (c > 0) node.right = deleteNode(node.right, name, score);
            else {
                // found
                if (node.left == null || node.right == null) {
                    Node t = node.left != null ? node.left : node.right;
                    if (t == null) {
                        node = null;
                    } else node = t;
                } else {
                    // get inorder successor (smallest in right) — but maintain our ordering
                    Node succ = minNode(node.right);
                    node.name = succ.name;
                    node.score = succ.score;
                    node.right = deleteNode(node.right, succ.name, succ.score);
                }
            }
            if (node == null) return null;
            update(node);

            int balance = getBalance(node);
            if (balance > 1 && getBalance(node.left) >= 0) return rightRotate(node);
            if (balance > 1 && getBalance(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            if (balance < -1 && getBalance(node.right) <= 0) return leftRotate(node);
            if (balance < -1 && getBalance(node.right) > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
            return node;
        }

        private Node minNode(Node n) {
            while (n.left != null) n = n.left;
            return n;
        }

        // public APIs

        // add player with score (if player exists, treat as update)
        public void addPlayer(String name, int score) {
            if (playerScore.containsKey(name)) {
                updatePlayer(name, score);
                return;
            }
            root = insertNode(root, name, score);
            playerScore.put(name, score);
        }

        // update player's score
        public void updatePlayer(String name, int newScore) {
            Integer old = playerScore.get(name);
            if (old != null) {
                root = deleteNode(root, name, old);
            }
            root = insertNode(root, name, newScore);
            playerScore.put(name, newScore);
        }

        // get rank of player (1-based): 1 is top
        public int getRank(String name) {
            Integer score = playerScore.get(name);
            if (score == null) return -1;
            // rank = number of nodes with strictly greater (score,name) ordering + 1
            return 1 + countGreater(root, name, score);
        }

        // counts how many nodes are "strictly before" (higher rank) the given key
        private int countGreater(Node node, String name, int score) {
            if (node == null) return 0;
            int c = cmp(name, score, node.name, node.score);
            if (c < 0) {
                // key < node => key is before node (higher), so nodes in node.right + node itself?
                // careful: because our cmp returns <0 when (key) should go left of node
                // We want count of nodes that are "higher" than key -> nodes that come before key in in-order
                // We'll implement simpler: do an in-order traversal counting nodes whose key is "higher" (cmp < 0)
            }
            // Simpler implementation: do recursive count using cmp
            return countGreaterRec(root, name, score);
        }

        private int countGreaterRec(Node node, String name, int score) {
            if (node == null) return 0;
            // node is "higher" than (name,score) if cmp(node.name,node.score, name,score) < 0
            int cnode = cmp(node.name, node.score, name, score);
            if (cnode < 0) {
                // node is higher => count node + all nodes in left subtree (since left subtree are even higher)
                return 1 + size(node.left) + countGreaterRec(node.right, name, score);
            } else if (cnode > 0) {
                // node is lower => check left subtree
                return countGreaterRec(node.left, name, score);
            } else {
                // equal key (should be this player)
                return size(node.left);
            }
        }

        // get top k players as list of (name,score)
        public List<String> topK(int k) {
            List<String> res = new ArrayList<>();
            topKRec(root, res, k);
            return res;
        }

        private void topKRec(Node node, List<String> res, int k) {
            if (node == null || res.size() >= k) return;
            // because ordering is score DESC (left first)
            topKRec(node.left, res, k);
            if (res.size() < k) res.add(node.name + ":" + node.score);
            topKRec(node.right, res, k);
        }

        // debug: print inorder (score desc)
        public void printInOrder() {
            System.out.println("排行榜（從高到低）:");
            printRec(root);
            System.out.println();
        }

        private void printRec(Node n) {
            if (n == null) return;
            printRec(n.left);
            System.out.println(n.name + " -> " + n.score);
            printRec(n.right);
        }
    }

    // demo
    public static void main(String[] args) {
        Leaderboard lb = new Leaderboard();
        lb.addPlayer("Alice", 50);
        lb.addPlayer("Bob", 40);
        lb.addPlayer("Charlie", 60);
        lb.addPlayer("Dave", 50);

        lb.printInOrder();

        System.out.println("Top 3: " + lb.topK(3));
        System.out.println("Rank of Alice: " + lb.getRank("Alice"));
        System.out.println("Rank of Charlie: " + lb.getRank("Charlie"));

        lb.updatePlayer("Bob", 55);
        lb.printInOrder();
        System.out.println("Rank of Bob: " + lb.getRank("Bob"));
    }
}
