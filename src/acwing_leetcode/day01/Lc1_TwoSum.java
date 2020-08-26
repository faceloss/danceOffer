package acwing_leetcode.day01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: danceOffer
 * @description: 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。   
 * 示例:  给定 nums = [2, 7, 11, 15], target = 9  因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/two-sum 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: mobing_yin
 * @create: 2020-08-26 10:00
 **/

public class Lc1_TwoSum {
    public static void main(String[] args) {
        int[] nums = {2, 9, 3, 11, 15};
        int[] res = getTwoSumIndex(nums, 14);
        for (int i = 0; i <res.length ; i++) {
            System.out.println(res[i]);
        }
    }
   // 正确解法
    public static int[] getTwoSumIndex2(int[] nums, int target){
        if(nums.length<2){
            return null;
        }
        Map<Integer,Integer> map = new HashMap();
        for (int i = 0; i < nums.length ; i++) {
            if(map.containsKey(target-nums[i])){
                return new int[]{map.get(target-nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    // 错误解法，如果是有序数组，且不重复？可以这样
    public static int[] getTwoSumIndex(int[] arr, int target){
        if(arr.length < 2){
            return null;
        }
        Map<Integer,Integer> map = new HashMap();
        for (int i = 0; i <arr.length ; i++) {
            // 这里也可以在map的value里放别的！
            map.put(arr[i], i);
        }
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length-1;
        while(left < right){
            if(arr[left]+arr[right] < target){
                left++;
            }else if(arr[left]+arr[right] > target){
                right--;
            }else{
                return new int[] {map.get(arr[left]),map.get(arr[right])};
            }
        }
        return null;
    }



}
