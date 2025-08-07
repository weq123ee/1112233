import java.util.*;

public class TreeReconstruction {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int v) { val = v; }
    }

    // 根據前序和中序重建二元樹
    public TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for(int i=0; i<inorder.length; i++) inMap.put(inorder[i], i);
        return buildPreIn(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1, inMap);
    }

    private TreeNode buildPreIn(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd, Map<Integer,Integer> inMap) {
        if(preStart > preEnd || inStart > inEnd) return null;
        int rootVal = pre[preStart];
        TreeNode root = new TreeNode(rootVal);
        int inRoot = inMap.get(rootVal);
        int numsLeft = inRoot - inStart;

        root.left = buildPreIn(pre, preStart+1, preStart+numsLeft, in, inStart, inRoot-1, inMap);
        root.right = buildPreIn(pre, preStart+numsLeft+1, preEnd, in, inRoot+1, inEnd, inMap);
        return root;
    }

    // 根據後序和中序重建二元樹
    public TreeNode buildTreePostIn(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for(int i=0; i<inorder.length; i++) inMap.put(inorder[i], i);
        return buildPostIn(postorder, 0, postorder.length-1, inorder, 0, inorder.length-1, inMap);
    }

    private TreeNode buildPostIn(int[] post, int postStart, int postEnd, int[] in, int inStart, int inEnd, Map<Integer,Integer> inMap) {
        if(postStart > postEnd || inStart > inEnd) return null;
        int rootVal = post[postEnd];
        TreeNode root = new TreeNode(rootVal);
        int inRoot = inMap.get(rootVal);
        int numsLeft = inRoot - inStart;

        root.left = buildPostIn(post, postStart, postStart+numsLeft-1, in, inStart, inRoot-1, inMap);
        root.right = buildPostIn(post, postStart+numsLeft, postEnd-1, in, inRoot+1, inEnd, inMap);
        return root;
    }

    // 根據層序重建完全二元樹
    public TreeNode buildTreeLevelOrder(Integer[] levelOrder) {
        if(levelOrder.length == 0 || levelOrder[0] == null) return null;
        TreeNode root = new TreeNode(levelOrder[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int i = 1;
        while(i < levelOrder.length) {
            TreeNode current = queue.poll();
            if(levelOrder[i] != null) {
                current.left = new TreeNode(levelOrder[i]);
                queue.offer(current.left);
            }
            i++;
            if(i < levelOrder.length && levelOrder[i] != null) {
                current.right = new TreeNode(levelOrder[i]);
                queue.offer(current.right);
            }
            i++;
        }
        return root;
    }

    // 驗證樹是否正確(中序走訪應該是排序的)
    public boolean validateTree(TreeNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);
        for(int i=1; i<inorder.size(); i++) {
            if(inorder.get(i) <= inorder.get(i-1)) return false;
        }
        return true;
    }

    private void inorderTraversal(TreeNode node, List<Integer> res) {
        if(node == null) return;
        inorderTraversal(node.left, res);
        res.add(node.val);
        inorderTraversal(node.right, res);
    }

    // 測試
    public static void main(String[] args) {
        TreeReconstruction tr = new TreeReconstruction();

        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root1 = tr.buildTreePreIn(preorder, inorder);
        System.out.println("前序+中序重建後，是否為有效BST？" + tr.validateTree(root1));

        int[] postorder = {9,15,7,20,3};
        TreeNode root2 = tr.buildTreePostIn(postorder, inorder);
        System.out.println("後序+中序重建後，是否為有效BST？" + tr.validateTree(root2));

        Integer[] levelOrder = {1,2,3,4,5,6,7};
        TreeNode root3 = tr.buildTreeLevelOrder(levelOrder);
        System.out.println("層序重建後，是否為有效BST？" + tr.validateTree(root3));
    }
}
