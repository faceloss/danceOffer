package acwing_leetcode.day01;

/**
 * @program: danceOffer
 * @description: 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:  输入: 123 输出: 321  
 * 示例 2:  输入: -123 输出: -321
 * 示例 3:  输入: 120 输出: 21
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/reverse-integer 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2的31,  2的31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0
 * @author: mobing_yin
 * @create: 2020-08-26 17:45
 **/

public class Lc7_ReverseInteger {
    public static void main(String[] args) {
        System.out.println(reveseInt4(123));
    }
    //憨憨思路
    public static int reverse(int x) {
        boolean isNegative = x < 0;
        String str = String.valueOf(x);
        if (isNegative){
            str = str.replace("-","");
        }
        char[] chars = str.toCharArray();
        int left = 0;
        int right =chars.length-1;
        while(left < right){
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        String res = new String(chars);
        long judge = Long.parseLong(res);
        if(judge>2147483647){
            return 0;
        }
        int result = Integer.parseInt(res);
        result = isNegative ? 0-result: result;
        return result;
    }
    // int范围 -2147483648～2147483647
    public static int reverse2(int x) {
        int rev=0;
        while(x != 0){
            // %10是取最低位
            int pop = x%10;
            //向右移位 10进制
            x/=10;
            // 这里可以cover所有测试用例
            if(rev>214748364 || rev<-214748364 || (rev==-214748364 && pop<-8) || (rev==214748364 && pop>7)){
                return 0;
            }
            rev=rev*10+pop;
        }
        return rev;
    }

    public static int reveseInt4(int num){
        int reverse = 0;
        while(num != 0){
            int last = num % 10;
            num /= 10;
            //reverse * 10 + last =  临界值; 不能直接按照公式算。。。越界。。 所以 再判断== 并且last>7 与 <-8
            if(reverse > Integer.MAX_VALUE /10 || reverse < Integer.MIN_VALUE /10 ){
                return 0;
            }
            reverse = reverse * 10 + last;
        }
        return reverse;
    }
}
