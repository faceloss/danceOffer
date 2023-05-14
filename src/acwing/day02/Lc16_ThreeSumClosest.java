package acwing.day02;

import java.util.Arrays;

/**
 * @program: danceOffer
 * @description:16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。
 * 返回这三个数的和。假定每组输入只存在唯一答案。
 * 示例：
 *
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 *
 * 提示：
 *
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 * @author: mobing_yin
 * @create: 2020-09-07 16:26
 **/

public class Lc16_ThreeSumClosest {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4,8,16,32,64,128};
        int[] arr1 = new int[]{-1,2,1,-4};

        // 2 16 64
        System.out.println(threeSumClosest(arr1, 1));
        Lc16_ThreeSumClosest new1 = new Lc16_ThreeSumClosest();
        System.out.println(Solution.threeSumClosest(arr1, 1));

    }


    public static int threeSumClosest(int[] nums, int target) {
        if(nums ==null || nums.length < 3 || nums.length > 1000 || Math.abs(target) > 10000){
            return -1;
        }
        Arrays.sort(nums);
        int len = nums.length;
        int res = 10000000;
        for (int i = 0; i < len; i++) {
            int start = i + 1;
            int end = len - 1;
            while(start < end ){
                int sum = nums[i] + nums[start] +nums[end];
                if(Math.abs(sum-target) == 0){
                    return sum;
                }
                if(Math.abs(sum-target)<Math.abs(res-target)){
                    res = sum;
                }
                if(sum > target){
                    int ee = end - 1;
                    while(start < ee && nums[ee] == nums[end]){
                        ee--;
                    }
                    end = ee;
                }else{
                    int ss = start + 1;
                    while(ss < end && nums[ss] == nums[start]){
                        ss++;
                    }
                    start = ss;
                }
            }
        }
        return res;
    }



    // Three Sum 是固定一个点，寻找另外两个点等于target的可能性（可以多个） 枚举比较
    // Three Sum Closest 固定一个点 寻找最近的可能（一个）双指针往中间逼近 并收集结果
    static class Solution {
        public static int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int n = nums.length;
            int best = 10000000;
            // 枚举 a
            for (int i = 0; i < n; ++i) {
                // 保证和上一次枚举的元素不相等
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                // 使用双指针枚举 b 和 c
                int j = i + 1, k = n - 1;
                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];
                    // 如果和为 target 直接返回答案
                    if (sum == target) {
                        return target;
                    }
                    // 根据差值的绝对值来更新答案
                    if (Math.abs(sum - target) < Math.abs(best - target)) {
                        best = sum;
                    }
                    if (sum > target) {
                        // 如果和大于 target，移动 c 对应的指针
                        int k0 = k - 1;
                        // 移动到下一个不相等的元素
                        while (j < k0 && nums[k0] == nums[k]) {
                            --k0;
                        }
                        k = k0;
                    } else {
                        // 如果和小于 target，移动 b 对应的指针
                        int j0 = j + 1;
                        // 移动到下一个不相等的元素
                        while (j0 < k && nums[j0] == nums[j]) {
                            ++j0;
                        }
                        j = j0;
                    }
                }
            }
            return best;
        }
    }
}
