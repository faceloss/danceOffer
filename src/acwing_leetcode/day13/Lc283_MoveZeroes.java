package acwing_leetcode.day13;

/**
 * @program: danceOffer 移动零
 * @description: 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 示例:  输入: [0,1,0,3,12] 输出: [1,3,12,0,0] 说明:  必须在原数组上操作，不能拷贝额外的数组。 尽量减少操作次数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/move-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: mobing_yin
 * @create: 2020-08-27 09:51
 **/

//  283 284 287 289 290 、 292 295 297 299 300
public class Lc283_MoveZeroes {
    public static void main(String[] args) {
        int[] arr = {2,0,9,0,1};
        moveZeroes(arr);
        for (int i = 0; i <arr.length ; i++) {
            System.out.println(arr[i]);
        }
    }
    // 稳定的！
    public static void moveZeroes(int[] nums) {
        // 从左往右 冒泡 找到第一个0位置 然后和后面非9换
        int n = nums.length;
        if(n == 1){
            return;
        }
        int l = -1;
        // 0 1 0 2 3 0 8
        for (int i = 0; i < n ; i++) {
            if(nums[i] == 0){
                l = i;
            }
            if(l >= 0){
                for (int j = l+1; j < n; j++) {
                    if(nums[j] != 0){
                        // 3 4  011 100 111
                        nums[l] = nums[l] ^ nums[j];
                        nums[j] = nums[i] ^ nums[j];
                        nums[l] = nums[l] ^ nums[j];
                        break;
                    }
                }
            }
        }
    }

}
