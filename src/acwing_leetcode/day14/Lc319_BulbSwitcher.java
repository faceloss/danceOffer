package acwing_leetcode.day14;

import java.util.Map;

/**
 * @Auther: mobing
 * @Date: 2020/9/10 00:24
 * @Description:319. 灯泡开关
 * 初始时有 n 个灯泡关闭。
 * 第 1 轮，你打开所有的灯泡。
 * 第 2 轮，每两个灯泡你关闭一次。
 * 第 3 轮，每三个灯泡切换一次开关（如果关闭则开启，如果开启则关闭）。
 * 第 i 轮，每 i 个灯泡切换一次开关。
 * 对于第 n 轮，你只切换最后一个灯泡的开关。 找出 n 轮后有多少个亮着的灯泡。
 *
 *  0 0 0 0 0 0 0
 *  1 1 1 1 1 1 1
 *  1 0 1 0 1 0 1
 *  1 0 0 0 1 1 1
 *  1 0 0 1 1 1 1
 *  1 0 0 1 0 1 1
 *
 *
 * （1～n） 1:1次 2:2次 3:2次 4：3次 （1 2 4）
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 1
 * 解释:
 * 初始时, 灯泡状态 [关闭, 关闭, 关闭].
 * 第一轮后, 灯泡状态 [开启, 开启, 开启].
 * 第二轮后, 灯泡状态 [开启, 关闭, 开启].
 * 第三轮后, 灯泡状态 [开启, 关闭, 关闭].
 *
 * 你应该返回 1，因为只有一个灯泡还亮着。
 */
public class Lc319_BulbSwitcher {
    /**
     *  *  0 0 0 0 0 0 0
     *  *  1 1 1 1 1 1 1
     *  *  1 0 1 0 1 0 1
     *  *  1 0 0 0 1 1 1
     *  *  1 0 0 1 1 1 1
     *  *  1 0 0 1 0 1 1
     *
     *  1
     *
     *  1 1
     *  1 0
     *
     *  1 1 1
     *  1 0 1
     *  1 0 0
     *
     *  * （1～n） 1:1次 2:2次(1 2) 3:2次(1 3) 4：3次 （1 2 4）
     * */

    // 10 11 3
    //https://blog.csdn.net/afei__/article/details/80638460 判断一个数是不是素数 质数的优化

    //超时了。。。擦
    //判断素数
    public static boolean isPrime(int n) {
        if (n <= 3) {
            return n > 1;
        }
        int sqrt = (int)Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(bulbSwitch(25));
    }
    // 超时找因子个数的解法。。。
    public static int bulbSwitch(int n) {
        if(n < 1){
            return 0;
        }
        int sum = 0 ;
        // 每个位置上经历了多少次变换 最后变成了几？n+1为了看起来方便
        for (int i = 1; i <= n; i++) {
            //从0开始变换
            int time = 0;
            int index = 1;
            //25 1 2 3 4 5 25 最大的因子只能是5
            while(index*index < i){
                if(i%index++ == 0){
                    time+=2;
                }
            }
            //todo 其实这里就已经暴露奇数才有效
            if(index*index==i){
                time++;
            }
            //变换了time次 奇数次1 偶数次0
            sum += time % 2== 0 ? 0 : 1;
        }
        return sum;
    }
    // 完全平方数解法
    public static int bulbSwitch2(int n) {
        if(n==1)
            return 1;
        int result = 1;
        //比如25 以内只有 1 2 3 4 5的倍数可以有奇数个因子
        while(true) {
            if(result*result>n)
                break;
            result++;
        }
        return result-1;
    }
    //233
    public static int bulbSwitch23(int n){
        return (int)Math.sqrt(n);
    }
}
