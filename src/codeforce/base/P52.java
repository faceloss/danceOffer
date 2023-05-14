package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:19
 **/

import java.util.Scanner;

public class P52{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        int min = 0;
        int countB = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'B') {
                // 如果当前位置是B，只统计B的个数
                countB ++;
            }
            if (str.charAt(i) == 'A') {
                // 方案1 : 把所有之前的B改为A
                int candidate1 = countB;

                // 方案2：假设在当前位置以前的子字符串经过修改已满足要求，把当前位置的A改为B,即动态编程思想
                int candidate2 = min + 1;

                min = Math.min(candidate1, candidate2);
            }
        }

        System.out.println(min);
    }
}


