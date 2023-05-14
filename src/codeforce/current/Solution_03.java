package codeforce.current;

import java.util.Scanner;

/**
 * @program: danceOffer
 *  @description:
 * @author: mobing_yin
 * @create: 2023-04-29 22:36
 **/

public class Solution_03 {
    public static int[][] help;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] arr = new int[m][n];
        help = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(0);
    }
}