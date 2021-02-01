//给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 105 
// -109 <= nums[i] <= 109 
// nums 是一个非递减数组 
// -109 <= target <= 109 
// 
// Related Topics 数组 二分查找 
// 👍 827 👎 0

package leetcode.leetcode.editor.cn;
//java:在排序数组中查找元素的第一个和最后一个位置
public class P34_FindFirstAndLastPositionOfElementInSortedArray{
    public static void main(String[] args){
        Solution solution = new P34_FindFirstAndLastPositionOfElementInSortedArray().new Solution();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length ==0){
            return new int[]{-1, -1};
        }
        int n = nums.length;
        int l= 0;
        int r = n-1;
        int first = -1;
        int last = -1;
        while(l+1<r){
            int mid = l + (r-l)/2;
            // 向右找end
            if(nums[mid] <= target){
                l = mid;
            }else{
                r = mid - 1;
            }
        }
        if(nums[l] == target){
            last = l;
        }
        if(nums[r] == target){
            last = r;
        }
        l = 0;
        r = n-1;
        while(l+1<r){
            int mid = l + (r-l)/2;
            // 向左找start
            if(nums[mid] < target){
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        if(nums[r] == target){
            first = r;
        }
        if(nums[l] == target){
            first = l;
        }
        return new int[]{first, last};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}