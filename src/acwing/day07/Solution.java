package acwing.day07;

import java.util.HashMap;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2021-01-15 10:38
 **/

public class Solution {
    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        int[] res = twoSum(nums, target);
        System.out.println(res[0]);
        for (int i = 0; i <res.length ; i++) {
            System.out.println(res[i]);
        }
    }
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> table = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            //不能重复使用 是关键。。
            if(table.containsKey(target - nums[i])){
                return new int[]{i,table.get(target-nums[i])};
            }
            table.put(nums[i], i);
        }
        return null;
    }
}
