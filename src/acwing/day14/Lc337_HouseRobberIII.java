package acwing.day14;

import acwing.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: mobing
 * @Date: 2020/9/6 18:46
 * @Description:337. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一+个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 *
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */
public class Lc337_HouseRobberIII {
    Map<TreeNode, Integer> f = new HashMap<TreeNode, Integer>(); // 选中
    Map<TreeNode, Integer> g = new HashMap<TreeNode, Integer>(); // 不选中

    //递归做法
    public int rob(TreeNode root) {
        if(root == null){
            return 0;
        }
        int res = root.val;
        if(root.left!=null){
            res += rob(root.left.left);
            res += rob(root.left.right);
        }
        if(root.right!=null){
            res += rob(root.right.right);
            res += rob(root.right.left);
        }
        int robLeft = rob(root.left);
        int robRight = rob(root.right);
        res = Math.max(res, robLeft + robRight);
        return res;
    }

    //dp做法
    public int rob2(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));// 选与不选的最大值
    }
    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        dfs(node.right);
        f.put(node, node.val + g.getOrDefault(node.left, 0) + g.getOrDefault(node.right, 0));//选 val+子节点不能选
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0))
                + Math.max(f.getOrDefault(node.right, 0), g.getOrDefault(node.right, 0)));//不选 子节点最大值（选与不选不受限制）
    }


    public int rob3(TreeNode root) {
        int[] rootStatus = dfs3(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    // 数组表示选与不选的值 省略hash映射的空间
    public int[] dfs3(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs3(node.left);
        int[] r = dfs3(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }

    public static void main(String[] args) {

    }
}
