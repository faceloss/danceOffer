package acwing_leetcode;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Auther: mobing
 * @Date: 2020/9/9 23:46
 * @Description:
 * 小强的梦中出现了一个N*N的矩形，矩形中的每个位置都有一个数字。
 * 现在小强可以让其中某个数字加上d,他想知道最少需要几步才能让整个矩形中的元素相等，或者这根本不可能。
 *
 * 输入描述：
 * 第一行两个整数N,d接下来N行，每行N个整数表示矩形
 * 输出描述：
 * 输出一个整数表示答案，若不存在合法的方案，则输出-1，否则输出最少的操作数
 *
 * 输入
 * 2 2
 * 2 4
 * 8 16
 * 输出
 * 17
 */
public class Demo01_RactangleToOneValue {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int d = sc.nextInt();
        int[][] nums = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nums[i][j] = sc.nextInt();
            }
        }
        System.out.println(minTime(d, nums));
    }
    public static int minTime(int d, int[][] nums){
        if(nums == null || nums.length == 0 || nums[0].length == 0){
            return -1;
        }
        int n = nums.length;
        int[] arr = new int[n*n];
        int len = arr.length;
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[index++] = nums[i][j];
            }
        }
        Arrays.sort(arr);
        int[] gap = new int[len-1];
        int sum = 0;
        index = 0;
        for (int i = 0; i < len-1; i++) {
            if(arr[i+1] == arr[i]){
                continue;
            }
            // 1 1 1 2 2 2 3 3 3  3种数字 2个gap
            int time = i + 1;
            gap[index] = arr[i+1] - arr[i];
            sum += time * gap[index];
            if(gap[index++] % d != 0){
                return -1;
            }
        }
        return sum % d == 0 ? sum/d : -1;
    }
}
