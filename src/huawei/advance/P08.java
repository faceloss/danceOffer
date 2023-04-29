package huawei.advance;

import java.util.*;

public class P08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] nums = new int[4]; // 创建一个长度为4的整型数组nums
        for (int i = 0; i < 4; i++) { // 循环4次，读入4个整数
            nums[i] = sc.nextInt(); // 读入一个整数，并赋值给nums数组的第i个元素
        }

        System.out.println(getMinNumOfA(nums[0], nums[1], nums[2], nums[3])); // 调用getMinNumOfA方法，并输出结果
    }

    public static int getMinNumOfA(int s, int t, int a, int b) { // 定义一个静态方法，输入4个整数，返回一个整数
        int x = 0; // 初始化x为0
        int diff = t - s; // 计算差值
        while (true) { // 循环计算
            if ((diff - a * x) % b == 0 || (diff + a * x) % b == 0) { // 如果满足条件
                return Math.abs(x); // 返回x的绝对值
            }
            x++; // x自增1
        }
    }
}
