package acwing_leetcode.day05;

/**
 * @Auther: mobing
 * @Date: 2020/9/5 19:16
 * @Description:
 * 91. 解码方法
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 *
 * 示例 1:
 *
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 *
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 */
public class Lc91_NumDecoding {
    public int numDecodings2(String s) {
        // write your code here
        //解密数字串就划分为若干段数字（数字对应字母）
        //最后一步也就是最后一个数字可以对应某个字母，也可以两个比如12变成L所以 dp[i]=dp[i-1]+dp[i-2]
        //但是 dp[i-1]要保证s[i-1]!=0才能解密 dp[i-2]要保证s[i-2]s[i-1]对应字母而不是大于26或者0*
        char[] ch=s.toCharArray();
        int n=ch.length;
        if(n==0)
            return 0;
        int[] dp=new int[n+1];
        //init
        dp[0]=1;//前0个个字符是1种解密
        for (int i=1;i<=n ;i++){
            dp[i]=0;
            int t=ch[i-1]-'0';
            if(t>=1 && t<=9){
                dp[i]+=dp[i-1];
            }
            if(i>=2){
                t=(ch[i-2]-'0')*10+(ch[i-1]-'0');
                if(t>=10 && t<=26){
                    dp[i]+=dp[i-2];
                }
            }
        }
        return dp[n];
    }
    public static void main(String[] args) {
    }
}
