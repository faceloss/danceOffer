package acwing.day13;

/**
 * @program: danceOffer 寻找重复数
 * @description: 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设只有一个重复的整数，找出这个重复的数。  示例 1:  输入: [1,3,4,2,2] 输出: 2 示例 2:  输入: [3,1,3,4,2] 输出: 3
 * 说明：  不能更改原数组（假设数组是只读的）。 只能使用额外的 O(1) 的空间。 时间复杂度小于 O(n2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。  来源：力扣（LeetCode）
 * @author: mobing_yin
 * @create: 2020-08-27 09:54
 **/

public class Lc287_FindDuplicate {
    public static void main(String[] args) {
        //  3 1 2 2 5 , 3 1  =》11 01 10 10 101=  10 00 保留了高位
        // 将 3 1 2 2异或得到的结果是3 1的异或结果
        int[] arr = {1,3,4,2,2};
        System.out.println(findDuplicate(arr));
    }
    // 不能更改原数组。。。这个复杂度是O(n2) 不对
    public static int findDuplicate(int[] nums) {
        int n = nums.length; //n+1
        int time = 0;
        for (int i = 1; i <= n-1 ; i++) {
            for (int j = 0; j <n  ; j++) {
                if(nums[j] == i){
                    time++;
                }
            }
            if(time > 1){
                return i;
            }
            time = 0;
        }
        return -1;
    }
    // n+1个数中 只有n种可能？1～n 那么以n/2为基准， 小于n/2的数量如果小于n/2说明 重复在大数中
    public static int findDuplicate2(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }
    // 快慢指针法，就是一种 映射 操作， 链表 里面的 一次映射操作，就是 求 next，且 将位置 更新到 这里；
    // 数组 这里，就是 根据 下标 i 求 nums[i] 这个元素值，且 将 下标 更新到这里。
    // 链表里面 有环，即 一个节点 被不同的 节点指向（映射）；而 这里说的 数组 有环，即 数组中的一个元素值 被不同的 index 指向（映射）； 所以，求解方法 一样可以 使用 快慢指针法
    public int findDuplicate3(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];//慢指针
            fast = nums[nums[fast]];//快指针
        } while (slow != fast);
        //找到了slow nums[fast]两个坐标（且保证 nums[2]=2,即slow != fast）需要再走一次？
        // 1 2 2 3 4
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
