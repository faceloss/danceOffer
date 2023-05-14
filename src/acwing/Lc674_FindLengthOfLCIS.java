package acwing;

/**
 * @Auther: mobing
 * @Date: 2020/9/5 19:58
 * @Description:
 * 674. 最长连续递增序列
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列，并返回该序列的长度。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 * 示例 2:
 *
 * 输入: [2,2,2,2,2]
 * 输出: 1
 * 解释: 最长连续递增序列是 [2], 长度为1。
 */
public class Lc674_FindLengthOfLCIS {

    public static void main(String[] args) {
        int[] arr= {1,3,5,7,2,3,1};
        System.out.println(findLengthOfLCIS(arr));
    }
    //最长连续递增序列
    public static int findLengthOfLCIS(int[] nums) {
        if(nums ==null || nums.length<1){
            return 0;
        }
        int res = Integer.MIN_VALUE;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = nums[i] > nums[i-1] ? dp[i-1] + 1 : 1;
        }
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        //reverse 如果求的是非递增 上升子序列 就反转一下 然后判单递增
/*        int q,w,e;
        q=0;
        w=len-1;
        while(q<w){
            e=A[q];
            A[q]=A[w];
            A[w]=e;
            q++;
            w--;
        }*/
        return res;
    }
}
