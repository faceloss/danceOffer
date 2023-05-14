package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:00
 **/

import java.util.Arrays;
import java.util.Scanner;

class P20 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象，用于读取输入
        int workTime = scanner.nextInt(); // 工作时长
        int workNum = scanner.nextInt(); // 工作数量
        int[][] works = new int[workNum][2]; // 工作清单，创建一个二维数组，存储每个工作的耗时和报酬
        for (int i = 0; i < workNum; i++) {
            works[i][0] = scanner.nextInt(); // 读取每个工作的耗时
            works[i][1] = scanner.nextInt(); // 读取每个工作的报酬
        }

        Arrays.sort(works, (a, b) -> a[0] - b[0]); // 使用Lambda表达式按照工作耗时从小到大排序

        int[][] dp = new int[workNum + 1][workTime + 1]; // 创建一个二维数组，用于存储前i个工作在j小时内的最大收益
        for (int i = 1; i <= workNum; i++) {
            for (int j = 1; j <= workTime; j++) {
                if (works[i - 1][0] <= j) { // 如果当前工作可以完成
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - works[i - 1][0]] + works[i - 1][1]); // 取当前工作完成和不完成两种情况的最大收益
                } else { // 如果当前工作无法完成
                    dp[i][j] = dp[i - 1][j]; // 直接继承前一个工作的最大收益
                }
            }
        }
        System.out.println(dp[workNum][workTime]); // 输出前workNum个工作在workTime小时内的最大收益
    }

}

