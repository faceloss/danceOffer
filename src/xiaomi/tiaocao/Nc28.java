package xiaomi.tiaocao;

import org.springframework.core.convert.converter.Converter;

import java.util.*;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2020-12-11 15:43
 * 题目描述
 * 给出两个字符串 SS 和 TT，要求在O(n)O(n)的时间复杂度内在 SS 中找出最短的包含 TT 中所有字符的子串。
 * 例如：
 *
 * S ="XDOYEZODEYXNZ"
 * T ="XYZ"
 * 找出的最短子串为"YXNZ""YXNZ".
 *
 * 注意：
 * 如果 SS 中没有包含 TT 中所有字符的子串，返回空字符串 “”；
 * 满足条件的子串可能有很多，但是题目保证满足条件的最短的子串唯一。
 *
 * 示例1
 * 输入
 * 复制
 * "XDOYEZODEYXNZ","XYZ"
 * 返回值
 * 复制
 * "YXNZ"
 **/

public class Nc28 {
    public static void main(String[] args) {
        //Comparable 和 Comparator
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        //lamda可以简化代码
        names.sort((a, b) -> b.compareTo(a));
        Converter<String, Integer> converter = Integer::valueOf;
        Integer res = converter.convert("123");
        System.out.println(res.getClass());
        //
        for (String str : names){
            System.out.println(str);
        }
    }


    public static String getMinFather(String ss, String tt){
        Set<Character> obj = new HashSet<>();
        for (int i = 0; i <tt.length() ; i++) {
            obj.add(tt.charAt(i));
        }
        return null;
    }
}
