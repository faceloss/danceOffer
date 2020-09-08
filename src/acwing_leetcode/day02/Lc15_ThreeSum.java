package acwing_leetcode.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: danceOffer
 * @description:15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * 示例：
 *
 * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 * @author: mobing_yin
 * @create: 2020-09-07 16:26
 **/

public class Lc15_ThreeSum {
    // [-1, 0, 1, 2, -1, -4]
    // 核心思路：排序 定位置枚举
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 枚举 a 当前面使用某个元素作为左指针 后面就不能使用了 比如 1 1 1 2 2 3 坐标已经可以把所有结果检索到 右边不用再使用（sort后的！） 1  -1  2 3
        for (int first = 0; first < n; ++first) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = n - 1;
            int target = -nums[first];//两数之和？
            // 枚举 b
            for (int second = first + 1; second < n; ++second) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (second < third && nums[second] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
    public List<List<Integer>> threeSum2(int[] nums) {
        //nums存在重复的！
        List<List<Integer>> res = new ArrayList<>();
        if(nums ==null || nums.length==0){
            return null;
        }
        Arrays.sort(nums);
        int len  = nums.length;
        for (int first = 0; first < len ; first++) {
            if(first > 0 && nums[first] == nums[first-1]){
                first++;
            }
            int target = -nums[first];
            int third = len - 1;
            for (int second = first + 1; second < len ;second++) {
                if(second > first + 1 && nums[second] == nums[second-1]){
                    continue;
                }
                while(second < third && nums[second]+nums[third]>target){
                    third--;
                }
                if(second == third){
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    res.add(list);
                }
            }

        }
        return res;
    }

}
