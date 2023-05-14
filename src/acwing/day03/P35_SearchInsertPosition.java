/**
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 你可以假设数组中无重复元素。
 示例 1:
 输入: [1,3,5,6], 5
输出: 2
 示例 2:
 输入: [1,3,5,6], 2
输出: 1
 示例 3:
 输入: [1,3,5,6], 7
输出: 4
 示例 4:
 输入: [1,3,5,6], 0
输出: 0
 Related Topics 数组 二分查找
 👍 801 👎 0
*/

package acwing.day03;
//java:搜索插入位置
public class P35_SearchInsertPosition{
    public static void main(String[] args){
        Solution solution = new P35_SearchInsertPosition().new Solution();
        int[] nums = {1};
        int target = 1;
        System.out.println(solution.searchInsert(nums, target));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length==0){
            return -1;
        }
        int n = nums.length;
        if(target<nums[0]){
            return 0;
        }
        if(target>nums[n-1]){
            return n;
        }
        int l = 0;
        int r = n-1;
        while(l+1 < r){
            int mid =l + (r-l)/2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                // 如果中间小于目标 1 3 5 7 9 目标
                l = mid;
            }else{
                r = mid;
            }
        }
        return nums[l] == target ? l : l+1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}