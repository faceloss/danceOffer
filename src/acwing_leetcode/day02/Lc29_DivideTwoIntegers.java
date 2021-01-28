package acwing_leetcode.day02;

/**
 * @program: danceOffer
 * @description:29. 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 *
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 *
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 *
 *
 *
 * 示例 1:
 *
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 *
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *
 *
 * 提示：
 *
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 * @author: mobing_yin
 * @create: 2020-09-08 17:36
 **/

public class Lc29_DivideTwoIntegers {
    public static void main(String[] args) {
        Lc29_DivideTwoIntegers s = new Lc29_DivideTwoIntegers();
        int res = s.divide(-2147483648,2);
        System.out.println(res);
    }
    public int divide(int dividend, int divisor) {
        if(dividend == 0) return 0;
        if(divisor == 1) return dividend;
        if(divisor == -1){
            if(dividend > Integer.MIN_VALUE){
                return - dividend;
            }
            if(dividend == Integer.MIN_VALUE){
                return Integer.MAX_VALUE;
            }
        }
        int sign = 1;
        int total = dividend;
        int base = divisor;
        if(dividend < 0){
            sign = -sign;
        }else{
            total = -total;
        }
        if(divisor<0){
            sign = -sign;
        }else{
            base = -base;
        }
        int res = recur(total, base);
        if(sign < 0){
            res = - res;
        }
        return res;
    }
    public int recur(int total, int base){
        if(total > base){
            return 0;
        }
        int cnt = 1;
        // base分支下去不变
        int cur = base;
        while(cur + cur >= total && cur +cur < 0){
            cnt += cnt;
            cur += cur;
        }
        //收集了1/2 剩下1/2
        return cnt + recur(total - cur, base);
    }
}
