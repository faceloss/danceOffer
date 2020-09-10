package acwing_leetcode.day14;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Auther: mobing
 * @Date: 2020/9/10 00:24
 * @Description:318. 最大单词长度乘积
 * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。
 * 你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 *
 * 示例 1:
 *
 * 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
 * 输出: 16
 * 解释: 这两个单词为 "abcw", "xtfn"。
 * 示例 2:
 *
 * 输入: ["a","ab","abc","d","cd","bcd","abcd"]
 * 输出: 4
 * 解释: 这两个单词为 "ab", "cd"。
 * 示例 3:
 *
 * 输入: ["a","aa","aaa","aaaa"]
 * 输出: 0
 * 解释: 不存在这样的两个单词
 */
public class Lc318_MaximumProductofWordLengths {
    public static void main(String[] args) {
        String[] strings = {"a","ab","abc","d","cd","bcd","abcd"};
        maxProduct(strings);
    }
    // 暴力解法。。。过了case
    public static int maxProduct(String[] words) {
        if(words == null || words.length<2){
            return 0;
        }
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        int res = 0;
        boolean valid = true;
        for (int i = words.length-1; i >= 0; i--) {
            int big = words[i].length();
            for (int j = i-1; j >=0 ; j--) {
                char[] temp = words[j].toCharArray();
                for (char cur : temp){
                    if(words[i].indexOf(cur) != -1){
                        valid = false;
                    }
                }
                if(valid){
//                    return big * words[j].length();
                    res = Math.max(big * words[j].length(), res);
                    break;
                }
                valid = true;
            }
        }
        return res;
    }
}
