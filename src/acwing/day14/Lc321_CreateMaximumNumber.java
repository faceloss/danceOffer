package acwing.day14;

/**
 * @Auther: mobing
 * @Date: 2020/9/10 00:24
 * @Description:321. 拼接最大数
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
 * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 *
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 *
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 *
 * 示例 1:
 *
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 示例 2:
 *
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * 示例 3:
 *
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 */
public class Lc321_CreateMaximumNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        if(k < 1){
            return null;
        }
        int[] res = new int[k];
        int m = nums1.length;
        int n = nums1.length;
        int i = 0;
        int j = 0;
        while(i < m && j<n && k>0){

        }
        return null;
    }
}
