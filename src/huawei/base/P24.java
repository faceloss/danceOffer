package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:01
 **/

import java.util.Scanner;

public class P24 {
    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        int left = in.nextInt();
        int right = in.nextInt();

        System.out.println(dp(right) - dp(left - 1));
    }

    public static int dp(int num) {
        // 将数字转化为二进制数组
        int[] binaryNums = Integer.toBinaryString(num).chars().map(c -> c - '0').toArray();
        int[][][] binaryDp = new int[binaryNums.length][2][2];

        return search(0, true, binaryDp, binaryNums, 0, 0);
    }

    public static int search(int p, boolean flag, int[][][] binaryDp, int[] binaryNums, int pre, int prepre) {
        // 边界条件，二进制数位数已经达到最高
        if (p == binaryNums.length) {
            return 1;
        }

        // 如果不是第一次递归且已经计算过，直接返回结果
        if (!flag && binaryDp[p][pre][prepre] != 0) {
            return binaryDp[p][pre][prepre];
        }

        // 如果是第一次递归或者需要继续递归，则继续计算
        int index = flag ? binaryNums[p] : 1;
        int count = 0;

        for (int i = 0; i < index + 1; i++) {
            // 如果出现 101，则直接跳过
            if (i == 1 && pre == 0 && prepre == 1) {
                continue;
            }
            count += search(p + 1, flag && i == index, binaryDp, binaryNums, i, pre);
        }

        // 如果不是第一次递归，则将结果存储到数组中
        if (!flag) {
            binaryDp[p][pre][prepre] = count;
        }

        return count;
    }
}

