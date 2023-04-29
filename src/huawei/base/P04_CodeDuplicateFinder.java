package huawei.base;

import java.util.Scanner;

/**
 * 最长公共子串
 * */
public class P04_CodeDuplicateFinder {
    public static void main(String[] args) {
        // 处理输入
        Scanner scanner = new Scanner(System.in); //创建Scanner对象，用于读取控制台输入
        String code1 = scanner.nextLine(); //读取第一行输入
        String code2 = scanner.nextLine(); //读取第二行输入

        char[] chars1 = code1.toCharArray(); //将第一行输入转换为字符数组
        char[] chars2 = code2.toCharArray(); //将第二行输入转换为字符数组
        int[][] dp = new int[chars1.length + 1][chars2.length + 1]; //创建二维数组dp，用于存储最长公共子串长度
        int maxLen = 0; //初始化最长公共子串长度为0
        int lastPos = 0; //初始化最长公共子串的最后一个字符在第一行输入中的位置为0
        for (int i = 0; i < chars1.length; i++) { //遍历第一行输入的每个字符
            for (int j = 0; j < chars2.length; j++) { //遍历第二行输入的每个字符
                if (chars1[i] == chars2[j]) { //如果当前字符相同
                    dp[i + 1][j + 1] = dp[i][j] + 1; //最长公共子串长度加1
                    //如果有更长的公共子串，更新长度和最后的位置
                    if (dp[i + 1][j + 1] > maxLen) { //如果当前公共子串长度大于之前的最长公共子串长度
                        maxLen = dp[i + 1][j + 1]; //更新最长公共子串长度
                        lastPos = i; //更新最长公共子串的最后一个字符在第一行输入中的位置
                    }
                }
            }
        }
        if (maxLen == 0) { //如果最长公共子串长度为0
            System.out.println(""); //输出空字符串
        } else {
            //输出最长公共子串
            System.out.println(code1.substring(lastPos - maxLen + 1, lastPos + 1)); //输出第一行输入中最长公共子串的字符串
        }
    }
}
