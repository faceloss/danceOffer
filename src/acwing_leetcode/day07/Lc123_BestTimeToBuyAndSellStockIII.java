package acwing_leetcode.day07;

/**
 * @Auther: mobing
 * @Date: 2020/9/6 20:46
 * @Description:123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 */
public class Lc123_BestTimeToBuyAndSellStockIII {
    // 必须在购买前出售掉手中股票 2次买卖

    public static void main(String[] args) {
        int[] pries =  {1,2,3,4,5};
        Lc123_BestTimeToBuyAndSellStockIII de = new Lc123_BestTimeToBuyAndSellStockIII();
        System.out.println(de.maxProfit(pries));
        System.out.println(de.maxProfit2(pries));
        System.out.println(de.maxProfit3(pries));

    }
    public int maxProfit(int[] prices) {
        // write your code here
        //这题需要记录状态 也就是当前持有股票的状态
        //pre work
        if (prices == null || prices.length == 0 || prices.length == 1) {
            return 0;
        }
        int n = prices.length;
        int i, j;
        int[][] dp = new int[n + 1][5 + 1];// 前i天处于状态j的最大收益
        //init
        //前0天的获利(5种状态)
        dp[0][1] = 0;
        dp[0][2] = dp[0][3] = dp[0][4] = dp[0][5] = Integer.MIN_VALUE;
        //dp work
        for (i = 1; i <= n; i++) {
            //1,3,5状态是表示当前没有持有股票
            for (j = 1; j < 6; j += 2) {
                dp[i][j] = dp[i - 1][j];//昨天也没持有股票 当时昨天为0时候会加上无穷小
                if (j > 1 && i > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                    //j不能是1，因为j是1前面一天就不可能持有，只有3对应2,5对应4有，并且昨天不是0天 3、5表示今天刚卖（有可能获得收益）
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }
            //状态2、4持有股票
            for (j = 2; j < 6; j += 2) {
                dp[i][j] = dp[i - 1][j - 1];//2 4 状态对应的是持有股票和昨天没有持有股票今天才买入
                if (i > 1 && dp[i - 1][j] != Integer.MIN_VALUE) {
                    //昨天也持有股票 那么就是需要将昨天到今天的收益加上
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }
                if (j > 2 && i > 1 && dp[i - 1][j - 2] != Integer.MIN_VALUE) {
                    //昨天持有股票，但是今天卖完就买 只有j为4才可以
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 2] + prices[i - 1] - prices[i - 2]);
                }

            }
        }
        //return work
        return Math.max(Math.max(dp[n][1], dp[n][3]), dp[n][5]);
    }

    public int maxProfit3(int[] prices) {
        /**
         对于任意一天考虑四个变量:
         fstBuy: 在该天第一次买入股票可获得的最大收益
         fstSell: 在该天第一次卖出股票可获得的最大收益
         secBuy: 在该天第二次买入股票可获得的最大收益
         secSell: 在该天第二次卖出股票可获得的最大收益
         分别对四个变量进行相应的更新, 最后secSell就是最大
         收益值(secSell >= fstSell)
         **/
        int fstBuy = Integer.MIN_VALUE, fstSell = 0;
        int secBuy = Integer.MIN_VALUE, secSell = 0;
        for (int p : prices) {
            fstBuy = Math.max(fstBuy, -p);
            fstSell = Math.max(fstSell, fstBuy + p);
            secBuy = Math.max(secBuy, fstSell - p);
            secSell = Math.max(secSell, secBuy + p);
        }
        return secSell;
    }

    public int maxProfit2(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;
        int k = 2;
        int[][][] dp = new int[len][k + 1][2];

        for (int i = 0; i < len; i++) {
            for (int j = k; j > 0; j--) {
                if (i == 0) {
                    //第i天，还有j次，手里没有股票，当i=0，手里没股票，最大利润为0
                    dp[i][j][0] = 0;
                    //当i=0，手里有股票，因为还没有盈利，最大利润为 负prices[i]
                    dp[i][j][1] = -prices[i];
                    continue;
                }
                //今天手里没股票，比较MAX（前一天可能没股票，前一天有股票但是今天卖出去了，卖出去就有利润，所以+ prices[i]）
                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                //今天手里有股票，比较MAX（前一天可能有股票，前一天没股票但是今天买了，买了就有成本，所以- prices[i]）
                dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][k][0];
    }
}
