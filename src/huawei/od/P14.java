package huawei.od;

/**
 * @program: danceOffer
 * @description:
 * @author: mobing_yin
 * @create: 2023-04-22 21:59
 **/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P14 { //定义Main类
    public static void main(String[] args) throws IOException { //定义主函数，并抛出可能出现的IOException异常
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //定义输入缓冲流
        String money_str = br.readLine(); //从标准输入流中读入一行字符串
        int money = Integer.parseInt(money_str); //将字符串转化为整型

        String topup_str = br.readLine(); //从标准输入流中读入一行字符串

        List<Integer> v = new ArrayList<>(); //定义整型列表
        StringTokenizer st = new StringTokenizer(topup_str); //定义字符串分割器
        while (st.hasMoreTokens()) { //从字符串流中读入每个数字
            String s = st.nextToken(); //获取当前数字的字符串表示
            v.add(Integer.parseInt(s)); //将数字转化为整型并存入列表中
        }

        int n = v.size(); //获取列表大小
        int[] dp = new int[money + 1]; //定义大小为money+1的整型数组，初始值为0

        for (int i = 1; i <= n; i++) { //循环遍历每个元素
            int vi = v.get(i - 1); //获取当前元素的值
            for (int j = money; j >= i; j--) { //循环遍历每个可能的金额
                int dp_j_i = dp[j - i]; //获取上一个状态的值
                dp[j] = Math.max(dp[j], dp_j_i + vi); //更新当前状态的值
            }
        }

        System.out.println(dp[money]); //输出最终状态的值
    }
}
