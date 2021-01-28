package acwing_leetcode.day04;

/**
 * @author mobing
 * @Description
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 *
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 *
 * 判断你是否能够到达最后一个位置。
 *
 * 示例 1:
 *
 * 输入: [2,3,1,1,4]
 * 输出: true
 * 解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
 * 示例 2:
 *
 * 输入: [3,2,1,0,4]
 * 输出: false
 * 解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @create 2020-09-05 16:47
 */
public class Lc55_JumpGame {
    // 从后往前看能否可达 i+A[i] >= last
    public boolean canJump(int[] A) {
        int n = A.length;
        int last = n-1;
        for(int i=n-2; i>=0; i--){
            if(i+A[i] >= last)
                last = i;
        }
        return last == 0;
    }
    //也可以使用动态规划。。能否到达当前n-1取决于前面只要有一个可达i 并且i可以跳到n-1，这个复杂度On2 重复计算多了

    // 贪心方法，最远可达距离
    public boolean canJump2(int[] A) {
        // write your code here
        if(A == null || A.length < 1){
            return false;
        }
        int len = A.length;
        int reach = 0;
        // 可以优化，如果第一步就reach很大 没必要继续遍历 i<reach && reach <=len
        for (int i=0; i<len; i++) {
            if(i>reach){
                return false;
            }
            reach = Math.max(i+A[i], reach);
        }
        return true;
    }

    public class Solution {
        public boolean canJump(int[] nums) {
            int n = nums.length;
            int rightmost = 0;
            for (int i = 0; i < n; ++i) {
                if (i <= rightmost) {
                    rightmost = Math.max(rightmost, i + nums[i]);
                    if (rightmost >= n - 1) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
