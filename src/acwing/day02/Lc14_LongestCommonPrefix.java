package acwing.day02;

/**
 * @program: danceOffer
 * @description:14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 * @author: mobing_yin
 * @create: 2020-09-07 16:26
 **/

public class Lc14_LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = new String[] {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }
    /**
    *时间复杂度：O(mnlogm)，其中 m 是字符串数组中的字符串的最小长度，n 是字符串的数量。
     * 二分查找的迭代执行次数是O(logm)，每次迭代最多需要比较 mn 个字符，因此总时间复杂度是 O(mnlogm)。
     * * */
        // 找到最短的字符串 和 其他的字符串去比较 是否被包含？
        public static String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            int minLength = Integer.MAX_VALUE;
            for (String str : strs) {
                minLength = Math.min(minLength, str.length());
            }
            int low = 0, high = minLength;
            // logm
            while (low < high) {
                // 这里mid故意大1 在isCommonPrefix subString
                int mid = (high - low + 1) / 2 + low;
                if (isCommonPrefix(strs, mid)) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            return strs[0].substring(0, low);
        }
        //两个字符串之间比较
        public static boolean isCommonPrefix(String[] strs, int length) {
            String str0 = strs[0].substring(0, length);
            int count = strs.length;
            // n*m
            for (int i = 1; i < count; i++) {
                if(!str0.equals(strs[i].substring(0,length))){
                    return false;
                }
            }
            return true;
        }
    class Solution2 {
        // 将上一次比较获得的信息利用上
        public String longestCommonPrefix(String[] strs) {
            String pre = "";
            if(strs.length==0){
                return "";
            }
            if (strs.length==1){
                return strs[0];
            }
            pre = help(strs[0],strs[1]);
            if(pre==null){
                return "";
            }
            // n
            for (int i = 2; i < strs.length; i++) {
                pre = help(pre,strs[i]);
                if(pre==null){
                    return "";
                }
            }
            return pre;
        }
        //
        private String help(String s1,String s2){
            int len = Math.min(s1.length(),s2.length());
            // 从后往前找最长相同
            for (int i = len; i > 0; i--) {
                String t = s1.substring(0,i);
                if (t.equals(s2.substring(0,i))){
                    return t;
                }
            }
            return null;
        }
    }
}
