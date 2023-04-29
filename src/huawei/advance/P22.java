package huawei.advance;

import java.util.Scanner;

public class P22 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in); // 创建一个Scanner对象

    int n = scanner.nextInt(); // 读取输入的n

    int[] p = new int[n]; // 创建一个长度为n的整型数组p
    for (int i = 0; i < n; i++) {
      p[i] = scanner.nextInt(); // 读取输入的n个整数
    }

    int pMax = scanner.nextInt(); // 读取输入的pMax

    System.out.println(getResult(n, p, pMax)); // 输出getResult方法的返回值
  }

  public static int getResult(int n, int[] p, int pMax) {
    int[][] dp = new int[n + 1][pMax + 1]; // 创建一个(n+1)*(pMax+1)的二维数组dp

    for (int i = 0; i <= n; i++) { // 循环遍历dp数组的每一个元素
      for (int j = 0; j <= pMax; j++) {
        if (i == 0 || j == 0) continue; // 如果i或j为0，则跳过本次循环

        if (p[i - 1] > j) { // 如果p[i-1]大于j
          dp[i][j] = dp[i - 1][j]; // 则dp[i][j]等于dp[i-1][j]
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], p[i - 1] + dp[i - 1][j - p[i - 1]]); // 否则dp[i][j]等于dp[i-1][j]和p[i-1]+dp[i-1][j-p[i-1]]的最大值
        }
      }
    }

    return dp[n][pMax]; // 返回dp[n][pMax]
  }
}
