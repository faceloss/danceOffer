package acwing_leetcode.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length==0){
            return null;
        }
        HashMap map = new HashMap();
        Arrays.sort(nums);
        // -4 -1 -1 0 1 2
        int len = nums.length;
        int left = 0;
        int right = len - 1;
        while(left < right){
            if(map.containsKey(0-nums[left]-nums[right])){
                List<Integer> cur = new ArrayList<>();
                cur.add(nums[left]);
                cur.add(nums[left]);
                cur.add(0);
            }
        }

        return null;
    }
}
