package codeforce.advance;

import java.util.Scanner;

public class P24_PowerPlant {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt(); // 行数
    int m = scanner.nextInt(); // 列数
    int c = scanner.nextInt(); // 压缩区块的大小
    int k = scanner.nextInt(); // 满足条件的最小发电量

    int[][] matrix = new int[n][m]; // 存储地块的二维数组

    // 逐行读入地块信息
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        matrix[i][j] = scanner.nextInt();
      }
    }

    System.out.println(getResult(matrix, n, m, c, k));
  }

  public static int getResult(int[][] matrix, int n, int m, int c, int k) {
    // 行压缩
    int[][] compressedRows = new int[n][];
    // 遍历每一行进行水平方向区块压缩
    for (int i = 0; i < n; i++) {
      int[] row = matrix[i];
      // 每c个相邻区块压缩的话，则一行最多压缩为m - c + 1个压缩区块
      int[] compressedRow = new int[m - c + 1];

      // 计算每行第一个压缩区块的发电量作为基准值
      for (int j = 0; j < c; j++) {
        compressedRow[0] += row[j];
      }

      // 第二个开始的压缩区块的发电量，基于和前面一个压缩区块的差异比较计算出，而不是重新全量计算
      for (int j = 1; j < compressedRow.length; j++) {
        compressedRow[j] = compressedRow[j - 1] - row[j - 1] + row[j + c - 1];
      }

      compressedRows[i] = compressedRow;
    }

    // 列压缩
    int[][] compressedColumns = new int[m - c + 1][];
    // 遍历经过行压缩后的地块的每一列，在垂直方向上进行压缩
    for (int i = 0; i < m - c + 1; i++) {
      // 如果c个相邻区块压缩的话，则一列最多压缩为n - c + 1个
      int[] compressedColumn = new int[n - c + 1];

      // 计算每列第一个压缩区块的发电量作为基准值
      for (int j = 0; j < c; j++) {
        compressedColumn[0] += compressedRows[j][i];
      }

      // 第二个开始的压缩区块的发电量，基于和前面一个压缩区块的差异比较计算出，而不是重新全量计算
      for (int j = 1; j < compressedColumn.length; j++) {
        compressedColumn[j] = compressedColumn[j - 1] - compressedRows[j - 1][i] + compressedRows[j + c - 1][i];
      }

      compressedColumns[i] = compressedColumn;
    }

    // 统计压缩区域发电量大于等于k的数量
    int count = 0;
    for (int i = 0; i < compressedColumns.length; i++) {
      for (int j = 0; j < compressedColumns[i].length; j++) {
        if (compressedColumns[i][j] >= k) {
          count++;
        }
      }
    }

    return count;
  }
}

