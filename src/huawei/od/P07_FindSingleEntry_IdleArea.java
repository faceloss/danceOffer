package huawei.od;

import java.util.*;
/**
 * 题目描述
 * 给定一个 m x n 的矩阵，由若干字符 ‘X’ 和 ‘O’构成，’X’表示该处已被占据，’O’表示该处空闲，请找到最大的单入口空闲区域。
 * 解释：
 * 空闲区域是由连通的’O’组成的区域，位于边界的’O’可以构成入口，
 * 单入口空闲区域即有且只有一个位于边界的’O’作为入口的由连通的’O’组成的区域。
 * 如果两个元素在水平或垂直方向相邻，则称它们是“连通”的。
 * 输入描述
 * 第一行输入为两个数字，第一个数字为行数m，第二个数字为列数n，两个数字以空格分隔，1<=m,n<=200。
 * 剩余各行为矩阵各行元素，元素为‘X’或‘O’，各元素间以空格分隔。
 * 输出描述
 * 若有唯一符合要求的最大单入口空闲区域，输出三个数字
 * 第一个数字为入口行坐标（0~m-1）
 * 第二个数字为入口列坐标（0~n-1）
 * 第三个数字为区域大小
 * 三个数字以空格分隔；
 * 若有多个符合要求，则输出区域大小最大的，若多个符合要求的单入口区域的区域大小相同，则此时只需要输出区域大小，不需要输出入口坐标。
 * 若没有，输出NULL。
 * 用例
 * 输入	4 4
 * X X X X
 * X O O X
 * X O O X
 * X O X X
 * 输出	3 1 5

* */
public class P07_FindSingleEntry_IdleArea {
    static int rows, cols;
    static List<List<String>> board; // 存储区域
    static List<List<Integer>> directions = Arrays.asList(
            Arrays.asList(0, -1),
            Arrays.asList(0, 1),
            Arrays.asList(-1, 0),
            Arrays.asList(1, 0)); // 上下左右四个方向
    static Set<String> visited = new HashSet<>(); // 存储已经访问过的格子的坐标

    // dfs函数返回连通块中的格子数量，同时将连通块的边界坐标存入enter中
    static int dfs(int row, int col, int count, List<List<Integer>> enter) {
        String pos = row + "-" + col; // 将坐标转化为字符串，方便存储到visited中

        // 如果当前格子不在区域内，或者是障碍物，或者已经访问过，直接返回当前连通块的格子数量
        if (row < 0 || row >= rows || col < 0 || col >= cols || board.get(row).get(col).equals("X") || visited.contains(pos)) {
            return count;
        }

        visited.add(pos); // 将当前格子标记为已访问

        // 如果当前格子在区域的边界上，将其坐标存入enter中
        if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
            enter.add(Arrays.asList(row, col));
        }

        count++; // 当前连通块的格子数量加1

        // 对当前格子的四个方向进行dfs搜索
        for (List<Integer> dir : directions) {
            int newRow = row + dir.get(0);
            int newCol = col + dir.get(1);
            count = dfs(newRow, newCol, count, enter);
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        rows = sc.nextInt();
        cols = sc.nextInt();

        board = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add(sc.next());
            }
            board.add(row);
        }

        List<List<Integer>> ans = new ArrayList<>(); // 存储所有符合条件的连通块

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board.get(i).get(j).equals("O") && !visited.contains(i + "-" + j)) { // 如果当前格子是未访问过的O格子，进行dfs搜索
                    List<List<Integer>> enter = new ArrayList<>();
                    int count = dfs(i, j, 0, enter); // 计算当前连通块的格子数量和边界坐标
                    if (enter.size() == 1) { // 如果当前连通块只有一个边界，将其存入ans中
                        List<Integer> pos = enter.get(0);
                        List<Integer> an = Arrays.asList(pos.get(0), pos.get(1), count);
                        ans.add(an);
                    }
                }
            }
        }

        if (ans.size() == 0) { // 如果没有符合条件的连通块，输出NULL
            System.out.println("NULL");
        } else {
            ans.sort((a, b) -> b.get(2) - a.get(2)); // 对符合条件的连通块按照格子数量从大到小排序

            if (ans.size() == 1 || ans.get(0).get(2) > ans.get(1).get(2)) { // 如果最大的连通块是唯一的，输出其边界坐标和格子数量；否则，只输出最大的连通块的格子数量
                String res = "";
                for (int ele : ans.get(0)) {
                    res += ele + " ";
                }
                System.out.println(res);
            } else {
                System.out.println(ans.get(0).get(2));
            }
        }
    }
}
