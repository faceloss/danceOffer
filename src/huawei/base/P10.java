package huawei.base;

/**
 * @program: danceOffer
 * @description:
 *
题目描述

你现在是一场采用特殊赛制投篮大赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。
比赛开始时，记录是空白的。
你会得到一个记录操作的字符串列表 [ops]，其中ops[i]是你需要记录的第i项操作，ops遵循下述规则：

整数x-表示本回合新获得分数x
“+” – 表示本回合新获得的得分是前两次得分的总和。
“D” – 表示本回合新获得的得分是前一次得分的两倍。
“C” – 表示本回合没有分数，并且前一次得分无效，将其从记录中移除。

请你返回记录中所有得分的总和。

输入描述

输入为一个[字符串数组]

输出描述

输出为一个整形数字

提示
1 <= ops.length <= 1000
ops[i] 为 “C”、“D”、“+”，或者一个表示整数的字符串。整数范围是 [-3 * 10^4, 3 * 10^4]
需要考虑异常的存在，如有异常情况，请返回-1
对于“+”操作，题目数据不保证记录此操作时前面总是存在两个有效的分数
对于“C”和“D”操作，题目数据不保证记录此操作时前面存在一个有效的分数
题目输出范围不会超过整型的最大范围，不超过2^63 - 1
用例
输入	5 2 C D +
输出	30
说明	“5”-记录加5，记录现在是[5]
“2”-记录加2，记录现在是[5,2]
“C”-使前一次得分…"
 * @author: mobing_yin
 * @create: 2023-04-22 21:58
 **/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P10 {
    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        String[] ops = in.nextLine().split(" ");

        List<Integer> scores = new ArrayList<>();
        for (String op : ops) {
            if (op.equals("+")) {
                // 如果记录得分不足两次，则无法进行加和操作
                if (scores.size() < 2) {
                    System.out.println(-1);
                    return;
                }
                // 将前两次得分加和并加入记录
                int sum = scores.get(scores.size() - 1) + scores.get(scores.size() - 2);
                scores.add(sum);
            } else if (op.equals("D")) {
                // 如果记录得分为空，则无法进行双倍操作
                if (scores.isEmpty()) {
                    System.out.println(-1);
                    return;
                }
                // 将前一次得分乘以2并加入记录
                int doubleScore = scores.get(scores.size() - 1) * 2;
                scores.add(doubleScore);
            } else if (op.equals("C")) {
                // 如果记录得分为空，则无法进行作废操作
                if (scores.isEmpty()) {
                    System.out.println(-1);
                    return;
                }
                // 将前一次得分作废
                scores.remove(scores.size() - 1);
            } else {
                // 将本次得分加入记录
                int score = Integer.parseInt(op);
                scores.add(score);
            }
        }

        // 计算得分总和
        int sum = scores.stream().mapToInt(Integer::intValue).sum();
        System.out.println(sum);
    }
}

