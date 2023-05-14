package codeforce.advance;

import java.util.Arrays;
import java.util.Scanner;

public class P26 {
    static int[] cache; // 缓存每个服务所需要的时间

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt(); // 服务的数量

        cache = new int[n];
        Arrays.fill(cache, -1); // 初始化缓存数组

        int[][] matrix = new int[n][n]; // 存储服务之间的依赖关系
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        int k = scanner.nextInt(); // 要查询的服务

        System.out.println(getResult(matrix, n, k));
    }

    public static int getResult(int[][] matrix, int n, int k) {
        return dfs(k - 1, matrix);
    }

    public static int dfs(int k, int[][] matrix) {
        if (cache[k] != -1) return cache[k]; // 如果缓存中已经有了该服务的时间，直接返回

        int[] preServices = matrix[k]; // 获取该服务依赖的服务

        int maxPreTime = 0; // 依赖服务中最长的启动时间
        for (int i = 0; i < preServices.length; i++) {
            if (i != k && preServices[i] != 0) {
                maxPreTime = Math.max(maxPreTime, dfs(i, matrix)); // 递归求解依赖服务的启动时间
            }
        }

        cache[k] = matrix[k][k] + maxPreTime; // 该服务的启动时间等于自身启动时间加上依赖服务中最长的启动时间

        return cache[k];
    }
}

