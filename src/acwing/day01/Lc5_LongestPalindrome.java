package acwing.day01;

/**
 * @program: danceOffer
 * @description: 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：  输入: "babad" 输出: "bab" 注意: "aba" 也是一个有效答案。
 * 示例 2：  输入: "cbbd" 输出: "bb"
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: mobing_yin
 * @create: 2020-08-26 15:58
 **/

public class Lc5_LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("ac"));
    }
    // 找最长回文子串，回文串特点就是中心和半径，但是存在偶数寻找半径和中心问题，因此对字符串需要预处理
    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        char[] chars1 = new char[2*n +1];
        for (int i = 0; i < 2*n+1 ; i++) {
            if(i%2==0){
                chars1[i] = '#';
            }else{
                chars1[i] = chars[i/2];
            }
        }
        // 解决偶数问题后 暴力找最大对应坐标center 和 最大宽度len
        int center = 0;
        int len = 1;
        for (int i = 0; i <chars1.length ; i++) {
            int cur = i;
            int curLen = 1;
            int left = cur-1;
            int right = cur+1;
            while(left>=0 && right<chars1.length && chars1[left] == chars1[right]){
                curLen++;
                left--;
                right++;
            }
            if(curLen>len){
                len = curLen;
                center = i;
            }
        }
        String newS = new String(chars1);
        String res = newS.substring(center-len+1,center+len).replace("#","");
        return res;
        //  #b#a#b#a#d# len=4,center=5;
        // #c#b#b#d# len=3,center=4;
    }
}
