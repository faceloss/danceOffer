package acwing_leetcode;

/**
 * @Auther: mobing
 * @Date: 2020/9/6 18:10
 * @Description:213. 打家劫舍 II
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [2,3,2]
 * 输出: 3
 * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * 示例 2:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 */
public class Lc213_HouseRobberII {
    public int houseRobber2(int[] nums) {
        // write your code here
        if(nums==null ||nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return nums[0];
        }
        int len=nums.length;
        int i,res;
        int[] dp=new int[len];//前i个可以获得的最大收益
        dp[0]=0;
        //由于引入了环的问题也就是 0 和 最后一个只能偷一个
        //remove first 第一个不偷，则dp[1]确定是nums[1] 不影响转移方程
        dp[1]=nums[1];
        for (i=2;i<len;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        res=dp[len-1];
        //remove last 偷第一个则dp[1]确定是nums[0] 影响转移方程dp[i]不能偷i 像是dp[i-1]与dp[i-2]+nums[i-1]
        dp[1]=nums[0];
        for(i=2;i<len;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i-1]);
        }
        if(dp[len-1]>res){
            res=dp[len-1];
        }
        return res;
    }
}
