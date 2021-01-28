/**实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

 必须 原地 修改，只允许使用额外常数空间。
 示例 1：
输入：nums = [1,2,3]
输出：[1,3,2]
 示例 2：
输入：nums = [3,2,1]
输出：[1,2,3]
 示例 3：
输入：nums = [1,1,5]
输出：[1,5,1]
 示例 4：
输入：nums = [1]
输出：[1]
 提示：
 1 <= nums.length <= 100
 0 <= nums[i] <= 100
 Related Topics 数组
 👍 909 👎 0*/
package acwing_leetcode.day03;
//java:下一个排列

//思路：从后往前如果last比last-1大，互换位置即可，没有就继续往前找直到0位置，再没有就返回Arrays.sort或者左右互换。。
public class P31_NextPermutation{
    public static void main(String[] args){
        Solution solution = new P31_NextPermutation().new Solution();
        int[] nums1 = {1,2,3};
        int[] nums2 = {3,2,1};
        solution.nextPermutation(nums1);
        solution.nextPermutation(nums2);
        System.out.println(1);
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 1) {
            return;
        }
        int len = nums.length;
        // 1 4 3 2--> 2 1 3 4
        //1234 34
        int small = 0;
        int rev = 0;
        int big = 0;
        // 找相邻升序点
        for (int i = len - 1; i > 0; i--) {
            if (nums[i] > nums[i-1]) {
                small = i-1;
                rev = i;
                break;
            }
        }
        //找最小的大数
        for (int i = len-1; i >= rev; i--) {
            if (nums[i] > nums[small]) {
                swap(nums, small, i);
                break;
            }
        }
        int i = rev;
        int j = len - 1;
        while (i < j) {
            swap(nums, i++, j--);
        }
    }
    // 1542  312 213
    public void swap(int[] arr, int i, int j){
        // 交换位置
        arr[i] = arr[i] ^ arr[j];// 取高位
        arr[j] = arr[i] ^ arr[j];// 将i的高位保留
        arr[i] = arr[i] ^ arr[j];// 将j的高位保留
//        int temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}