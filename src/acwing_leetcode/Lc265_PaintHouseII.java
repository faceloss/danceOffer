package acwing_leetcode;

/**
 * @Auther: mobing
 * @Date: 2020/9/6 17:17
 * @Description:265. 粉刷房子 II
 * 假如有一排房子，共 n 个，每个房子可以被粉刷成 k 种颜色中的一种，你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 *
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。每个房子粉刷成不同颜色的花费是以一个 n x k 的矩阵来表示的。
 *
 * 例如，costs[0][0] 表示第 0 号房子粉刷成 0 号颜色的成本花费；costs[1][2] 表示第 1 号房子粉刷成 2 号颜色的成本花费，以此类推。请你计算出粉刷完所有房子最少的花费成本。
 *
 * 注意：
 *
 * 所有花费均为正整数。
 *
 * 示例：
 *
 * 输入: [[1,5,3],[2,9,4]]
 * 输出: 5
 * 解释: 将 0 号房子粉刷成 0 号颜色，1 号房子粉刷成 2 号颜色。最少花费: 1 + 4 = 5;
 *      或者将 0 号房子粉刷成 2 号颜色，1 号房子粉刷成 0 号颜色。最少花费: 3 + 2 = 5.
 * 进阶：
 * 您能否在 O(nk) 的时间复杂度下解决此问题？
 *
 */
public class Lc265_PaintHouseII {
    public int minCostII(int[][] costs) {
        // write your code here
        if(costs==null || costs.length==0){
            return 0;
        }
        int m=costs.length;
        int n=costs[0].length;
        int[][] dp=new int[m+1][n];
        int i,j,k;
        int min1,min2;//最小值和次小值，可以省略掉重复的运算（对每种颜色都要算就是k2次，通过记录信息降低复杂度）
        int j1=0,j2=0;
        //init
        for (i=0;i<n ;i++ ){
            dp[0][i]=0;
        }
        //dp
        for(i=1;i<=m;i++){
            min1=min2=Integer.MAX_VALUE;
            for(j=0;j<n;j++){
                if(dp[i-1][j]<min1){
                    //将最小值坐标j给j1,在更新的过程中把j1给j2,min1给min2（最小值的值和坐标传递给次小值 更新前维护）
                    min2=min1;
                    j2=j1;
                    min1=dp[i-1][j];//记录左边最小花费
                    j1=j;//记录最小花费对应的临边染色
                }else{
                    //如果最小值没触发，但是有可能触发次小值。。。。
                    if(dp[i-1][j]<min2){
                        min2=dp[i-1][j];
                        j2=j;
                    }
                }
            }
            for(k=0;k<n;k++){
                //先把前面位置的最小值和次小值算出如果最小值对应的颜色不是当前的颜色j 那么就可以直接用啦，如果是不能用就用次小值
                if(k!=j1){
                    dp[i][k]=min1+costs[i-1][k];
                }
                else{
                    dp[i][k]=min2+costs[i-1][k];
                }
            }
        }
        //最后一个位置m的n种颜色结尾
        int res=Integer.MAX_VALUE;
        for(j=0;j<n;j++){
            res=Math.min(res,dp[m][j]);
        }
        return res;

    }
}
