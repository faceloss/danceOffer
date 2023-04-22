package acwing_leetcode.day10;

class Solution {
    public static void main(String[] args) {
        int[] nums = {5};
        System.out.println(findKthLargest(nums,1));
    }
    public static int findKthLargest(int[] nums, int k) {
    // write your code here
        return quickSelect(nums, 0, nums.length - 1, k);
    }
    // 求前k个数用堆  求第k个数使用partition left到j i到right  j+1 和i-1最多重合 判断第k个在左还是在右
    //  两个数组中的第k 使用k/2之间的比较
    private static int quickSelect(int[] nums, int left, int right, int k) {
        int pivot = nums[left];
        // partition 的值选哪一个？ mid或者别的都可以吧
        int i = left, j = right;
        while (i <= j) {
            while (i <= j && nums[i] > pivot) {
                i++;
            }
            while (i <= j && nums[j] < pivot) {
                j--;
            }
            //这里很重要 相等也会交换 让i j错位 只剩下一个的时候 错位 返回j+1 或者i-1
            if (i <= j) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i++;
                j--;
            }
        }
        // 1 2 3例子 找第一个 0 1 2
        // left 到j-1 i+1 到right都是大于或者小于 的
        if (left + k - 1 <= j) {
            return quickSelect(nums, left, j, k);
        }
        if (left + k - 1 >= i) {
            return quickSelect(nums, i, right, k - (i - left));
        }
        // 在j和i之间 那就return j+1
        // 在收集第k 或者前k都可以使用这个方法 而不用使用堆的on做法
        for(int x = 0;x<=j+1;x++){
            System.out.println(nums[x]);
        }
        return nums[j + 1];
    }
}