package acwing.day02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: danceOffer
 * @description:17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序
 * @author: mobing_yin
 * @create: 2020-09-07 16:26
 **/

public class Lc17_LetterCombinationsOfPhoneNumber {
    public static void main(String[] args) {
        System.out.println(staticChars.length);
        System.out.println(letterCombinations("9"));
    }
    public static char[] staticChars = {'a','b','c',  'd','e','f',  'g','h','i',  'j','k','l',  'm','n','o',  'p','q','r','s',  't','u','v',  'w','x','y','z'};
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        List<String> result = new ArrayList<>();
        char[] chars = digits.toCharArray();
        for (char cur:chars){
            if (!Character.isDigit(cur) || cur < '2'){
                return res;
            }
        }
        int len = digits.length();
        int index = 0;
        getS(index, len, digits, res, result);
        return result;
    }
    public static void getS(int begin, int end, String digits, List<String> res, List<String> result){
        System.out.println("ee");
        if(begin == end){
            for (String s:res){
                result.add(s);
            }
            return;
        }
        int index = digits.charAt(begin) - '2';
        List<String> newRes = new ArrayList<>();
        if(res.isEmpty()){
            res.add("");
        }
        for (int i = 0; i < res.size(); i++) {
            if(index<5){
                newRes.add(res.get(i)+staticChars[3*index]);
                newRes.add(res.get(i)+staticChars[3*index+1]);
                newRes.add(res.get(i)+staticChars[3*index+2]);
            }else if(index == 5 ){
                newRes.add(res.get(i)+staticChars[3*index]);
                newRes.add(res.get(i)+staticChars[3*index+1]);
                newRes.add(res.get(i)+staticChars[3*index+2]);
                newRes.add(res.get(i)+staticChars[3*index+3]);
            }else if (index ==7){
                newRes.add(res.get(i)+staticChars[3*index+1]);
                newRes.add(res.get(i)+staticChars[3*index+2]);
                newRes.add(res.get(i)+staticChars[3*index+3]);
                newRes.add(res.get(i)+staticChars[3*index+4]);
            }else{
                newRes.add(res.get(i)+staticChars[3*index+1]);
                newRes.add(res.get(i)+staticChars[3*index+2]);
                newRes.add(res.get(i)+staticChars[3*index+3]);
            }
        }
        getS(begin + 1, end, digits, newRes, result);
    }

    static class Solution {
        public static void main(String[] args) {
            letterCombinations("23");
        }
        public static List<String> letterCombinations(String digits) {
            List<String> combinations = new ArrayList<String>();
            if (digits.length() == 0) {
                return combinations;
            }
            Map<Character, String> phoneMap = new HashMap<Character, String>() {{
                put('2', "abc");
                put('3', "def");
                put('4', "ghi");
                put('5', "jkl");
                put('6', "mno");
                put('7', "pqrs");
                put('8', "tuv");
                put('9', "wxyz");
            }};
            backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
            return combinations;
        }

        public static void backtrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination) {
            if (index == digits.length()) {
                combinations.add(combination.toString());
            } else {
                char digit = digits.charAt(index);
                String letters = phoneMap.get(digit);
                int lettersCount = letters.length();
                for (int i = 0; i < lettersCount; i++) {
                    combination.append(letters.charAt(i));
                    backtrack(combinations, phoneMap, digits, index + 1, combination);
                    //最底层往上回溯 假设index == digits.length()-1 上面的执行完成后需要删除掉供下一次combination使用
                    combination.deleteCharAt(index);
                }
            }
        }
    }
}
