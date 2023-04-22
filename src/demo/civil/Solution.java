package demo.civil;
import acwing_leetcode.TreeNode;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Solution {
    public static void main(String[] args) {
        ConcurrentHashMap c = new ConcurrentHashMap<>();
        c.values().iterator();
        TreeNode n1= new TreeNode(3);
        TreeNode n2= new TreeNode(2);
        TreeNode n3= new TreeNode(4);
        n1.right = n3;
        n1.left = n2;
        TreeNode res = new Solution().KthNode(n1,3);
        System.out.println(res.val);
    }
    TreeNode KthNode(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        ArrayList<TreeNode> res = new ArrayList<>();
        while(root !=null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            TreeNode cur = stack.pop();
            res.add(cur);
            root = cur.right;
        }
        return res.get(k-1);
    }
}