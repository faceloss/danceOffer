/**给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 示例 1：
输入：s = "(()"
输出：2
解释：最长有效括号子串是 "()"
 示例 2：
输入：s = ")()())"
输出：4
解释：最长有效括号子串是 "()()"
 示例 3：
输入：s = ""
输出：0
 提示：
 0 <= s.length <= 3 * 104
 s[i] 为 '(' 或 ')'
 Related Topics 字符串 动态规划
 👍 1143 👎 0*/

package acwing.day03;
//java:最长有效括号
public class P32_LongestValidParentheses{
    public static void main(String[] args){
        Solution solution = new P32_LongestValidParentheses().new Solution();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 有效括号的最长长度
    // 子串问题：严格以每个结尾计算个答案，最终答案必在其中
    public int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;
        int[] dp = new int[s.length()]; // dp[i]：严格以i位置结尾，形成的有效括号子串最长长度是多少
        int max = 0; // 最终的答案

        // dp[0] = 0; // 默认

        for (int i = 1; i < s.length(); i++) {
            // if (s.charAt(i) == '(') dp[i] = 0; 以左括号结尾，无效

            if (s.charAt(i) == ')') {
                //假如前面字符有效是4 则需要i-1往前移动4位才是需要去验证
                int preLen = dp[i - 1]; // 前面已经形成的有效括号长度
                int pre = i - 1 - preLen; // 寻找与当前的右括号相匹配的左括号位置：前面有效括号长度再往前一个位置

                if (pre >= 0 && s.charAt(pre) == '(') { // 如果寻找到左括号：前面有效括号长度再往前一个位置是左括号
                    dp[i] = dp[i-1] + 2; // 可以与当前的右括号闭合，有效长度增加2

                    // 【注意】此时，需要再往前看下，是否还有有效长度，如果有，合并过来
                    // 例如："()(()())" 当前在计算最后一个位置时，dp[7]已经等于 dp[6]+2 = 4+2
                    // 但需要再往前看一眼，dp[1]还有有效长度，合并过来 dp[7] = 4+2+2
                    // 那是否还需要再往前看？
                    // 不需要了，因为，如果前面还有有效长度，其长度肯定已经合并到dp[2]上了
                    // 因此，每次只需要再往前多看一眼就可以
                    if (pre-1 >= 0) {
                        dp[i] += dp[pre-1];
                    }
                }

                max = Math.max(max, dp[i]); // 严格以每个结尾抓一个答案，最终答案必在其中
            }
        }

        return max;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}