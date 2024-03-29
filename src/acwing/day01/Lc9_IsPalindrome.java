package acwing.day01;

/**
 * @program: danceOffer
 * @description: 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1:  输入: 121 输出: true
 * 示例 2:  输入: -121 输出: false 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:  输入: 10 输出: false 解释: 从右向左读, 为 01 。因此它不是一个回文数。 进阶:  你能不将整数转为字符串来解决这个问题吗？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: mobing_yin
 * @create: 2020-08-26 20:52
 **/

public class Lc9_IsPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome(12321));
    }
    public static  boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        int reverse = 0;
        int temp = x;
        while(temp > 0){
            int digit = temp % 10;
            temp /=10;
            if(reverse > (Integer.MAX_VALUE - digit)/10){
                return false;
            }
            reverse = reverse * 10 + digit;
        }
        return x == reverse;
    }
}
