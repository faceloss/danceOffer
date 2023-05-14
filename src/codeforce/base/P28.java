package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:02
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P28 {
    // 递归函数，找到第n个字符串的第k个字符
    public static char find(long n, long k) {
        // 第1个字符串
        if (n == 1) {
            return 'R';
        }
        // 第2个字符串
        if (n == 2) {
            if (k == 0) return 'B'; // 如果k为0，则返回B
            else return 'R'; // 否则返回R
        }

        long len = 1L << (n-2); // 计算字符串长度
        // 如果 k 在后半段，则与前一个字符串相同
        if (k >= len) {
            long pos = k - len; // 计算在前一个字符串中的位置
            return find(n - 1, pos); // 递归求解前一个字符串中的字符
        } else {
            // 如果 k 在前半段，则与前一个字符串相反
            return find(n - 1, k) == 'R' ? 'B' : 'R'; // 递归求解前一个字符串中的字符，并根据结果返回相反的字符
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        List<List<Long>> input_case = new ArrayList<>();
        // 读入所有测试用例
        for (int i = 0; i < t; i++) {
            long n = sc.nextLong();
            long k = sc.nextLong();
            List<Long> single_case = new ArrayList<>();
            single_case.add(n);
            single_case.add(k);
            input_case.add(single_case);
        }

        // 对每个测试用例进行求解
        for (int i = 0; i < t; i++) {
            long n = input_case.get(i).get(0);
            long k = input_case.get(i).get(1);
            String res = find(n, k) == 'R' ? "red" : "blue"; // 根据求解结果判断是红色还是蓝色
            System.out.println(res); // 输出结果
        }
    }
}

