package huawei.current;

import java.util.Scanner;

/**
 * @program: danceOffer
 * @description:输入描述 荒地建设电站 /区域发电量统计
 * 第一行输入为调研的地区长，宽，以及准备建设的电站【长宽相等，为正方形】的边长最低要求的发电量
 * 之后每行为调研区域每平方公里的发电量
 * 输出描述
 * 输出为这样的区域有多少个
 * 示例1：
 * 输入
 * 2 5 2 6
 * 1 3 4 5 8
 * 2 3 6 7 1
 * 输出
 * 4
 * @author: mobing_yin
 * @create: 2023-04-29 21:53
 **/

public class Solution_02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int len = sc.nextInt();
        int target = sc.nextInt();
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        System.out.println(getSuitableNum(m,n,len,arr, target));
    }

    public static double getSuitableNum(int m, int n, int len, int[][] arr, int target){
        if(m < len || n < len){
            return 0;
        }
        int res = 0;
        int[][] help =  new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                help[i][j] = (i-1 >= 0 ? help[i-1][j] : 0 )
                        + (j-1 >= 0 ? help[i][j-1] : 0 )
                        - ((i-1>=0 && j-1>=0) ? help[i-1][j-1] : 0)
                        + arr[i][j];
            }
        }
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if(i + len - 1 < m && j + len - 1 < n){
                    int left = j-1>=0 ? help[i+len-1][j-1] : 0;
                    int top = i-1>=0 ? help[i-1][j+len-1]  : 0;
                    int repair = i-1>=0 && j-1>=0 ? help[i-1][j-1]:0;
                    if(help[i+len-1][j+len-1] -left  -top + repair >= target)
                        res++;
                }
            }
        }
        return res;
    }
}
