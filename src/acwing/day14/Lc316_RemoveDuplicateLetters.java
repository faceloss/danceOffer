package acwing.day14;


import java.util.*;

/**
 * @Auther: mobing
 * @Date: 2020/9/10 00:23
 * week14需要做的题：第 316、318 ~ 319、321 ~ 322、324、326~329 | 330~ 332、334 ~ 338、341 ~ 342
 * @Description:316. 去除重复字母
 * 给你一个仅包含小写字母的字符串，请你去除字符串中重复的字母，使得每个字母只出现一次。
 * 需保证返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *
 * 示例 1:
 *
 * 输入: "bcabc"
 * 输出: "abc"
 * 示例 2:
 *
 * accad
 * 输入: "cbacdcbc"
 * 输出: "acdb"
 */

/**
 * 首先要知道什么叫 “字典序”。字符串之间比较跟数字之间比较是不太一样的。字符串比较是从头往后一个字符一个字符比较的。
 * 哪个字符串大取决于两个字符串中 第一个对应不相等的字符 。根据这个规则，任意一个以 a 开头的字符串都大于任意一个以 b 开头的字符串。
 *
 * 综上所述，解题过程中我们将 最小的字符尽可能的放在前面 。下面将给出两种 O(N)O(N) 复杂度的解法：
 *
 * 方法一：题目要求最终返回的字符串必须包含所有出现过的字母，同时得让字符串的字典序最小。
 * 因此，对于最终返回的字符串，最左侧的字符是在能保证其他字符至少能出现一次情况下的最小字符。
 *
 * 方法二：在遍历字符串的过程中，如果字符 i 大于字符i+1，在字符 i 不是最后一次出现的情况下，删除字符 i。
 *
 * */
public class Lc316_RemoveDuplicateLetters {
    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters2("bcabc"));
    }

    //每次递归中，在保证其他字符至少出现一次的情况下，确定最小左侧字符。之后再将未处理的后缀字符串继续递归。
    public static String removeDuplicateLetters(String s) {
        int[] cnt = new int[26];
        int pos = 0;
        //统计次数，预处理 为找最小字符的最后位置准备
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        //找最小标的最后位置
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < s.charAt(pos)) pos = i;//找最小标  bbddaabcac
            if (--cnt[s.charAt(i) - 'a'] == 0) break;//最后位置 pos 保证每个字符出现一次
        }
        //一次字符结束找到一个pos（这个pos是一种字符剔除找到最小字符）
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }

    // 用栈来存储最终返回的字符串，并维持字符串的最小字典序。每遇到一个字符，如果这个字符不存在于栈中，就需要将该字符压入栈中
    // 但在压入之前，需要先将之后还会出现，并且字典序比当前字符小的栈顶字符移除，然后再将当前字符压入。
    public static String removeDuplicateLetters2(String s) {

        Stack<Character> stack = new Stack<>();

        // this lets us keep track of what's in our solution in O(1) time
        HashSet<Character> seen = new HashSet<>();

        // this will let us know if there are any more instances of s[i] left in s
        HashMap<Character, Integer> last_occurrence = new HashMap<>();
        for(int i = 0; i < s.length(); i++) last_occurrence.put(s.charAt(i), i);
//bbddaabcac
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            // we can only try to add c if it's not already in our solution
            // this is to maintain only one of each character
            if (!stack.contains(c)){
                // if the last letter in our solution:
                //     1. exists
                //     2. is greater than c so removing it will make the string smaller
                //     3. it's not the last occurrence
                // we remove it from the solution to keep the solution optimal
                // 要保证非最后一次出现 stack.peek()才可以删除
                while(!stack.isEmpty() && c < stack.peek() && last_occurrence.get(stack.peek()) > i){
//                    seen.remove(stack.pop());
                    stack.pop();
                }
//                seen.add(c);
                stack.push(c);
            }
        }
        StringBuilder sb = new StringBuilder(stack.size());
        for (Character c : stack) sb.append(c.charValue());
        return sb.toString();
    }
}
