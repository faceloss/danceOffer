package huawei.base;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 22:17
 **/

import java.util.Scanner;
import java.util.Arrays;

class P37 {
    public static void main(String[] args) {
// 处理输入
        Scanner scanner = new Scanner(System.in);
        String paramStr = scanner.nextLine();
        int count = Integer.parseInt(paramStr);
        // 构造输入数据结构，并求和
        int[] nums = new int[count];
        String numStr = scanner.nextLine();
        int sum = 0;
        String[] numList = numStr.split(" ");
        for (int i = 0; i < count; i++) {
            nums[i] = Integer.parseInt(numList[i]);
            sum += Integer.parseInt(numList[i]);
        }

        // 最大可以等分为 m 个子数组
        for (int i = count; i > 0; i--) {
            // 从最大的可能行开始，满足条件即为最小的情况
            if (canPartitionKSubsets(nums, i, sum)) {
                System.out.println(sum / i);
                break;
            }
        }
    }

    // 判断是否可以将 nums 数组分成 k 个子数组，使得每个子数组的和相等
    public static boolean canPartitionKSubsets(int[] nums, int k, int all) {
        // all 为 nums 数组的总和，如果无法整除 k，就不可能平分成 k 个子数组
        if (all % k != 0) {
            return false;
        }
        int per = all / k; // 每个子数组的和
        Arrays.sort(nums); // 将 nums 数组升序排序
        int n = nums.length;
        if (nums[n - 1] > per) {
            return false;
        }
        boolean[] dp = new boolean[1 << n]; // dp 数组用于记录状态是否被遍历过
        int[] curSum = new int[1 << n]; // curSum 数组用于记录当前状态下已经累加的和
        dp[0] = true;
        for (int i = 0; i < 1 << n; i++) { // 枚举所有状态
            if (!dp[i]) { // 如果状态 i 没有被遍历过，直接跳过
                continue;
            }
            for (int j = 0; j < n; j++) { // 枚举所有元素
                if (curSum[i] + nums[j] > per) { // 如果累加和超过了 per，说明不能再添加元素了，直接跳过
                    break;
                }
                if (((i >> j) & 1) == 0) { // 如果元素 j 不在状态 i 中
                    int next = i | (1 << j); // 将元素 j 加入到状态 i 中，得到下一个状态 next
                    if (!dp[next]) { // 如果下一个状态 next 没有被遍历过
                        curSum[next] = (curSum[i] + nums[j]) % per; // 将当前状态的累加和加上元素 j 的值，更新下一个状态的累加和
                        dp[next] = true; // 标记下一个状态已经被遍历过
                    }
                }
            }
        }
        return dp[(1 << n) - 1];
    }
}
