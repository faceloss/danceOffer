package acwing.day13;

import java.util.HashMap;

/**
 * @program: danceOffer 单词规律
 * @description: 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * 示例1:  输入: pattern = "abba", str = "dog cat cat dog" 输出: true
 * 示例 2:  输入:pattern = "abba", str = "dog cat cat fish" 输出: false
 * 示例 3:  输入: pattern = "aaaa", str = "dog cat cat dog" 输出: false
 * 示例 4:  输入: pattern = "abba", str = "dog dog dog dog" 输出: false
 * 说明: 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。 
 * @author: mobing_yin
 * @create: 2020-08-27 09:55
 **/

//12.5+1+5+5
public class Lc290_WordPattern {
    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog dog dog dog";
        System.out.println(wordPattern(pattern, str));
    }

    public static boolean wordPattern(String pattern, String str) {
        char[] charsP = pattern.toCharArray();
        String[] strS = str.split(" ");
        if(charsP.length != strS.length){
            return false;
        }
        HashMap<Character,String> map = new HashMap<>();
        HashMap<String,Character> contains = new HashMap<>();
        for (int i = 0; i <charsP.length ; i++) {
            String value = map.get(charsP[i]);
            if(value == null && !contains.containsKey(strS[i])){
                map.put(charsP[i], strS[i]);
                contains.put(strS[i], charsP[i]);
            }else{
                if(value == null){
                    return false;
                }
                if(!value.equals(strS[i])){
                    return false;
                }
            }
        }
        return true;
    }
}
