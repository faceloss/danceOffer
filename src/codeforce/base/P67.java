package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:23
 **/

import java.util.Scanner;

public class P67 {
    public static String reverse_string(String str) {
        // 反转字符串
        String res = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            res += str.charAt(i);
        }
        return res;
    }

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        String input_str = scanner.nextLine();
        String res = "";
        String temp = "";
        for (int i = 0; i < input_str.length(); i++) {
            char c = input_str.charAt(i);
            // 是否为字母
            if (Character.isLetter(c)) {
                temp += c;
                // 遇到空格，对上一个字符串进行翻转
            } else if (c == ' ') {
                res += reverse_string(temp) + " ";
                temp = "";
                // 遇到标点符号，对上一个字符串进行翻转，并加上当前字符
            } else {
                if (!temp.equals("")) {
                    res += reverse_string(temp);
                    temp = "";
                }
                res += c;
            }
            if (i == input_str.length() - 1 && !temp.equals("")) {
                res += reverse_string(temp);
            }
        }

        System.out.println(res);
    }
}

