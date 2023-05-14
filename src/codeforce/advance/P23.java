package codeforce.advance;

import java.util.Scanner;
import java.util.*;
import java.util.stream.Collectors;
 
public class P23 {
    public static void main(String[] args) {
        //处理输入
        Scanner in=new Scanner(System.in); 
        List<Integer> params =Arrays.stream(in.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int n = params.get(0);
        int m = params.get(1);
        int c = params.get(2);
        int k = params.get(3);
 
        int[][] matrix = new int [n][m];
        for (int i=0;i<n;i++) {
            String[] num_strs =in.nextLine().split(" ");
            for (int j=0;j<m;j++) {
                matrix[i][j] = Integer.parseInt(num_strs[j]);
            }  
        }
 
        System.out.println(get_area_count(matrix, k, c));
    }
 
    public static int get_area_count(int[][] mat, int threshold, int c) {
        int n = mat.length;
        int m = mat[0].length;
        int[][] s = new int [n+1][m+1];
        //1、生成前缀和子矩阵
        for (int i = 1; i <= n; ++i){
            for (int j = 1; j <= m; ++j) {
                //s[i][j]表示以[i,j]作为矩阵最右下角的最大矩阵的前缀和
                //解释：以点[i,j]作为作为最右下角的最大矩阵的前缀和需要加上点[i-1,j]和点[i,j-1]的前缀和，然而会重复多加一个点[i-1][j-1]的前缀和，所以要减一个
                s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int ans = 0;
        //2、遍历前缀和矩阵，获得边长等于c的矩阵
        for (int i = c; i <= n; ++i) {
            for (int j = c; j <= m; ++j) {
                //重点理解：减去点[i-c][j]和点[i][j-c]的矩阵前缀和，剩下来的为一个边长为c正方形，注意点[i-c][j-c]减了两次，需要加一个回来
                if (s[i][j] - s[i - c][j] - s[i][j - c] + s[i - c][j - c] >= threshold)
                    ans += 1;
            }
        }
        return ans;
    }
 
}
