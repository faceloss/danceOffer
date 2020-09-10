package acwing_leetcode.day14;

/**
 * @Auther: mobing
 * @Date: 2020/9/10 00:25
 * @Description:327. 区间和的个数
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 *
 * 说明:
 * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 *
 * 示例:
 *
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出: 3
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 */
public class Lc327_CountofRangeSum {
    // i到j表示区间 区间和在l~u之间的个数  暴力解法。。。。
    public static int countRangeSum(int[] nums, int lower, int upper) {
        //滑动窗口
        int len = nums.length;
        int sum = 0;
        for (int i = 1; i <=len ; i++) {
            long temp = 0;
            for (int j = 0; j <= len-i ; j++) {
                if(j==0){
                    for (int k = j; k < j+i; k++) {
                        temp+=nums[k];
                    }
                }else{
                    // 注意边界 假如是i=len-1 j=1  j+i-1
                    temp += nums[j+i-1];
                    temp -= nums[j-1];
                }

                if(temp>=lower && temp<=upper){
                    sum++;
                }

            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr ={-2,5,-1};
        int lower = -2;
        int upper = 2;
        System.out.println(countRangeSum(arr, lower, upper));
    }
}
