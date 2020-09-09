package acwing_leetcode.day01;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: danceOffer
 * @description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:  输入: "abcabcbb" 输出: 3  解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:  输入: "bbbbb" 输出: 1 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:  输入: "pwwkew" 输出: 3 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。  
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @author: mobing_yin
 * @create: 2020-08-26 14:38
 **/

public class Lc3_Longest_Unique_SubStr {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
    // 双指针l，r一起走，维护一个set记录指针中间唯一字符串，走的过程中，收集最大长度
    public static int lengthOfLongestSubstring(String s) {
        // 刚开始看到最长，想到动态规划，但是好像不行？记录每个字符是否出现过的状态无法表示
        Set<Character> unique = new HashSet<Character>();
        int n = s.length();
        char[] chars = s.toCharArray();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int right = -1, res = 0;
        for (int left = 0; left < n; ++left) {
            //计算从left开始可以得到的最大unique SubStr
            if (left != 0) {
                // 左指针向右移动一格，移除一个字符
                unique.remove(chars[left-1]);
            }
            // 移动右指针,只要不重复，否则停止 判断下一位是否重复 重复则不移动指针
            while (right + 1 < n && !unique.contains(chars[right+1])) {
                unique.add(chars[right+1]);
                ++right;
            }
            // left到right是一个非重复字串
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
