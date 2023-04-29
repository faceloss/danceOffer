package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:23
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class P66 {
    // 存储所有符合条件的三角形周长
    public static ArrayList<Integer> trianglePerimeterList = new ArrayList<>();
    // 存储所有符合条件的三角形
    public static ArrayList<Integer[]> triangleList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        for (int i = 0; i < testCases; i++) {
            int n = scanner.nextInt();
            int[] nums = new int[n];
            for (int j = 0; j < n; j++) {
                nums[j] = scanner.nextInt();
            }

            // 对数组进行排序
            Arrays.sort(nums);
            // 求出所有符合条件的三角形
            dfs(nums, 0, new LinkedList<>());
            // 统计每个数字出现的次数
            int[] count = new int[100];
            for (int num : nums) {
                count[num]++;
            }
            // 求出所有符合条件的三角形的周长
            check(0, count, 0);

            // 找出最大的符合条件的三角形的周长
            int maxTrianglePerimeter = 0;
            for (Integer trianglePerimeter : trianglePerimeterList) {
                if (trianglePerimeter > maxTrianglePerimeter) {
                    maxTrianglePerimeter = trianglePerimeter;
                }
            }
            System.out.println(maxTrianglePerimeter);

            // 重置存储所有符合条件的三角形周长的列表
            trianglePerimeterList = new ArrayList<>();
        }
    }

    /**
     * 求出所有符合条件的三角形
     *
     * @param nums  未使用的数字列表
     * @param index 未使用的数字列表的起始下标
     * @param sides 当前已选的三角形的三条边
     */
    public static void dfs(int[] nums, int index, LinkedList<Integer> sides) {
        if (sides.size() == 3) {
            if (sides.get(0) * sides.get(0) + sides.get(1) * sides.get(1) == sides.get(2) * sides.get(2)) {
                triangleList.add(sides.toArray(new Integer[3]));
                return;
            }
        }

        for (int i = index; i < nums.length; i++) {
            if (!(i > 0 && nums[i] == nums[i - 1])) {
                sides.add(nums[i]);
                dfs(nums, i + 1, sides);
                sides.removeLast();
            }
        }
    }

    /**
     * 求出所有符合条件的三角形的周长
     *
     * @param index 当前未使用的三角形的下标
     * @param count 数字出现的次数
     * @param num   当前已选的三角形的数量
     */
    public static void check(int index, int[] count, int num) {
        if (index >= triangleList.size()) {
            trianglePerimeterList.add(num);
            return;
        }

        for (int i = index; i < triangleList.size(); i++) {
            Integer[] singleTriangle = triangleList.get(i);
            int a = singleTriangle[0];
            int b = singleTriangle[1];
            int c = singleTriangle[2];

            if (count[a] > 0 && count[b] > 0 && count[c] > 0) {
                count[a]--;
                count[b]--;
                count[c]--;
                num++;
                check(i + 1, count, num);
                num--;
                count[a]++;
                count[b]++;
                count[c]++;
            }
        }

        trianglePerimeterList.add(num);
    }
}

