package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:01
 **/


import java.util.Arrays;
import java.util.Scanner;

public class P25_DepartmentBikeRiding {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int maxWeight = sc.nextInt(); // 最大载重
        int n = sc.nextInt(); // 部门总人数

        int[] weights = new int[n]; // 存储每个人的体重
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }

        System.out.println(getBikeCount(weights, maxWeight));
    }

    public static int getBikeCount(int[] weights, int maxWeight) {
        Arrays.sort(weights); // 将体重升序排列

        int count = 0; // 记录需要的双人自行车数量

        int i = 0;
        int j = weights.length - 1;

        while (i < j) {
            if (weights[i] + weights[j] <= maxWeight) { // 如果当前最轻和最重的两个人体重之和小于等于最大载重
                i++; // 则最轻的人和最重的人一起骑
            }
            j--; // 否则最重的人单独骑
            count++; // 记录使用的双人自行车数量
        }

        if (i == j) { // 如果最后只剩一个人
            count++; // 则需要一辆单人自行车
        }

        return count;
    }
}

