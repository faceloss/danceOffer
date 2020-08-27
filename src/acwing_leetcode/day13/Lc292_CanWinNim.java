package acwing_leetcode.day13;

import java.util.Scanner;

/**
 * @program: danceOffer
 * @description: 292. Nim 游戏 你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。
 * 拿掉最后一块石头的人就是获胜者。你作为先手。  你们是聪明人，每一步都是最优解。 编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。
 * 示例:  输入: 4 输出: false  解释: 如果堆中有 4 块石头，那么你永远不会赢得比赛；
 * 因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
 * @author: mobing_yin
 * @create: 2020-08-27 09:56
 **/

public class Lc292_CanWinNim {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextInt()){
            int n = sc.nextInt();
            System.out.println(canWinNim(n) == canWinNim2(n));
        }
        //我的5是否赢 取决于234他能赢不 他能！ 但是我先手。。。
    }
    //内存会溢出 因为n太大 滚动数组。。。但是依然超时
    public static boolean canWinNim(int n) {
        if(n<4){
            return true;
        }
        // 先手方是否存在获胜 1 2 3 4 5 ~ n
        boolean[] dp = new boolean[4];
        for (int i = 0; i < 3; i++) {
            dp[i] = true;//初始化
        }
        for (int i = 3; i <n ; i++) {
            //先手4个能否获胜？我能否获胜取决我能否堵死他（他即我，博弈。。）先手的的三种获胜路径（
            dp[3] = !(dp[2] && dp[1] && dp[0]);
            // 4 = 3 2 1
            dp[0] = dp[1];
            dp[1] = dp[2];
            dp[2] = dp[3];
        }
        return dp[3];
    }

    // 被4整除 我赢不了
    public static boolean canWinNim2(int n) {
        return (n % 4 != 0);
    }
}
