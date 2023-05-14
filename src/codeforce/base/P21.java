package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:00
 **/

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 木板数量
        int m = scanner.nextInt(); // 切割次数
        scanner.nextLine();
        int[] woodLengths = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // 木板长度数组

        // 统计每种长度的木板数量
        HashMap<Integer, Integer> woodCountMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            woodCountMap.put(woodLengths[i], woodCountMap.getOrDefault(woodLengths[i], 0) + 1);
        }

        // 将每种长度的木板按长度升序排序
        PriorityQueue<int[]> woodPriorityQueue = new PriorityQueue<>((wood1, wood2) -> wood1[0] - wood2[0]);
        for (int length : woodCountMap.keySet()) {
            woodPriorityQueue.offer(new int[]{length, woodCountMap.get(length)});
        }

        while (m > 0) {
            // 特殊情况，只有一种长度
            if (woodPriorityQueue.size() == 1) {
                int[] woodKind = woodPriorityQueue.poll();
                System.out.println(woodKind[0] + m / woodKind[1]); // 输出最终的木板长度
                return;
            } else {
                int[] woodKind1 = woodPriorityQueue.poll(); // 取出长度最小的一种木板
                int[] woodKind2 = woodPriorityQueue.peek(); // 取出长度次小的一种木板

                int diff = woodKind2[0] - woodKind1[0]; // 两种木板长度的差值
                int total = diff * woodKind1[1]; // 可以切割的木板数量

                if (total > m) { // 如果可以切割的木板数量大于剩余切割次数
                    System.out.println(woodKind1[0] + m / woodKind1[1]); // 输出最终的木板长度
                    return;
                } else if (total == m) { // 如果可以切割的木板数量等于剩余切割次数
                    System.out.println(woodKind2[0]); // 输出最终的木板长度
                    return;
                } else {
                    m -= total; // 减去已经切割的木板数量
                    woodKind2[1] += woodKind1[1]; // 将两种木板合并
                }
            }
        }

        System.out.println(woodPriorityQueue.peek()[0]); // 输出最终的木板长度
    }
}

