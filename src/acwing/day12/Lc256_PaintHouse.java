package acwing.day12;

/**
 * @Auther: mobing
 * @Date: 2020/9/5 18:18
 * @Description:
 * 256. 粉刷房子
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 *
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x 3 的矩阵来表示的。
 *
 * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。
 *
 * 注意：
 *
 * 所有花费均为正整数。
 *
 * 示例：
 *
 * 输入: [[17,2,17],[16,16,5],[14,3,19]]
 * 输出: 10
 * 解释: 将 0 号房子粉刷成蓝色，1 号房子粉刷成绿色，2 号房子粉刷成蓝色。
 *      最少花费: 2 + 5 + 3 = 10。
 */
public class Lc256_PaintHouse {
    public int minCost(int[][] costs) {
        // write your code here
        //1.分析状态2.分析转移方程3.定义初始化和边界4.返回结果
        int n=costs.length;//表示一共有n栋房子
        int[][] dp=new int[n+1][3];
        int res;
        //init
        for (int i=0; i<3;i++ ){
            dp[0][i]=0;
        }
        //dp
        for(int i=1;i<=n;i++){
            for(int j=0;j<3;j++){
                if(j==0)
                    dp[i][j]=costs[i-1][j]+Math.min(dp[i-1][1],dp[i-1][2]);
                if(j==1)
                    dp[i][j]=costs[i-1][j]+Math.min(dp[i-1][0],dp[i-1][2]);
                if(j==2)
                    dp[i][j]=costs[i-1][j]+Math.min(dp[i-1][0],dp[i-1][1]);
            }
        }
        res=Math.min(dp[n][0],Math.min(dp[n][1],dp[n][2]));
        return res;
    }

    public int minCost2(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n+1][3];
        dp[0][0] = 0;
        dp[0][1] = 0;
        dp[0][2] = 0;
        int res = Integer.MAX_VALUE;
        for(int i=1; i<=n; i++){
            dp[i][0] = Math.min(dp[i-1][1]+costs[i-1][0], dp[i-1][2]+costs[i-1][0]);
            dp[i][1] = Math.min(dp[i-1][0]+costs[i-1][1], dp[i-1][2]+costs[i-1][1]);
            dp[i][2] = Math.min(dp[i-1][0]+costs[i-1][2], dp[i-1][1]+costs[i-1][2]);
        }
        res = Math.min(Math.min(dp[n][0],dp[n][1]),dp[n][2]);
        return res;
    }
}
