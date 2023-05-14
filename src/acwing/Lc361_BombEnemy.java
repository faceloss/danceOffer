package acwing;

/**
 * @Auther: mobing
 * @Date: 2020/9/6 16:15
 * @Description:
 * 361. 轰炸敌人
 * 想象一下炸弹人游戏，在你面前有一个二维的网格来表示地图，网格中的格子分别被以下三种符号占据：
 *
 * 'W' 表示一堵墙
 * 'E' 表示一个敌人
 * '0'（数字 0）表示一个空位
 *
 *
 * 请你计算一个炸弹最多能炸多少敌人。
 *
 * 由于炸弹的威力不足以穿透墙体，炸弹只能炸到同一行和同一列没被墙体挡住的敌人。
 *
 * 注意：你只能把炸弹放在一个空的格子里
 *
 * 示例:
 *
 * 输入: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
 * 输出: 3
 * 解释: 对于如下网格
 *
 * 0 E 0 0
 * E 0 W E
 * 0 E 0 0
 *
 * 假如在位置 (1,1) 放置炸弹的话，可以炸到 3 个敌人
 */
public class Lc361_BombEnemy {
    public class Solution {
        /**
         * @param : Given a 2D grid, each cell is either 'W', 'E' or '0'
         * @return: an integer, the maximum enemies you can kill using one bomb
         */
        public int maxKilledEnemies(char[][] arr) {
            // write your code here
            if(arr==null || arr.length==0||arr[0]==null||arr[0].length==0){
                return 0;
            }
            int m=arr.length;
            int n=arr[0].length;
            int[][] dp=new int[m][n];
            int[][] res=new int[m][n];
            int i,j;
            for (i=0;i<m ;i++ ){
                for(j=0;j<n;j++){
                    //初始化 res表示可以kill enemies
                    res[i][j]=0;
                }
            }

            //up
            for (i=0;i<m ;i++ ){
                for(j=0;j<n;j++){
                    //初始化 res表示可以kill enemies
                    if(arr[i][j]=='W'){
                        dp[i][j]=0;
                    }else{
                        dp[i][j]=0;
                        if(arr[i][j]=='E'){
                            dp[i][j]=1;
                        }
                        if(i>0){
                            dp[i][j]+=dp[i-1][j];
                        }
                    }
                    res[i][j]+=dp[i][j];

                }
            }

            //down
            for (i=m-1;i>=0 ;i-- ){
                for(j=0;j<n;j++){
                    //初始化 res表示可以kill enemies
                    if(arr[i][j]=='W'){
                        dp[i][j]=0;
                    }else{
                        dp[i][j]=0;
                        if(arr[i][j]=='E'){
                            dp[i][j]=1;
                        }
                        if(i+1<m){
                            dp[i][j]+=dp[i+1][j];
                        }
                    }
                    res[i][j]+=dp[i][j];

                }
            }

            //left
            for (i=0;i<m ;i++ ){
                for(j=0;j<n;j++){
                    //初始化 res表示可以kill enemies
                    if(arr[i][j]=='W'){
                        dp[i][j]=0;
                    }else{
                        dp[i][j]=0;
                        if(arr[i][j]=='E'){
                            dp[i][j]=1;
                        }
                        if(j>0){
                            dp[i][j]+=dp[i][j-1];
                        }
                    }
                    res[i][j]+=dp[i][j];

                }
            }

            //right
            for (i=0;i<m ;i++ ){
                for(j=n-1;j>=0;j--){
                    //初始化 res表示可以kill enemies
                    if(arr[i][j]=='W'){
                        dp[i][j]=0;
                    }else{
                        dp[i][j]=0;
                        if(arr[i][j]=='E'){
                            dp[i][j]=1;
                        }
                        if(j+1<n){
                            dp[i][j]+=dp[i][j+1];
                        }
                    }
                    res[i][j]+=dp[i][j];

                }
            }
            int result=0;
            for(i=0;i<m ;i++ ){
                for(j=n-1;j>=0;j--){
                    if(arr[i][j]=='0'){
                        if(res[i][j]>result){
                            result=res[i][j];
                        }
                    }
                }
            }
            return result;

        }

    }
}
