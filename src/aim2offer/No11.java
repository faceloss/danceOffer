package aim2offer;

/**
 * 实现函数double Power(double base,int exponent)，
 * 求base的exponent次方。不得使用库函数，
 * 同时不需要考虑大数问题。
 */

public class No11 {

    public static void main(String[] args) {
        System.out.println(Power(2.0, 3));
    }

    //没有考虑exponent是负数
    public static double Power(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;

        double result = Power(base, exponent >> 1);
        result *= result;
        //奇数 还是偶数的次方
        if ((exponent & 0x1) == 1) {
            result *= base;
        }
        return result;
    }

    class Solution {
        public double quickMul(double x, long N) {
            if (N == 0) {
                return 1.0;
            }
            double y = quickMul(x, N / 2);
            return N % 2 == 0 ? y * y : y * y * x;
        }

        public double myPow(double x, int n) {
            long N = n;
            return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
        }
    }
}
