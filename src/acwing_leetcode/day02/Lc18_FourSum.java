package acwing_leetcode.day02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: danceOffer
 * @description:18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？
 * 找出所有满足条件且不重复的四元组。
 *
 * 注意：
 * 答案中不可以包含重复的四元组。
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

// 两数之和用map记录 如果包含target-cur 则return

//先排序 当数组问题无思路。。排个序 预处理
// 三数之和固定first和second（固定的时候需要注意重复，如果不是第一个并且左边已经出现了，就跳过该固定点）
// 让third向左逼近，如果third=second说明没找到否则判断是否是目标值，是则添加

// 三数之和最近的固定first（固定的时候需要注意重复，如果不是第一个并且左边已经出现了，就跳过该固定
//然后去判断后面的 second和third和与target的大小关系 （如何移动这两个指针 要去除重复的判断 当然也可以重复判断？）

// 四数之和 与三数之和类似 固定 1 2 3点 找 3和4与target-1-2的关系
public class Lc18_FourSum {
    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        System.out.println(fourSum(nums, target));
    }
    // -2 -1 0 0 1 2
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        //固定 1 2 3 点 判断4
        List<List<Integer>> res = new ArrayList<>();
        if(nums == null || nums.length<4){
            return res;
        }
        Arrays.sort(nums);
        int len  = nums.length;
        // -2 -1 0 0 1 2
        for (int first = 0; first < len; first++) {
            if(first>0 && nums[first] == nums[first-1]){
                continue;
            }
            for (int second = first + 1; second < len; second++) {
                if(second > first+1 && nums[second]==nums[second-1]){
                    continue;
                }
                int four = len - 1;
                int targetTmp = target-nums[first]-nums[second];//两数之和？
                for (int third = second+1; third < len ; third++) {
                    if(third > second+1 && nums[third] == nums[third-1]){
                        continue;
                    }
                    while(third<four && targetTmp - nums[third] < nums[four]){
                        four--;
                    }
                    if(third == four){
                        break;
                    }
                    if(nums[four] + nums[third] == targetTmp){
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(nums[first]);
                        list.add(nums[second]);
                        list.add(nums[third]);
                        list.add(nums[four]);
                        res.add(list);
                    }
                }
            }

        }
        return res;
    }
}
