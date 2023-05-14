package acwing.day02;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: danceOffer
 * @description:22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例：
 * 输入：n = 3
 * 输出：[
 *        "((()))",
 *        "(()())",
 *        "(())()",
 *        "()(())",
 *        "()()()"
 *      ]
 * @author: mobing_yin
 * @create: 2020-09-08 17:35
 **/

public class Lc22_GenerateParentheses {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
    }

    //生成n个 化简成生成1个 一个需要啥()最后是0 并且 （）（）、（（）），三个。。。
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generate(res,  n, 0, 0, new StringBuffer());
        return res;
    }
    public static void generate(List<String> res, int index, int open, int close, StringBuffer combination) {
        if (combination.length() == 2 * index) {
            res.add(combination.toString());
            return;
        }
        if(open < index){
            combination.append("(");
            generate(res, index, open+1, close, combination);
            combination.deleteCharAt(combination.length()-1);
        }
        if(close < open){
            combination.append(")");
            generate(res, index, open, close+1, combination);
            combination.deleteCharAt(combination.length()-1);
        }
        //可以理解成两个n需要使用掉
    }
}
