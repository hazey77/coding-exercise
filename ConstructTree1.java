/*
 * Construct Binary Tree from Inorder and Postorder Traversal
 * /

public class ConstructTree1 {
	
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length==0 || postorder.length==0 || inorder.length!=postorder.length) {
            return null;
        }
        return buildHelper(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1);
    }
    
    public TreeNode buildHelper(int[] inorder, int start1, int end1, int[] postorder, int start2, int end2) {
        if (start1>end1) {
            return null;
        }
        if (start1==end1) {
            TreeNode n = new TreeNode(inorder[start1]);
            return n;
        }
        TreeNode root = new TreeNode(postorder[end2]);
        int pivot = -1;
        for (int i=start1; i<=end1; i++) {
            if (inorder[i]==postorder[end2]) {
                pivot = i;
                break;
            }
        }
        root.left = buildHelper(inorder, start1, pivot-1, postorder, start2, start2+pivot-start1-1);
        root.right = buildHelper(inorder, pivot+1, end1, postorder, start2+pivot-start1, end2-1);
        return root;
    }
    
	public static void main(String[] args) {
		ConstructTree1 test = new ConstructTree1();
		int[] inorder = new int[]{1, 2, 3, 4, 5};
		int[] postorder = new int[]{4, 3, 5, 2, 1};
		TreeNode root = test.buildTree(inorder, postorder);
	}

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

