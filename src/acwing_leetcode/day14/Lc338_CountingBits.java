package acwing_leetcode.day14;

/**
 * @Auther: mobing
 * @Date: 2020/9/6 16:54
 * @Description:
 * 338. 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 *
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 *
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 *
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 */
public class Lc338_CountingBits {
    public static int[] countBits(int num) {
        int[] dp=new int[num+1];
        dp[0]=0;
        int i;
        for (i=1;i<=num;i++){
            //当前数有多少个1可以由前面位个数加上当前位置是否为1
            dp[i]=dp[i>>1]+(i%2);//如果判断0的个数可以将i%1 变成(i+1)/2
        }
        return dp;
    }

    public static int[] countBitsZero(int num) {
        int[] dp=new int[num+1];
        dp[0]=1;
        if(num<1){
            return dp;
        }
        dp[1]=0;
        int i;
        //5 101
        //4 110
        //3 011
        //2 010
        for (i=2;i<=num;i++){
            //当前数有多少个1可以由前面位个数加上当前位置是否为1
            dp[i]=dp[i>>1]+(i%2==0?1:0);//如果判断0的个数可以将i%1 变成(i+1)/2
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] resZero = countBitsZero(10);
        int[] res = countBits(10);
        for (int i = 0; i < 11; i++) {
            System.out.println("one is" + res[i]);
        }
        System.out.println("********");
        for (int i = 0; i < 11; i++) {
            System.out.println("zero is" + resZero[i]);
        }
    }
}
