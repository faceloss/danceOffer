package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:19
 **/

import java.util.Arrays;
import java.util.Scanner;

/**
 * 日志限流算法
 */
public class P51_LogLimiter {
    /**
     * @param n      系统某一天运行的单位时间数N
     * @param logs   这一天每单位时间产生的日志数量的数组
     * @param maxNum 系统一天可以保存的总日志条数total
     * @return 每单位时间内最大可保存的日志条数limit，如果不需要启动限流机制，返回-1
     */
    public static int getLimit(int n, int[] logs, int maxNum) {
        // 计算一天产生的日志条数总和
        int sum = Arrays.stream(logs).sum();

        // 如果一天产生的日志条数总和小于等于最大日志条数，则不需要启动限流机制，返回-1
        if (sum <= maxNum) {
            return -1;
        }

        // 计算最小限流量和最大限流量
        int maxLimit = Arrays.stream(logs).max().getAsInt();
        int minLimit = maxNum / n;

        // 二分查找限流量
        int ans = minLimit;
        while (maxLimit - minLimit > 1) {
            int limit = (maxLimit + minLimit) / 2;

            // 计算以当前限流量为限制条件时，一天内最多可以保存的日志条数
            int tmp = 0;
            for (int log : logs) {
                tmp += Math.min(log, limit);
            }

            // 根据计算结果更新限流量
            if (tmp > maxNum) {
                maxLimit = limit;
            } else if (tmp < maxNum) {
                minLimit = limit;
                ans = limit;
            } else {
                return limit;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 输入日志产生的时间单位数
        int n = sc.nextInt();

        // 输入每个时间单位内产生的日志数
        int[] logs = new int[n];
        for (int i = 0; i < n; i++) {
            logs[i] = sc.nextInt();
        }

        // 输入系统一天可以保存的日志条数
        int maxNum = sc.nextInt();

        // 输出每个时间单位内最大可保存的日志条数
        System.out.println(getLimit(n, logs, maxNum));
    }
}

