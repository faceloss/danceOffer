package aim2offer;

/**
 * 输入一个整数n，求从1到n这n个整数的十进制表示中1出现的次数。
 * 例如输入12，从1到12这些整数中包含1的数字有1,10,11和12,1一共出现5次。
 */

public class No32 {

    public static void main(String[] args) {
        System.out.println(countOne(115));
        System.out.println(Integer.bitCount(7));

    }

    private static long countOne(int n) {
        //收集总数量
        long count = 0;
        //对应位置上出现的个数 个十百
        long i = 1;// 这里一直乘10 会溢出
        long current = 0, after = 0, before = 0;
        while ((n / i) != 0) {
            // i位置上的数字是current 321 个位置是1
            current = (n / i) % 10;
            //个位 01 11 21 31 41 51 61 71 81 91
            // 刚好撞到1 大于1 等于0三种情况
            // cur前面的高位
            before = n / (i * 10);
            //cur后面的低位
            after = n - (n / i) * i;

            if (current > 1)
                count = count + (before + 1) * i;
            else if (current == 0)
                count = count + before * i;
            else if (current == 1)
                count = count + before * i + after + 1;
            // 统计下一个
            if(n/10 < i){
                break;
            }
            i = i * 10;
        }
        return count;
    }
    // 如何防止n溢出
    public int countDigitOne(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0) res += high * digit;
            else if(cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }

}
