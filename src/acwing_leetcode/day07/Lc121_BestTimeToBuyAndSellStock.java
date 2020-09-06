package acwing_leetcode.day07;

/**
 * @Auther: mobing
 * @Date: 2020/9/6 20:45
 * @Description:121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 *
 * 注意：你不能在买入股票前卖出股票。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 *
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class Lc121_BestTimeToBuyAndSellStock {
    //  前i天的最大收益 = max{前i-1天的最大收益，第i天的价格-前i-1天中的最小价格}
    public int maxProfit(int[] prices) {
        if(prices ==null || prices.length == 0){
            return 0;
        }
        int len = prices.length;
        int[] dp = new int[len];//第i天卖能获得的最大收益
        dp[0] = 0;
        int max = 0;
        int min = prices[0];
        for(int i=1;i<len;i++){
            dp[i] = prices[i] - min;
            min = Math.min(min, prices[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int maxProfit2(int[] prices) {
        if(prices==null || prices.length==0){
            return 0;
        }
        int min=Integer.MAX_VALUE;
        int res=0;
        for(int temp:prices){
            min=temp<min?temp:min;
            res=(temp-min)>res?temp-min:res;
        }
        return res;
    }
}
