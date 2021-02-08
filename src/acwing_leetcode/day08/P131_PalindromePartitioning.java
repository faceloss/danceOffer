//给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。 
//
// 返回 s 所有可能的分割方案。 
//
// 示例: 
//
// 输入: "aab"
//输出:
//[
//  ["aa","b"],
//  ["a","a","b"]
//] 
// Related Topics 深度优先搜索 动态规划 回溯算法 
// 👍 471 👎 0

package acwing_leetcode.day08;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//java:分割回文串
public class P131_PalindromePartitioning{
    public static void main(String[] args){
        Solution solution = new P131_PalindromePartitioning().new Solution();
        solution.partition("aab");
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> partition(String s) {
        if(s==null || s.length()==0){
            return null;
        }
        List<List<String>> res = new ArrayList<>();
        LinkedList<String> re = new LinkedList<String>();
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        for (int size = 2; size <= n; size++) {
            for (int i = 0; i + size - 1< n ; i++) {
                if(s.charAt(i) == s.charAt(i + size -1)){
                    if(size==2){
                        dp[i][i+size-1] = true;
                    }else{
                        dp[i][i+size-1] = dp[i+1][i+size-1];
                    }
                }
            }
        }

        boolean dp1[][] = new boolean[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i <=j; i++) {
                if(s.charAt(i) == s.charAt(j) && ((j-i<3) || dp[i+1][j-1])){
                    dp[i][j] = true;
                }
            }
        }
        trip(res, 0, n, s, re, dp);
        return res;
    }
    public void trip(List<List<String>> res, int l, int r, String s, LinkedList<String> queue,  boolean[][] dp){
        if(l == r){
            res.add(new ArrayList<>(queue));
            return;
        }
        for (int i = l; i < r; i++) {
            if(!dp[l][i]){
                continue;
            }
            queue.offer(s.substring(l, i+1));
            trip(res, i+1, r,s,queue,dp);
            queue.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}