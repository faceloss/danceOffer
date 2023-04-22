package acwing_leetcode.day07;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2021-03-14 23:53
 **/
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;
}
public class GudgeSearchComTree {


    public static void main(String[] args) {
        TreeNode n1 = new TreeNode();
        TreeNode n2 = new TreeNode();
        TreeNode n3 = new TreeNode();
        TreeNode n4 = new TreeNode();
        TreeNode n5 = new TreeNode();
        TreeNode n6 = new TreeNode();
        n1.val = 1;
        n2.val = 2;
        n3.val = 3;
        n4.val = 4;
        n5.val = 5;
        n6.val = 6;
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        GudgeSearchComTree g  = new GudgeSearchComTree();
        g.judgeIt(n1);
    }
        /**
         *
         * @param root TreeNode类 the root
         * @return bool布尔型一维数组
         */
        public boolean[] judgeIt (TreeNode root) {
            // write code here
            boolean b1 = isMidTree(root, null, null);
            boolean b2 = iscomTree(root);
            return new boolean[]{b1, b2};
        }
        //判断搜索二叉树
        public boolean isMidTree(TreeNode root, Integer low, Integer up){
            if(root == null){
                return true;
            }
            int value = root.val;
            if(low!=null && low >= value)
                return false;
            if(up!=null && up <= value)
                return false;
            return isMidTree(root.left, low, value) && isMidTree(root.right, value, up);
        }

        //判断完全二叉树
        public boolean iscomTree(TreeNode root){
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            boolean falg = true;
            while(!queue.isEmpty()){
                TreeNode cur = queue.peek();
                if(cur.left!=null){
                    queue.add(cur.left);
                }else{
                    break;
                }
                if(cur.right!=null){
                    queue.add(cur.right);
                }else{
                    break;
                }
                queue.poll();
            }
            if(queue.peek().right == null){
                queue.poll();
            }
            while(!queue.isEmpty()){
                TreeNode cur = queue.poll();
                if(cur.left == null || cur.right == null){
                    falg = false;
                }
            }
            return falg;
        }
}

//       1
//   2       3
//              6
//