package codeforce.advance;

import java.util.Scanner;
public class P42 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt(); // 输入行数
        int col = scanner.nextInt(); // 输入列数
        int[][] heights = new int[row][col]; // 存储山峰高度的二维数组
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                heights[i][j] = scanner.nextInt(); // 输入每个山峰的高度
            }
        }

        int[][] res = new int[row][col]; // 存储每个山峰的流量
        int declineIndex = 0; // 记录山峰高度下降的位置
        for (int i = 0; i < row; i++) {
            for (int j = 1; j < col; j++) {
                getCount(declineIndex, i, j, res, heights); // 计算每个山峰的流量
                if (heights[i][j] >= heights[i][declineIndex]) {
                    declineIndex = j; // 更新山峰高度下降的位置
                }
            }
            declineIndex = 0;
        }

        declineIndex = 0;
        for (int i = 0; i < col; i++) {
            for (int j = 1; j < row; j++) {
                getCount2(declineIndex, i, j, res, heights); // 计算每个山峰的流量
                if (heights[j][i] >= heights[declineIndex][i]) {
                    declineIndex = j; // 更新山峰高度下降的位置
                }
            }
            declineIndex = 0;
        }

        System.out.println(res.length + " " + res[0].length); // 输出结果的行数和列数

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                sb.append(res[i][j]).append(" "); // 将每个山峰的流量添加到字符串中
            }
        }

        System.out.println(sb.toString().substring(0, sb.toString().length() - 1)); // 输出结果字符串
    }

    private static void getCount2(int declineIndex, int i, int j, int[][] res, int[][] heights) {
        if (heights[j][i] <= heights[j - 1][i]) {
            res[j][i]++;
            return;
        }

        res[j][i]++;
        int tempHeight = heights[j - 1][i];
        for (int k = j - 2; k >= declineIndex; k--) {
            if (heights[k][i] > tempHeight) {
                res[j][i]++;
                tempHeight = Math.max(tempHeight, heights[k][i]);
                if (tempHeight >= heights[j][i]) {
                    break;
                }
            }
        }
    }

    private static void getCount(int declineIndex, int i, int j, int[][] res, int[][] heights) {
        if (heights[i][j] <= heights[i][j - 1]) {
            res[i][j]++;
            return;
        }

        res[i][j]++;
        int tempHeight = heights[i][j - 1];
        for (int k = j - 2; k >= declineIndex; k--) {
            if (heights[i][k] > tempHeight) {
                res[i][j]++;
                tempHeight = Math.max(tempHeight, heights[i][k]);
                if (tempHeight >= heights[i][j]) {
                    break;
                }
            }
        }
    }
}
