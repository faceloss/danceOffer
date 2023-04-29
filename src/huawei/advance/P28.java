package huawei.advance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class P28 {
    public static ArrayList<Integer[]> result;

    public static void main(String[] args) {
        // 处理输入
        Scanner scanner = new Scanner(System.in);
        Integer[] nums = new Integer[9];
        for (int i = 0; i < 9; i++) {
            nums[i] = scanner.nextInt();
        }
        result = new ArrayList<>();

        // 全排列核心思想就是每个数字逐个与后面的数字进行交换
        permute(nums, 0, 8);

        // 排序
        result.sort((a, b) -> {
            for (int i = 0; i < 9; i++) {
                if (a[i] != b[i]) {
                    return a[i] - b[i];
                }
            }
            return 0;
        });

        // 输出
        for (Integer[] singleResult : result) {
            for (int i = 0; i < 9; i++) {
                System.out.print(singleResult[i]);
                if (i != 8) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void permute(Integer[] a, int i, int j) {
        if (i == j) {
            if (check(a)) {
                result.add(Arrays.copyOf(a, 9));
            }
        } else {
            for (int k = i; k <= j; k++) {
                swap(a, i, k); // 交换第一个i=k,即交换1和他自己；对后面的数字进行递归
                permute(a, i + 1, j); // 递归
                swap(a, i, k); // 再交换回来，进行下一次交换
            }
        }
    }

    public static boolean check(Integer[] a) {
        int fixedResult = a[0] * a[1] * a[2];
        // 每行
        if (fixedResult != a[3] * a[4] * a[5] || fixedResult != a[6] * a[7] * a[8]) {
            return false;
        }

        // 每列
        if (fixedResult != a[0] * a[3] * a[6] || fixedResult != a[1] * a[4] * a[7] || fixedResult != a[2] * a[5] * a[8]) {
            return false;
        }

        // 对角线
        if (fixedResult != a[0] * a[4] * a[8] || fixedResult != a[2] * a[4] * a[6]) {
            return false;
        }

        return true;
    }

    public static void swap(Integer[] a, int k, int i) {
        int temp = a[k];
        a[k] = a[i];
        a[i] = temp;
    }
}
