package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:19
 **/

import java.util.Scanner;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.math.BigInteger;
import java.util.stream.Stream;

class P49 {
    public static void main(String[] args) {
        // 创建Scanner对象
        Scanner scanner = new Scanner(System.in);
        // 获取输入的目标值
        int target = scanner.nextInt();
        // 吃掉nextInt()后面的空格
        scanner.nextLine();
        // 获取输入的整数数组
        Integer[] nums = Arrays.stream(scanner.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);

        // 定义窗口左右边界
        int left = 0, right = 0;
        // 定义窗口内的数字和
        int windowSum = 0;
        // 定义最大窗口和
        int maxWindowSum = 0;

        while (right < nums.length) {
            int temp = windowSum + nums[right];

            // 如果窗口内数字和大于目标值，左端边界+1，窗口内数字和减去左端数字
            if (temp > target) {
                windowSum -= nums[left];
                left += 1;
            }
            // 如果窗口内数字和小于目标值，右端边界+1，窗口内数字和加上右端数字，更新最大窗口和
            else if (temp < target) {
                windowSum += nums[right];
                maxWindowSum = Math.max(windowSum, maxWindowSum);
                right += 1;
            }
            // 如果窗口内数字和等于目标值，直接输出目标值并结束程序
            else {
                System.out.println(target);
                return;
            }
        }

        // 输出最大窗口和
        System.out.println(maxWindowSum);
    }
}


