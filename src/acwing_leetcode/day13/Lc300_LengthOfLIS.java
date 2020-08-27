package acwing_leetcode.day13;

/**
 * @program: danceOffer
 * @description: 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:  输入: [10,9,2,5,3,7,101,18] 输出: 4  解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:  可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 * @author: mobing_yin
 * @create: 2020-08-27 09:59
 **/

public class Lc300_LengthOfLIS {
    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};
        System.out.println(getNum(arr));
    }
    public static int getNum(int[] arr){
        if(arr.length == 1){
            return 1;
        }
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int len = 2; len <= n; len++) {
            // 假如len = n-1 则j只能取0 若是2 最大为n-2~n-1
            for (int j = 0; j < n-len ; j++) {
                if(arr[j+len] > arr[j+len-1]){
                    dp[j][j+len] = dp[j][j+len-1] + 1;
                }
            }
        }
        return dp[0][n-1];
    }
}
