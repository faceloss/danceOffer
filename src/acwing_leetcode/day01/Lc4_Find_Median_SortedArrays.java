package acwing_leetcode.day01;

/**
 * @program: danceOffer
 * @description: 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。   
 * 示例 1:  nums1 = [1, 3] nums2 = [2]  则中位数是 2.0
 * 示例 2:  nums1 = [1, 2] nums2 = [3, 4]  则中位数是 (2 + 3)/2 = 2.5
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @author: mobing_yin
 * @create: 2020-08-26 15:32
 **/

public class Lc4_Find_Median_SortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
    // 2路归并 如果是无序的怎么做？ k个数组呢？ k个数组取topk 维护k个堆，堆顶是最大或者最小值 peek出来比较？
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int[] merge = new int[m+n];
        int indexm =0, indexn=0;
        int index=0;
        while(indexm<m && indexn<n){
            if(nums1[indexm]<nums2[indexn]){
                merge[index++] = nums1[indexm++];
            }else{
                merge[index++] = nums2[indexn++];
            }
        }
        while(indexm < m){
            merge[index++] = nums1[indexm++];
        }
        while(indexn < n){
            merge[index++] = nums2[indexn++];
        }
        int len = merge.length;
        if(len%2 == 0){
            return (double) (merge[len/2] + merge[len/2 -1])/2;
        }else{
            return merge[len/2];
        }
    }
}
