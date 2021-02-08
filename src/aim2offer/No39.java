package aim2offer;

/**
 * 输入一颗二叉树的根节点，求该树的深度。
 * 从根节点到叶节点依次经过的结点（含根、叶结点）形成树的一条路径，
 * 最长路径的长度为树的深度。
 */

public class No39 {

    public static void main(String[] args) {
        BinaryTreeNode node1 = new BinaryTreeNode(1);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node3 = new BinaryTreeNode(3);
        BinaryTreeNode node4 = new BinaryTreeNode(4);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node7 = new BinaryTreeNode(7);
        BinaryTreeNode node11 = new BinaryTreeNode(11);
        BinaryTreeNode node12 = new BinaryTreeNode(12);

        node1.setLchildNode(node2);
        node1.setRchildNode(node3);
        node2.setLchildNode(node4);
        node2.setRchildNode(node5);
        node3.setLchildNode(node6);
        node3.setRchildNode(node7);
        node4.setLchildNode(node11);
        node4.setRchildNode(node12);

        //       1
        //    2      3
        //   4  5   6   7
        // 11 12
        BinaryTreeNode res = getLast(node1);
        System.out.println(res.getData());
    }

    public static int treeDepth(BinaryTreeNode root) {

        if (root == null) return 0;

        int left = treeDepth(root.getLchildNode());

        int right = treeDepth(root.getRchildNode());

        return (left > right) ? left + 1 : right + 1;

    }
    public static BinaryTreeNode getLast(BinaryTreeNode root){
        int left = treeDepth(root.getLchildNode());
        int right = treeDepth(root.getRchildNode());
        if(left == 1 && right==1){
            return root.getRchildNode();
        }
        if(left == 1 && right==0){
            return root.getLchildNode();
        }
        if(left > right){
            return getLast(root.getLchildNode());
        }else{
            return getLast(root.getRchildNode());
        }
    }

}
