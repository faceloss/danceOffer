package java_base.test;

import acwing_leetcode.TreeNode;

import java.util.ArrayList;
public class Solution {
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);
        System.out.println("Hello World!");
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.right=t3;
        t1.left=t2;
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        TreeNode t8 = new TreeNode(10);
        TreeNode t9 = new TreeNode(11);
        t7.left = t8;
        t7.right=t9;
        t2.right = t5;
        t2.left = t4;
        t3.left = t6;
        t3.right = t7;
        System.out.println(new Solution().getSum(t1));
    }
    ArrayList<String> res ;
    public int getSum(TreeNode root){
        if(null == root){
            return 0;
        }
        res = new ArrayList<>();
        dfs(root, new StringBuilder());
        int sum = 0;
        for(String item : res){
            sum += Integer.parseInt(item);
        }
        return sum;

    }
    public void dfs(TreeNode root, StringBuilder ss){
        if(root == null){
            return ;
        }
        ss.append(root.val);
        if(root.left == null && root.right == null){
            res.add(ss.toString());
            return;
        }
        if(root.left!=null){
            dfs(root.left, ss);
            ss.deleteCharAt(ss.length()-1);
        }
        if(root.right!=null){
            dfs(root.right, ss);
            ss.deleteCharAt(ss.length()-1);
        }
    }

}