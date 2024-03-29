package acwing.day04;

/**
 * @Auther: mobing
 * @Date: 2020/9/5 20:22
 * @Description:
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例:
 *
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 7
 * 解释: 因为路径 1→3→1→1→1 的总和最小。
 *
 */
public class Lc64_MinimumPathSum {
    public int minPathSum2(int[][] grid) {
        // write your code here

        if(grid==null || grid.length==0 || grid[0]==null ||grid[0].length==0){
            return 0;
        }

        int m=grid.length;
        int n=grid[0].length;

        int[][] dp=new int[2][n];//以行做滚动 如果列更短可以用列(行扫描和列扫描)
        int old=1,now=0;
        int i,j,t1,t2;
        for( i=0;i<m;i++){
            old=now;// row first
            now=1-now; // row second

            //滚动数组
            for( j=0;j<n;j++){
                if(i==0 && j==0){
                    dp[now][j]=grid[i][j];//用哪一行都一样，因为当i变了时 会覆盖
                    continue;
                }

                dp[now][j]=grid[i][j];//i j was 0
                if(i>0){
                    //说明有前一行 可以由前一行得来
                    t1=dp[old][j];
                }else{
                    //没有说明他就是0第一行要初始化
                    t1=Integer.MAX_VALUE;
                }

                if(j>0){
                    //结果可能来自前面一列
                    t2=dp[now][j-1];
                }else{
                    //没有说明第一列需要初始化
                    t2=Integer.MAX_VALUE;
                }
                if(t1<t2){
                    dp[now][j]+=t1;
                }else{
                    dp[now][j]+=t2;
                }
            }
        }
        return dp[now][n-1];

        /*int m=grid.length;
        int n=grid[0].length;
        int[][] dp=new int[m][n];
        //init
        dp[0][0]=grid[0][0];
        for(int i=1;i<m;i++){
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }
        for(int i=1;i<n;i++){
            dp[0][i]=dp[0][i-1]+grid[0][i];
        }
        for (int i=1;i<m ;i++ ){
            for(int j=1;j<n;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];*/
    }
}
