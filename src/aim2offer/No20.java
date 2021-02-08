package aim2offer;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字
 */

public class No20 {


    public static void main(String[] args) {
        int[][] a = create(5, 5);
        print(a);
        clockWisePrint(a, 0, 4);
    }
    // 按照规律移动index 控制在空间上的查找
    private static void clockWisePrint(int[][] a, int i, int j) {
        if (j < i)
            return;
        if (j == i) {
            System.out.print(a[i][j] + " ");
            return;
        }
        //第i行 y是一个index 从i i到i j
        int y = i;
        while (y <= j) {
            System.out.print(a[i][y] + " ");
            y++;
        }
        //第j列去掉第一个 从i+1 j  到 jj
        y = i + 1;
        while (y <= j) {
            System.out.print(a[y][j] + " ");
            y++;
        }
        // 第j行 去掉第一个此时i=j j j-1 j i
        y = j - 1;
        while (y >= i) {
            System.out.print(a[j][y] + " ");
            y--;
        }
        //第 i列 j-1 i  i+1 i
        y = j - 1;
        while (y >= i + 1) {
            System.out.print(a[y][i] + " ");
            y--;
        }
        clockWisePrint(a, i + 1, j - 1);
    }

    private static void print(int[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] create(int row, int line) {
        int[][] a = new int[row][line];
        int num = 1;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < line; j++) {
                a[i][j] = num++;
            }
        }
        return a;
    }

}
