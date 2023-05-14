package codeforce.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:18
 **/

import java.util.Scanner;

class P39 {
    // 定义四个方向，上下左右
    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        // 输入处理
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(); // 行数
        int n = in.nextInt(); // 列数
        int[][] matrix = new int[m][n]; // 定义矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt(); // 读入矩阵中的值
            }
        }

        // 遍历每个点作为起点，求最大活动范围
        int maxRange = 0; // 定义最大活动范围
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean[][] visited = new boolean[m][n]; // 定义是否访问过
                int range = dfs(matrix, visited, i, j); // 深度优先搜索
                maxRange = Math.max(maxRange, range); // 更新最大活动范围
            }
        }

        System.out.println(maxRange); // 输出最大活动范围
    }

    public static int dfs(int[][] matrix, boolean[][] visited, int x, int y) {
        visited[x][y] = true; // 标记当前点已经访问过
        int range = 1; // 定义活动范围
        for (int[] direction : directions) { // 遍历四个方向
            int newX = x + direction[0]; // 新的横坐标
            int newY = y + direction[1]; // 新的纵坐标
            if (newX >= 0 && newX < matrix.length && newY >= 0 && newY < matrix[0].length
                    && !visited[newX][newY] && Math.abs(matrix[newX][newY] - matrix[x][y]) <= 1) { // 判断是否越界、是否访问过、是否符合条件
                range += dfs(matrix, visited, newX, newY); // 更新活动范围
            }
        }
        return range; // 返回活动范围
    }
}

