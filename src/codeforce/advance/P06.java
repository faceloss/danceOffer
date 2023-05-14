package codeforce.advance;

import java.util.Scanner;
import java.util.HashMap;

public class P06 {
    public static void main(String[] args) {
        // 处理输入
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }

        int result = 0;

        int left = 0;
        int right = 0;
        // 统计每个数字出现的次数
        HashMap<Integer, Integer> numCount = new HashMap<>();

        while (left < n && right < n) {
            // 右指针向右移动
            Integer num = nums[right];
            numCount.put(num, numCount.getOrDefault(num, 0) + 1);
            // 如果当前数字出现次数大于等于k，则以右指针为结尾的子数组都是优雅子数组
            if (numCount.get(num) >= k) {
                result += n - right;
                // 左指针向右移动，缩小子数组范围
                numCount.put(nums[left], numCount.get(nums[left]) - 1);
                left++;
                // 右指针向左移动，减少当前数字出现次数
                numCount.put(num, numCount.get(num) - 1);
                right--;
            }
            right++;
        }
        System.out.println(result);
    }
}
