package aim2offer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 例如输入字符串abc， 则打印出由字符串a、b、c所能
 * 排列出来的所有字符串abc、acb、bac、bca、cab和cba
 */

public class No28 {

    class Solution {
        List<String> res = new LinkedList<>();
        char[] c;

        public String[] permutation(String s) {
            c = s.toCharArray();
            dfs(0);
            return res.toArray(new String[res.size()]);
        }

        void dfs(int x) {
            if(x == c.length - 1) {
                res.add(String.valueOf(c)); // 添加排列方案
                return;
            }
            HashSet<Character> set = new HashSet<>();
            //针对x这个位置上 可以出现哪些个字符 防止重复使用set去重
            for(int i = x; i < c.length; i++) {
                if(set.contains(c[i])) continue; // 重复，因此剪枝
                set.add(c[i]);
                swap(i, x); // 交换，将 c[i] 固定在第 x 位
                dfs(x + 1); // 开启固定第 x + 1 位字符
                swap(i, x); // 恢复交换
            }
        }

        void swap(int a, int b) {
            char tmp = c[a];
            c[a] = c[b];
            c[b] = tmp;
        }
    }

    public static void main(String[] args) {
        myPrint("abc");
    }

    private static void myPrint(String str) {
        if (str == null)
            return;
        char[] chs = str.toCharArray();
        myPrint(chs, 0);
    }

    private static void myPrint(char[] str, int i) {
        if (i >= str.length)
            return;
        if (i == str.length - 1) {
            System.out.println(String.valueOf(str));
        } else {
            // 比如 1 2 3 ， 1的位置可以有3种可能， 1定完后2的位置可以有2钟 12 定完后3的位置就确定了
            for (int j = i; j < str.length; j++) {
                // 通过交换使i位置有多种可能
                char temp = str[j];
                str[j] = str[i];
                str[i] = temp;
                //对于上一个位置的可能确定后 锁定后面位置的可能
                myPrint(str, i + 1);
                // 回溯
                temp = str[j];
                str[j] = str[i];
                str[i] = temp;
            }
        }
    }

}
