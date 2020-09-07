package acwing_leetcode.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: danceOffer
 * @description:18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：
 *
 * 答案中不可以包含重复的四元组。
 *
 * 示例：
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 *
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 * @author: mobing_yin
 * @create: 2020-09-07 16:26
 **/

public class Lc18 {
   /* public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums.length<4){
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int l = 0;
        int r = nums.length;
        while(l + 2 < r){
            List<Integer> curList = new ArrayList();
            while(arr[l] == leftV){
                l++;
            }
            while(arr[r] == rightV){
                r--;
            }
            int curTarget = target - nums[l] - nums[r];
            findTar(res, nums, curTarget, l, right);
        }
    }
    public void findTar(List<List<Integer>> res, int[] arr, int target, int left, int right){
        if(r - l < 1){
            return null;
        }
        List<Integer> tar = new ArrayList<>();
        while(left < right){
            int leftV = arr[left];
            int rightV = arr[right];
            if(arr[left]+arr[right] < target){
                left++;
            }else if(arr[left]+arr[right] > target){
                right--;
            }else{
                tar.add(arr[left]);
                tar.add(nums[right]);
                while(arr[left] == leftV){
                    left++;
                }
                while(arr[right] == rightV){
                    right--;
                }
                res.add(tar);
            }
        }
    }*/
}
